package comapps.com.lakewoodsmokehouse.drinks;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

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
    private static final String ARG_SENDING_ACTIVITY = "number_of_pages";
    int x = 0;
    int y = 0;


    private List<DrinkListObject> drinkObject;
    private DrinksListViewAdapter adapter;

    public DrinksListViewFragment() {

    }

    public static DrinksListViewFragment newInstance(int page, String sendingActivity) {
        DrinksListViewFragment fragment = new DrinksListViewFragment();

        String pageString = Integer.toString(page);
        System.out.println("DLVF position " + pageString);
        System.out.println("DLVF sending activity " + sendingActivity);

        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUMBER, page);
        args.putString(ARG_SENDING_ACTIVITY, sendingActivity);
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
                drink.setDrinkInfo((String) drinks.get("info"));
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
    public void onListItemClick(ListView l, View v, int position, long id) {

        x = x + 1;
        y = y + 1;

        int groupId = getArguments().getInt(ARG_PAGE_NUMBER, 1);
        String groupIdString = Integer.toString(groupId);
        System.out.println("Group ID ARG_PAGE_NUMBER is " + groupIdString);

        if ( groupId == 1) {

            y = 6;

        } else {

            y = 2;

        }

        if (x == y) {

            x = 0;

        }

        String[] toastSort = new String[8];
        toastSort[0] = "Sorted by name Ascending";
        toastSort[1] = "by name descending";
        toastSort[2] = "by abv (alcohol by volume) ascending";
        toastSort[3] = "by abv descending";
        toastSort[4] = "by IBU (bitterness) ascending";
        toastSort[5] = "by IBU descending";

        String tm = toastSort[x];

        Toast sort = Toast.makeText(getActivity(), tm, Toast.LENGTH_LONG);
        sort.setGravity(Gravity.CENTER, 0, 0);
        sort.show();

        List<ParseObject> ob;

        try {
            // Locate the class table named "stansbeers" in Parse.com
            ParseQuery<ParseObject> query = new ParseQuery<>(
                    "lw_smokehousedrinks").fromLocalDatastore();
            // Locate the column named "name" in Parse.com and order list
            // by ascending

            query.orderByAscending("sort").whereEqualTo("groupsort", groupId);

            switch (x) {
                case 0:
                    query.orderByAscending("item");
                    break;
                case 1:
                    query.orderByDescending("item");
                    break;
                case 2:
                    query.orderByAscending("abv");
                    break;
                case 3:
                    query.orderByDescending("abv");
                    break;
                case 4:
                    query.orderByAscending("IBU");
                    break;
                case 5:
                    query.orderByDescending("IBU");
                    break;
            }


            ob = query.find();



            drinkObject = new ArrayList<>();

            for (ParseObject drinks : ob) {


                DrinkListObject drink = new DrinkListObject();
                drink.setDrinkName((String) drinks.get("item"));
                drink.setDrinkAbv(drinks.getDouble("abv"));
                drink.setDrinkGroup((String) drinks.get("group"));
                drink.setDrinkPrice((String) drinks.get("price"));
                drink.setDrinkIBU((Integer) drinks.get("IBU"));
                drink.setDrinkInfo((String) drinks.get("info"));
                drinkObject.add(drink);
            }

        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        adapter = new DrinksListViewAdapter(getActivity(), drinkObject);
        setListAdapter(adapter);

        adapter.notifyDataSetChanged();


    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setDivider(new ColorDrawable(Color.BLACK));
        getListView().setDividerHeight(0);
    }


}






















