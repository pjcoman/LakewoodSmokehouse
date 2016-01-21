package comapps.com.lakewoodsmokehouse.menu;

import android.graphics.Color;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
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

import comapps.com.lakewoodsmokehouse.R;


/**
 * Created by me on 10/6/2015.
 */
public class MenuViewPager extends AppCompatActivity implements SensorEventListener {


    private ViewPager viewPager = null;

    private Sensor mySensor;
    private SensorManager SM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main_viewpager_menu);

        SM = (SensorManager)getSystemService(SENSOR_SERVICE);
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);

        String idOfSendingActivity = getIntent().getStringExtra("activityId");

        Log.d("Sending activty is", idOfSendingActivity);

        viewPager = (ViewPager) findViewById(R.id.pager);


        PagerTitleStripV22 pts = (PagerTitleStripV22) findViewById(R.id.title);

        pts.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        pts.setTextColor(Color.parseColor("#FFFFFF"));

        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new MyAdapter(fragmentManager));


        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "fonts/MerriweatherSans-Italic.ttf");


        for (int i = 0; i < pts.getChildCount(); i++) {

            View nextChild = pts.getChildAt(i);
            if (nextChild instanceof TextView) {
                TextView textViewToConvert = (TextView) nextChild;
                textViewToConvert.setTypeface(tf);

            }
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

     //   Log.d("Sensor value Y is ", String.valueOf(event.values[1]));
     //   Log.d("Sensor value Z is ", String.valueOf(event.values[2]));

        if (event.values[2] > 10) {

     //       Log.d("Sensor value X is ", String.valueOf(event.values[0]));

            finish();

            overridePendingTransition(R.anim.pushinfromright, R.anim.pushouttoleft);

        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

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


                    return new SpannableStringBuilder(" " + menuGroups.get(i));
                }
            }
            return null;
        }
    }

    private static ArrayList<String> getMenuGroups()    {

        List<ParseObject> ob;

        ArrayList<String> menuGroups = new ArrayList<>();

        try {

            ParseQuery<ParseObject> query = new ParseQuery<>(
                    "ls_groups").fromLocalDatastore();
            query.orderByAscending("sort").whereEqualTo("type", "EATS");
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

