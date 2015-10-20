package comapps.com.theblindbutcherdallas;

import android.app.Application;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by me on 10/6/2015.
 */
public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(getApplicationContext());

        // Add your initialization code here
        Parse.initialize(this, "3auP8OKsyBVdwDFFhQ7bAINSFldjA0zYrrSiKjIx", "FE0F9uUocCMzPiSyMl0UoEWDT1tdzYs3MOcQwp3O");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);

        //   PushService.setDefaultPushCallback(this, MainActivity.class);


        ParseInstallation.getCurrentInstallation().saveInBackground();



        ParseQuery<ParseObject> queryDrinks = new ParseQuery<ParseObject>(
                "theblindbutcherdrinks");
        queryDrinks.setLimit(200);


        queryDrinks.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> object, ParseException e) {
                // Remove the previously cached results.
                ParseObject.unpinAllInBackground("theblindbutcherdrinks", new DeleteCallback() {
                    public void done(ParseException e) {
                        // Cache the new results.
                        ParseObject.pinAllInBackground("theblindbutcherdrinks", object);
                    }
                });
            }
        });

        ParseQuery<ParseObject> queryMenu = new ParseQuery<ParseObject>(
                "theblindbutchermenu");
        // Locate the column named "day" in Parse.com and order list
        // by ascending

        queryMenu.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> object, ParseException e) {
                // Remove the previously cached results.
                ParseObject.unpinAllInBackground("theblindbutchermenu", new DeleteCallback() {
                    public void done(ParseException e) {
                        // Cache the new results.
                        ParseObject.pinAllInBackground("theblindbutchermenu", object);
                    }
                });
            }
        });





    }

}
