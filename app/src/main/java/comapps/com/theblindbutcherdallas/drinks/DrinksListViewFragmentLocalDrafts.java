package comapps.com.theblindbutcherdallas.drinks;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import comapps.com.theblindbutcherdallas.R;


/**
 * Created by me on 9/29/2015.
 */
public class DrinksListViewFragmentLocalDrafts extends ListFragment {

    int x = 0;
    private List<DrinkListObject> drinkObject;
    DrinksListViewAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.drinklistfragment, null, false);

    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        List<ParseObject> ob;

        try {
            // Locate the class table named "stansbeers" in Parse.com
            ParseQuery<ParseObject> query = new ParseQuery<>(
                    "theblindbutcherdrinks");
            // Locate the column named "name" in Parse.com and order list
            // by ascending


            query.orderByAscending("sort").whereEqualTo("group", "LOCALDRAFTS");

            ob = query.find();



            drinkObject = new ArrayList<>();

            for (ParseObject drinks : ob) {
                // Locate images in flag column

                //  ParseFile image = (ParseFile) stansbeers.get("image");

                DrinkListObject drink = new DrinkListObject();
                drink.setDrinkName((String) drinks.get("item"));
                drink.setDrinkAbv(drinks.getDouble("abv"));
                drink.setDrinkGroup((String) drinks.get("group"));
                drink.setDrinkPrice((String) drinks.get("price"));
                drinkObject.add(drink);
            }

        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        adapter = new DrinksListViewAdapter(getActivity(), drinkObject);
        setListAdapter(adapter);


    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setDivider(new ColorDrawable(Color.BLACK));
        getListView().setDividerHeight(5);
    }


}






















