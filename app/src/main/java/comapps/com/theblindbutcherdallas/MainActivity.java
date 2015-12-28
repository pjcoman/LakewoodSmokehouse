package comapps.com.theblindbutcherdallas;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;

import comapps.com.theblindbutcherdallas.drinks.DrinksViewPager;
import comapps.com.theblindbutcherdallas.menu.MenuViewPager;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity  {

    private ViewGroup mRoot;
    private Button drinkButton;
    private Button eatButton;
    private Button addReviewButton;
    private Button readReviewButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/MerriweatherSans-Italic.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        setContentView(R.layout.activity_main);


        mRoot = (ViewGroup) findViewById(R.id.container_a);
        drinkButton = (Button) findViewById(R.id.drinkbutton);
        eatButton = (Button) findViewById(R.id.eatbutton);
        addReviewButton = (Button) findViewById(R.id.button_add);
        readReviewButton = (Button) findViewById(R.id.button_read);

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new AccelerateInterpolator()); //add this
        fadeIn.setDuration(500);

        Animation fadeInButtons = new AlphaAnimation(0, 1);
        fadeInButtons.setInterpolator(new AccelerateInterpolator()); //add this
        fadeInButtons.setDuration(250);
        fadeInButtons.setStartOffset(500);

        Animation fadeInButtons2 = new AlphaAnimation(0, 1);
        fadeInButtons2.setInterpolator(new AccelerateInterpolator()); //add this
        fadeInButtons2.setDuration(250);
        fadeInButtons2.setStartOffset(750);

        Animation fadeInButtons3 = new AlphaAnimation(0, 1);
        fadeInButtons3.setInterpolator(new AccelerateInterpolator()); //add this
        fadeInButtons3.setDuration(250);
        fadeInButtons3.setStartOffset(1000);

        Animation fadeInButtons4 = new AlphaAnimation(0, 1);
        fadeInButtons4.setInterpolator(new AccelerateInterpolator()); //add this
        fadeInButtons4.setDuration(250);
        fadeInButtons4.setStartOffset(1250);


        AnimationSet animation = new AnimationSet(false); //change to false
        animation.addAnimation(fadeIn);

        AnimationSet animationButtons = new AnimationSet(false); //change to false
        animationButtons.addAnimation(fadeInButtons);
        AnimationSet animationButtons2 = new AnimationSet(false); //change to false
        animationButtons2.addAnimation(fadeInButtons2);
        AnimationSet animationButtons3 = new AnimationSet(false); //change to false
        animationButtons3.addAnimation(fadeInButtons3);
        AnimationSet animationButtons4 = new AnimationSet(false); //change to false
        animationButtons4.addAnimation(fadeInButtons4);

        mRoot.startAnimation(animation);


        drinkButton.startAnimation(animationButtons);
        eatButton.startAnimation(animationButtons2);
        addReviewButton.startAnimation(animationButtons3);
        readReviewButton.startAnimation(animationButtons4);

        //   setupWindowAnimations();



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
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/dir/''/1919+Greenville+Ave,+Dallas,+TX+75206" +
                    "/@32.8141255,-96.7726532,17z/data=!3m1!4b1!4m8!4m7!1m0!1m5!1m1!1s0x864e9f49d45f8155:0x6d15b5e3c59991b0!2m2!1d-96.7704645!2d32.8141255")));

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

        Intent intentMeals = new Intent();
        intentMeals.setClass(MainActivity.this, MenuViewPager.class);
        intentMeals.putExtra("activityId", "meals");
        startActivity(intentMeals);
        overridePendingTransition(R.anim.pushinfromleft,
                R.anim.pushouttoright);


    }

    public void drinkslist(View v) {

        Intent intentDrinks = new Intent();
        intentDrinks.setClass(this, DrinksViewPager.class);
        intentDrinks.putExtra("activityId", "drinks");
        startActivity(intentDrinks);
        overridePendingTransition(R.anim.pushinfromright,
                R.anim.pushouttoleft);
    }

    public void addReview(View v) {

        Intent intentAddReview = new Intent();
        intentAddReview.setClass(this, AddReview.class);

        startActivity(intentAddReview);
    }


    public void readReview(View v) {

        Intent intentReviews = new Intent();
        intentReviews.setClass(this, GetReviews.class);

        startActivity(intentReviews);
    }




    @Override
    protected void attachBaseContext(Context newBase) {

        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));

    }

    private void setupWindowAnimations() {
        Fade fade = new Fade();
        fade.setDuration(3000);
        TransitionManager.beginDelayedTransition(mRoot, fade);
        toggleVisibility(mRoot, drinkButton, eatButton, addReviewButton, readReviewButton);


    }

    public void toggleVisibility(View... views) {
        for (View current : views) {
            if (current.getVisibility() != View.VISIBLE) {
                current.setVisibility(View.VISIBLE);
            }
        }
    }





    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        finish();
        System.exit(0);
    }

}
