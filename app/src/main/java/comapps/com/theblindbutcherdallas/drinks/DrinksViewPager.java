package comapps.com.theblindbutcherdallas.drinks;

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

import comapps.com.theblindbutcherdallas.R;


/**
 * Created by me on 10/6/2015.
 */
public class DrinksViewPager extends AppCompatActivity implements SensorEventListener {


    private ViewPager viewPager = null;

    private Sensor mySensor;
    private SensorManager SM;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_viewpager_drinks);

        SM = (SensorManager)getSystemService(SENSOR_SERVICE);
        mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);



        String idOfSendingActivity = getIntent().getStringExtra("activityId");

        Log.d("Sending activty is", idOfSendingActivity);



        viewPager = (ViewPager) findViewById(R.id.pager);
        PagerTitleStripV22 pts = (PagerTitleStripV22) findViewById(R.id.title);
        pts.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
        pts.setTextColor(Color.parseColor("#000000"));

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


        Log.d("Sensor value Y is ", String.valueOf(event.values[1]));
        Log.d("Sensor value Z is ", String.valueOf(event.values[2]));

        if (event.values[2] > 10) {

            Log.d("Sensor value X is ", String.valueOf(event.values[0]));

            finish();

            overridePendingTransition(R.anim.pushinfromleft, R.anim.pushouttoright);

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // not using
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

                    drinkGroups.set(i, drinkGroups.get(i).replace("IMPORTED BOMBERS", "JIHADIS"));
                    drinkGroups.set(i, drinkGroups.get(i).replace("DOMESTIC BOMBERS", "MCVEIGHS"));

                    return new SpannableStringBuilder(" " + drinkGroups.get(i).toLowerCase());
                }
            }
            return null;
        }



    }

    private static ArrayList<String> getDrinkGroups()    {

        List<ParseObject> ob;

        ArrayList<String> drinkGroups = new ArrayList<>();

        try {
            // Locate the class table named "stansbeers" in Parse.com
            ParseQuery<ParseObject> query = new ParseQuery<>(
                    "theblindbutchergroups").fromLocalDatastore();
            query.orderByAscending("sort").whereEqualTo("type", "DRINK");
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

