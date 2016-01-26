package comapps.com.lakewoodsmokehouse;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import comapps.com.lakewoodsmokehouse.drinks.DrinksViewPager;
import comapps.com.lakewoodsmokehouse.menu.MenuViewPager;
import comapps.com.lakewoodsmokehouse.menu.ToGoRecyclerActivity;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity  {

    private ViewGroup mRoot;
    private Button drinkButton;
    private Button drinkButton2;
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        mRoot = (ViewGroup) findViewById(R.id.container_a);
        drinkButton = (Button) findViewById(R.id.drinkbutton);
        eatButton = (Button) findViewById(R.id.eatbutton);
        drinkButton2 = (Button) findViewById(R.id.drinkbutton2);
        addReviewButton = (Button) findViewById(R.id.button_add);
        readReviewButton = (Button) findViewById(R.id.button_read);

    /*    Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new AccelerateInterpolator()); //add this
        fadeIn.setDuration(500);

        Animation fadeInButtons = new AlphaAnimation(0, 1);
        fadeInButtons.setInterpolator(new AccelerateInterpolator()); //add this
        fadeInButtons.setDuration(100);
        fadeInButtons.setStartOffset(500);

        Animation fadeInButtons2 = new AlphaAnimation(0, 1);
        fadeInButtons2.setInterpolator(new AccelerateInterpolator()); //add this
        fadeInButtons2.setDuration(100);
        fadeInButtons2.setStartOffset(600);

        Animation fadeInButtons3 = new AlphaAnimation(0, 1);
        fadeInButtons2.setInterpolator(new AccelerateInterpolator()); //add this
        fadeInButtons2.setDuration(100);
        fadeInButtons2.setStartOffset(600);

        Animation fadeInButtons4 = new AlphaAnimation(0, 1);
        fadeInButtons3.setInterpolator(new AccelerateInterpolator()); //add this
        fadeInButtons3.setDuration(100);
        fadeInButtons3.setStartOffset(700);

        Animation fadeInButtons5 = new AlphaAnimation(0, 1);
        fadeInButtons4.setInterpolator(new AccelerateInterpolator()); //add this
        fadeInButtons4.setDuration(100);
        fadeInButtons4.setStartOffset(800);


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
        AnimationSet animationButtons5 = new AnimationSet(false); //change to false
        animationButtons5.addAnimation(fadeInButtons5);

        mRoot.startAnimation(animation);


        drinkButton.startAnimation(animationButtons);
        eatButton.startAnimation(animationButtons2);
        drinkButton2.startAnimation(animationButtons3);
        addReviewButton.startAnimation(animationButtons4);
        readReviewButton.startAnimation(animationButtons5);


        */

        //   setupWindowAnimations();







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
            callIntent.setData(Uri.parse("tel:9726777906"));
            startActivity(callIntent);

            return true;
        }



        if (id == R.id.action_settings2) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://goo.gl/maps/R4xMDMbp1vm")));

            return true;
        }

        if (id == R.id.action_settings3) {



            Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
            String facebookUrl = getFacebookPageURL(this);
            facebookIntent.setData(Uri.parse(facebookUrl));
            startActivity(facebookIntent);

        }

        if (id == R.id.action_settings4) {

            Intent intent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");
            intent.setComponent(new ComponentName("com.instagram.android", "com.instagram.android.activity.UrlHandlerActivity"));
            intent.setData(Uri.parse("http://instagram.com/_u/lakewoodsmokehouse"));
            startActivity(intent);

            return true;
        }

        if (id == R.id.action_settings5) {
            Intent intent;
            String username = "lakewood_bbq";
            try {
                // get the Twitter app if possible
                this.getPackageManager().getPackageInfo("com.twitter.android", 0);
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + username));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            } catch (Exception e) {
                // no Twitter app, revert to browser
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/lakewood_bbq"));
            }
            this.startActivity(intent);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    public void drinkslist(View v) {

        Animation animationXpos = new TranslateAnimation(0, 300, 0, 0);
        animationXpos.setDuration(500);
        //  animationXpos.setRepeatMode(Animation.REVERSE);
        drinkButton.startAnimation(animationXpos);

        animationXpos.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Intent intentDrinks = new Intent();
                intentDrinks.setClass(MainActivity.this, DrinksViewPager.class);
                intentDrinks.putExtra("activityId", "drinks");
                startActivity(intentDrinks);
                overridePendingTransition(R.anim.pushinfromright,
                        R.anim.pushouttoleft);

            }
        });
    }


    public void menulist(View v) {
        Animation animationXpos = new TranslateAnimation(0, -300, 0, 0);
        animationXpos.setDuration(500);
        //   animationXpos.setRepeatMode(Animation.REVERSE);
        eatButton.startAnimation(animationXpos);

        animationXpos.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Intent intentMeals = new Intent();
                intentMeals.setClass(MainActivity.this, MenuViewPager.class);
                intentMeals.putExtra("activityId", "meals");
                startActivity(intentMeals);
                overridePendingTransition(R.anim.pushinfromleft,
                        R.anim.pushouttoright);

            }
        });




    }

    public void drinklist2(View v) {

        Animation animationXpos = new TranslateAnimation(0, 300, 0, 0);
        animationXpos.setDuration(500);
        //  animationXpos.setRepeatMode(Animation.REVERSE);
        drinkButton2.startAnimation(animationXpos);

        animationXpos.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Intent intentDrinks = new Intent();
                intentDrinks.setClass(MainActivity.this, DrinksViewPager.class);
                intentDrinks.putExtra("activityId", "drinks2");
                startActivity(intentDrinks);
                overridePendingTransition(R.anim.pushinfromright,
                        R.anim.pushouttoleft);

            }
        });
    }

    public void togo(View v) {



                Intent intentToGo = new Intent();
            //    intentToGo.setClass(MainActivity.this, ToGoListViewActivity.class);
                //
                intentToGo.setClass(MainActivity.this, ToGoRecyclerActivity.class);
                intentToGo.putExtra("activityId", "togo");
                startActivity(intentToGo);


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
        fade.setDuration(1000);
        TransitionManager.beginDelayedTransition(mRoot, fade);
        toggleVisibility(mRoot, drinkButton, eatButton, drinkButton2, addReviewButton, readReviewButton);


    }

    private void toggleVisibility(View... views) {
        for (View current : views) {
            if (current.getVisibility() != View.VISIBLE) {
                current.setVisibility(View.VISIBLE);
            }
        }
    }

    private static String FACEBOOK_URL = "https://www.facebook.com/LakewoodSmokehouse";
    private static String FACEBOOK_PAGE_ID = "1027584667305709";

    //method to get the right URL to use in the intent
    private String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first

        setupWindowAnimations();
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        finish();
        System.exit(0);
    }

}
