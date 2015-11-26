package comapps.com.theblindbutcherdallas.menu;

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
public class MenuViewPager extends AppCompatActivity {


    ViewPager viewPager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main_viewpager_menu);

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
            return MenuListViewFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {

            List<ParseObject> ob;

            ArrayList<String> menuGroups = getMenuGroups();

            return menuGroups.size();
        }


        @Override
        public CharSequence getPageTitle(int position) {


            ArrayList<String> menuGroups = getMenuGroups();


            for (int i = 0; i < menuGroups.size(); i++) {
                Log.v("Array Value", "Array Value" + menuGroups.get(i));

                if (position == i) {

                    SpannableStringBuilder sb = new SpannableStringBuilder(" " + menuGroups.get(i)); // space added before text for convenience
                    return sb;
                }
            }
            return null;
        }
    }

    public static ArrayList<String> getMenuGroups()    {

        List<ParseObject> ob;

        ArrayList<String> menuGroups = new ArrayList<>();

        try {
            // Locate the class table named "stansbeers" in Parse.com
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                    "theblindbutchermenugroups").fromLocalDatastore();
            query.orderByAscending("sort");
            ob = query.find();


            for (ParseObject menu : ob) {
                // Locate images in flag column

                menuGroups.add((String) menu.get("group"));

            }

        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return(menuGroups);
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pushinfromright, R.anim.pushouttoleft);
    }


}

