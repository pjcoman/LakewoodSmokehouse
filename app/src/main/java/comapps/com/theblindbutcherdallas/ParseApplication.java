package comapps.com.theblindbutcherdallas;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.startup.BootstrapNotifier;
import org.altbeacon.beacon.startup.RegionBootstrap;

import java.util.List;

/**
 * Created by me on 10/6/2015.
 */
public class ParseApplication extends Application implements BootstrapNotifier {

    private static final String TAG = "ParseApplication";

    private RegionBootstrap regionBootstrap;

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



        ParseQuery<ParseObject> queryDrinks = new ParseQuery<>(
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

        ParseQuery<ParseObject> queryMenu = new ParseQuery<>(
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

        ParseQuery<ParseObject> queryReviews = new ParseQuery<>(
                "theblindbutcherreviews");
        // Locate the column named "day" in Parse.com and order list
        // by ascending

        queryReviews.findInBackground(new FindCallback<ParseObject>() {
            public void done(final List<ParseObject> object, ParseException e) {
                // Remove the previously cached results.
                ParseObject.unpinAllInBackground("theblindbutcherreviews", new DeleteCallback() {
                    public void done(ParseException e) {
                        // Cache the new results.
                        ParseObject.pinAllInBackground("theblindbutcherreviews", object);
                    }
                });
            }
        });


        Log.d(TAG, "App started up");

        BeaconManager beaconManager = BeaconManager.getInstanceForApplication(this);

        beaconManager.getBeaconParsers().add(new BeaconParser().
                setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25"));


        String blueUUID = "b7d1027d-6788-416e-994f-ea11075f1765";

        Region region = new Region("comapps.com.theblindbutcherdallas", Identifier.parse(blueUUID), null, null);


        regionBootstrap = new RegionBootstrap(this, region);

        beaconManager.setDebug(true);





    }



    @Override
    public void didEnterRegion(Region region) {

        Log.d(TAG, "did enter region.");
        regionBootstrap.disable();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.startActivity(intent);

    }

    @Override
    public void didExitRegion(Region region) {

    }

    @Override
    public void didDetermineStateForRegion(int i, Region region) {

    }
}
