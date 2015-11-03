package comapps.com.theblindbutcherdallas;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import comapps.com.theblindbutcherdallas.drinks.DrinksViewPager;
import comapps.com.theblindbutcherdallas.menu.MenuViewPager;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/MerriweatherSans-Italic.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



 /*       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });  */
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent callIntent = new Intent(Intent.ACTION_VIEW);
            callIntent.setData(Uri.parse("tel:2148870000"));
            startActivity(callIntent);

            return true;
        }




        if (id == R.id.action_settings2) {
            Intent intent3 = new Intent(android.content.Intent.ACTION_SEND);
            intent3.setType("text/plain");String[] address = {"lindseymiller@lindseymillerpr.com"};

            intent3.putExtra(android.content.Intent.EXTRA_EMAIL, address);
            intent3.putExtra(android.content.Intent.EXTRA_SUBJECT, "subject");
            intent3.putExtra(android.content.Intent.EXTRA_TEXT, "text");
            startActivityForResult((Intent.createChooser(intent3, "Email")), 1);

            return true;
        }

        if (id == R.id.action_settings3) {





            try{

                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/576657282362451"));
                startActivity(intent);

            }catch(Exception e){

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/id=576657282362451")));

            }

            return true;
        }

        if (id == R.id.action_settings4) {

            Intent intent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");
            intent.setComponent(new ComponentName("com.instagram.android", "com.instagram.android.activity.UrlHandlerActivity"));
            intent.setData(Uri.parse("http://instagram.com/_u/blindbutchertx"));
            startActivity(intent);

            return true;
        }

        if (id == R.id.action_settings5) {
            Intent intent;
            String username = "blindbutcherdal";
            try {
                // get the Twitter app if possible
                this.getPackageManager().getPackageInfo("com.twitter.android", 0);
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + username));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            } catch (Exception e) {
                // no Twitter app, revert to browser
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/blindbutcherdal"));
            }
            this.startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }



    public void menulist(View v) {

        Intent intentMenu = new Intent();
        intentMenu.setClass(this, MenuViewPager.class);
        intentMenu.putExtra("activityId", "menu");
        startActivity(intentMenu);
    }

    public void drinkslist(View v) {

        Intent intentDrinks = new Intent();
        intentDrinks.setClass(this, DrinksViewPager.class);
        intentDrinks.putExtra("activityId", "drinks");
        startActivity(intentDrinks);
    }

    public void addReview(View v) {

        Intent intentDrinks = new Intent();
        intentDrinks.setClass(this, StoreReviews.class);

        startActivity(intentDrinks);
    }

    public void readReview(View v) {

        Intent intentDrinks = new Intent();
        intentDrinks.setClass(this, GetReviews.class);

        startActivity(intentDrinks);
    }




    @Override
    protected void attachBaseContext(Context newBase) {

        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));

    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        finish();
        System.exit(0);
    }

}
