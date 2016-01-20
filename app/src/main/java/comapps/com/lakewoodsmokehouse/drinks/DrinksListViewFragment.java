package comapps.com.lakewoodsmokehouse.drinks;


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

import comapps.com.lakewoodsmokehouse.R;


/**
 * Created by me on 9/29/2015.
 */
public class DrinksListViewFragment extends ListFragment {

    private static final String ARG_PAGE_NUMBER = "page_number";

    private List<DrinkListObject> drinkObject;
    private DrinksListViewAdapter adapter;

    public DrinksListViewFragment() {

    }

    public static DrinksListViewFragment newInstance(int page) {
        DrinksListViewFragment fragment = new DrinksListViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUMBER, page);
        fragment.setArguments(args);


        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.drinklistfragment, null, false);

    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int groupId = getArguments().getInt(ARG_PAGE_NUMBER, 1);

        List<ParseObject> ob;

        try {

            ParseQuery<ParseObject> query = new ParseQuery<>(
                    "lw_smokehousedrinks").fromLocalDatastore();
            // Locate the column named "name" in Parse.com and order list
            // by ascending


            query.orderByAscending("sort").whereEqualTo("groupsort", groupId);

            ob = query.find();



            drinkObject = new ArrayList<>();

            for (ParseObject drinks : ob) {


                DrinkListObject drink = new DrinkListObject();
                drink.setDrinkName((String) drinks.get("item"));
                drink.setDrinkAbv(drinks.getDouble("abv"));
                drink.setDrinkGroup((String) drinks.get("group"));
                drink.setDrinkPrice((String) drinks.get("price"));
                drink.setDrinkIBU((Integer) drinks.get("IBU"));
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
        getListView().setDividerHeight(0);
    }


}






















