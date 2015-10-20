package comapps.com.theblindbutcherdallas.menu;


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
public class MenuListViewThingsOnTheSideFragment extends ListFragment {


    private List<MenuListObject> menuObject;
    MenuListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.menulistfragment, null, false);
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        List<ParseObject> ob;

        try {
            // Locate the class table named "stansbeers" in Parse.com
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                    "theblindbutchermenu").fromLocalDatastore();
            // Locate the column named "name" in Parse.com and order list
            // by ascending

            query.orderByAscending("sort").whereEqualTo("group", "THINGSONTHESIDE");


            ob = query.find();



            menuObject = new ArrayList<>();

            for (ParseObject menu : ob) {
                // Locate images in flag column


                MenuListObject menuItem = new MenuListObject();
                menuItem.setItem((String) menu.get("item"));
                menuItem.setPrice((String) menu.get("price"));
                menuItem.setGroup((String) menu.get("group"));
                menuItem.setDescription((String) menu.get("description"));
                menuObject.add(menuItem);
            }


        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        adapter = new MenuListViewAdapter(getActivity(), menuObject);
        setListAdapter(adapter);


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setDivider(new ColorDrawable(Color.BLACK));
        getListView().setDividerHeight(5);
    }
}



