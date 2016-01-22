package comapps.com.lakewoodsmokehouse.drinks;

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
public class DrinksViewPager extends AppCompatActivity implements SensorEventListener {


    private String idOfSendingActivity = "";
    private final String drinks2 = "drinks2";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_viewpager_drinks);

        SensorManager SM = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor mySensor = SM.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SM.registerListener(this, mySensor, SensorManager.SENSOR_DELAY_NORMAL);



        idOfSendingActivity = getIntent().getStringExtra("activityId");


        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        PagerTitleStripV22 pts = (PagerTitleStripV22) findViewById(R.id.title);
        pts.setTextSize(TypedValue.COMPLEX_UNIT_SP, 24);
        pts.setTextColor(Color.parseColor("#e00025"));

        FragmentManager fragmentManager = getSupportFragmentManager();
        viewPager.setAdapter(new MyAdapter(fragmentManager));




        Typeface tf = Typeface.createFromAsset(this.getAssets(),
                "fonts/MerriweatherSans-Regular.ttf");


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


    //    Log.d("Sensor value Y is ", String.valueOf(event.values[1]));
    //    Log.d("Sensor value Z is ", String.valueOf(event.values[2]));

        if (event.values[2] > 10) {

     //       Log.d("Sensor value X is ", String.valueOf(event.values[0]));

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

                System.out.println("Size of drinkGroups is " + getDrinkGroups().size());
                Log.d("Sending activity is", idOfSendingActivity);

                if ( idOfSendingActivity.equalsIgnoreCase(drinks2)) {

                    return DrinksListViewFragment.newInstance(3, idOfSendingActivity);

                } else {

                    return DrinksListViewFragment.newInstance(position + 1, idOfSendingActivity);

                }

        }

        @Override
        public int getCount() {


            ArrayList<String> drinkGroups = getDrinkGroups();

            if ( idOfSendingActivity.equalsIgnoreCase(drinks2)) {

                return 1;

            } else {

                return drinkGroups.size();

            }


        }


        @Override
        public CharSequence getPageTitle(int position) {

            ArrayList<String> drinkGroups = getDrinkGroups();

         //   System.out.println("Size of drinkGroups is " + drinkGroups.size());

                for (int i = 0; i < drinkGroups.size(); i++) {
                    Log.v("Array Value", "Array Value " + drinkGroups.get(i));

                    if (position == i) {

                        //   drinkGroups.set(i, drinkGroups.get(i).replace("IMPORTED BOMBERS", "JIHADIS"));
                        //   drinkGroups.set(i, drinkGroups.get(i).replace("DOMESTIC BOMBERS", "MCVEIGHS"));

                     //   Log.v("DrinkGroupName", "Drink group name is " + drinkGroups.get(i));

                        if ( idOfSendingActivity.equalsIgnoreCase(drinks2)) {

                            return new SpannableStringBuilder(" COCKTAILS");

                        } else {

                            return new SpannableStringBuilder(" " + drinkGroups.get(i));
                        }



                    }
                }


                return null;
            }
        }


    private ArrayList<String> getDrinkGroups()    {

        List<ParseObject> ob;

        ArrayList<String> drinkGroups = new ArrayList<>();



        try {

            ParseQuery<ParseObject> query = new ParseQuery<>(
                    "ls_groups").fromLocalDatastore();


         //   Log.d("Sending activity in DG ", idOfSendingActivity);




                query.orderByAscending("sort").whereEqualTo("type", "DRINK");


            ob = query.find();


            for (ParseObject drink : ob) {

                drinkGroups.add((String) drink.get("group"));

            //    System.out.println("Size of drinkGroups after add is " + drinkGroups.size());

            //    Log.v("DrinkGroup", "Drink group is " + drink.get("group"));

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

