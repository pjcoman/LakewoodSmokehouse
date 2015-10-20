package comapps.com.theblindbutcherdallas.drinks;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerTitleStripV22;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import comapps.com.theblindbutcherdallas.R;


/**
 * Created by me on 10/6/2015.
 */
public class DrinksViewPager extends AppCompatActivity {


    ViewPager viewPager = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_viewpager_drinks);


        Typeface gp = Typeface.createFromAsset(getAssets(), "fonts/Garamond-Premier-Pro_19595.ttf");






        String idOfSendingActivity = getIntent().getStringExtra("activityId");

        Log.d("Sending activty is", idOfSendingActivity);



        viewPager = (ViewPager) findViewById(R.id.pager);
        PagerTitleStripV22 pts = (PagerTitleStripV22) findViewById(R.id.title);
        pts.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new MyAdapter(fragmentManager));


        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "fonts/Garamond-Premier-Pro_19595.ttf");


        for (int i = 0; i < pts.getChildCount(); i++) {

            View nextChild = pts.getChildAt(i);
            if (nextChild instanceof TextView) {
                TextView textViewToConvert = (TextView) nextChild;
                textViewToConvert.setTypeface(tf);

            }


        }


        switch (idOfSendingActivity) {
            case "beer":
                viewPager.setCurrentItem(0);
                break;
            case "cocktails":
                viewPager.setCurrentItem(1);
                break;
            case "food":
                viewPager.setCurrentItem(2);
                break;
            case "specials":
                viewPager.setCurrentItem(3);
                break;
            case "staff":
                viewPager.setCurrentItem(4);
                break;

            default:
                viewPager.setCurrentItem(0);
        }


    }


 /*   @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_actions, menu);

        return super.onCreateOptionsMenu(menu);


    }


    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_map:
                Map();
                return true;
            case R.id.action_gmail:
                Gmail();
                return true;
            case R.id.action_call:
                CallStans();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void Map() {

        // TODO Auto-generated method stub

        Intent intent5 = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://www.google.com/maps/place/Capitol+Pub" +
                        "/@32.8148723,-96.7787406,17z/data=!3m1!4b1!4m2!3m1!1s0x864e9f30f7a3dcdb:0x1abe5d7d32dc6008"));
                intent5.setComponent(new ComponentName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity"));
                startActivity(intent5);

    }


    private void Gmail() {
        // TODO Auto-generated method stub

        // The following code is the implementation of Email client
        Intent intent3 = new Intent(android.content.Intent.ACTION_SEND);
        intent3.setType("text/plain");
        String[] address = {"amy@capitolpubdallas.com"};

        intent3.putExtra(android.content.Intent.EXTRA_EMAIL, address);
        intent3.putExtra(android.content.Intent.EXTRA_SUBJECT, "subject");
        intent3.putExtra(android.content.Intent.EXTRA_TEXT, "text");

        startActivityForResult((Intent.createChooser(intent3, "Email")), 1);

    }


    private void CallStans() {

        // TODO Auto-generated method stub

        Intent callIntent = new Intent(Intent.ACTION_VIEW);
        callIntent.setData(Uri.parse("tel:2148879330"));
        startActivity(callIntent);


    }  */


    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == 0) {

                fragment = new DrinksListViewFragmentImportedBeersCans();
            }
            if (position == 1) {

                fragment = new DrinksListViewFragmentDomesticBeersCans();
            }
            if (position == 2) {

                fragment = new DrinksListViewFragmentImportedBeersBottles();
            }
            if (position == 3) {

                fragment = new DrinksListViewFragmentDomesticBeersBottles();
            }
            if (position == 4) {

                fragment = new DrinksListViewFragmentImportedBombers();
            }
            if (position == 5) {

                fragment = new DrinksListViewFragmentDomesticBombers();
            }
            if (position == 6) {

                fragment = new DrinksListViewFragmentNitroDrafts();
            }
            if (position == 7) {

                fragment = new DrinksListViewFragmentOtherBeers();
            }

            if (position == 8) {

                fragment = new DrinksListViewFragmentDrafts();
            }

            if (position == 9) {

                fragment = new DrinksListViewFragmentLocalDrafts();
            }

            if (position == 10) {

                fragment = new DrinksListViewFragmentCocktails();
            }







            return fragment;
        }

        @Override
        public int getCount() {
            return 11;
        }


        @Override
        public CharSequence getPageTitle(int position) {

            String[] tabTitles = {"IMPORTED BEERS CANS", "DOMESTIC BEERS CANS", "IMPORTED BEERS BOTTLES", "DOMESTIC BEERS BOTTLES",
                    "IMPORTED BOMBERS", "DOMESTIC BOMBERS", "NITRO DRAFTS", "OTHER BEERS", "DRAFTS", "LOCAL DRAFTS", "COCKTAILS"};


            if (position == 0) {

                SpannableStringBuilder sb = new SpannableStringBuilder(" " + tabTitles[position]); // space added before text for convenience
            //    Drawable drawable = getDrawable(R.drawable.beericon);
            //    drawable.setBounds(0, 0, 100, 100);
            //    ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
            //    sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                return sb;

            }
            if (position == 1) {

                SpannableStringBuilder sb = new SpannableStringBuilder(" " + tabTitles[position]); // space added before text for convenience
             //   Drawable drawable = getDrawable(R.drawable.whiskeyicon);
             //   drawable.setBounds(0, 0, 100, 100);
             //   ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
             //   sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                return sb;

            }
            if (position == 2) {

                SpannableStringBuilder sb = new SpannableStringBuilder(" " + tabTitles[position]); // space added before text for convenience
             //   Drawable drawable = getDrawable(R.drawable.drinkicon);
             //   drawable.setBounds(0, 0, 100, 100);
             //   ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
             //   sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                return sb;

            }
            if (position == 3) {

               SpannableStringBuilder sb = new SpannableStringBuilder(" " + tabTitles[position]); // space added before text for convenience
            //    Drawable drawable = getDrawable(R.drawable.menuicon);
            //    drawable.setBounds(0, 0, 100, 100);
            //    ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
             //   sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                return sb;

            }
            if (position == 4) {

                SpannableStringBuilder sb = new SpannableStringBuilder(" " + tabTitles[position]); // space added before text for convenience
             //   Drawable drawable = getDrawable(R.drawable.specialsicon);
             //   drawable.setBounds(0, 0, 100, 100);
             //   ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
             //   sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                return sb;

            }
            if (position == 5) {

                SpannableStringBuilder sb = new SpannableStringBuilder(" " + tabTitles[position]); // space added before text for convenience
                //   Drawable drawable = getDrawable(R.drawable.specialsicon);
                //   drawable.setBounds(0, 0, 100, 100);
                //   ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
                //   sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                return sb;

            }

            if (position == 6) {

                SpannableStringBuilder sb = new SpannableStringBuilder(" " + tabTitles[position]); // space added before text for convenience
                //   Drawable drawable = getDrawable(R.drawable.specialsicon);
                //   drawable.setBounds(0, 0, 100, 100);
                //   ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
                //   sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                return sb;

            }

            if (position == 7) {

                SpannableStringBuilder sb = new SpannableStringBuilder(" " + tabTitles[position]); // space added before text for convenience
                //   Drawable drawable = getDrawable(R.drawable.specialsicon);
                //   drawable.setBounds(0, 0, 100, 100);
                //   ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
                //   sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                return sb;

            }

            if (position == 8) {

                SpannableStringBuilder sb = new SpannableStringBuilder(" " + tabTitles[position]); // space added before text for convenience
                //   Drawable drawable = getDrawable(R.drawable.specialsicon);
                //   drawable.setBounds(0, 0, 100, 100);
                //   ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
                //   sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                return sb;

            }

            if (position == 9) {

                SpannableStringBuilder sb = new SpannableStringBuilder(" " + tabTitles[position]); // space added before text for convenience
                //   Drawable drawable = getDrawable(R.drawable.specialsicon);
                //   drawable.setBounds(0, 0, 100, 100);
                //   ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
                //   sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                return sb;

            }

            if (position == 10) {

                SpannableStringBuilder sb = new SpannableStringBuilder(" " + tabTitles[position]); // space added before text for convenience
                //   Drawable drawable = getDrawable(R.drawable.specialsicon);
                //   drawable.setBounds(0, 0, 100, 100);
                //   ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
                //   sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

                return sb;

            }

            return null;
        }


    }


}

