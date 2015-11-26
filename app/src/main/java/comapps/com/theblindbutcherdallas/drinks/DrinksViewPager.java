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

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

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
    }

    class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return DrinksListViewFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {


            ArrayList<String> drinkGroups = getDrinkGroups();

            return drinkGroups.size();
        }



        @Override
        public CharSequence getPageTitle(int position) {

            ArrayList<String> drinkGroups = getDrinkGroups();



            for (int i = 0; i < drinkGroups.size(); i++) {
                Log.v("Array Value", "Array Value" + drinkGroups.get(i));

                if (position == i) {

                    SpannableStringBuilder sb = new SpannableStringBuilder(" " + drinkGroups.get(i)); // space added before text for convenience
                    return sb;
                }
            }
            return null;
        }



    }

    public static ArrayList<String> getDrinkGroups()    {

        List<ParseObject> ob;

        ArrayList<String> drinkGroups = new ArrayList<>();

        try {
            // Locate the class table named "stansbeers" in Parse.com
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                    "theblindbutcherdrinkgroups").fromLocalDatastore();
            query.orderByAscending("sort");
            ob = query.find();


            for (ParseObject drink : ob) {
                // Locate images in flag column

                drinkGroups.add((String) drink.get("group"));

            }

        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return(drinkGroups);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pushinfromleft, R.anim.pushouttoright);
    }


}

