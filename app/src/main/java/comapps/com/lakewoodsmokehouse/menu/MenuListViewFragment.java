package comapps.com.lakewoodsmokehouse.menu;


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
public class MenuListViewFragment extends ListFragment {

    private static final String ARG_PAGE_NUMBER = "page_number";
    private List<MenuObject> menuObject;
    int x = 0;

    public MenuListViewFragment() {

    }

    public static MenuListViewFragment newInstance(int page) {
        MenuListViewFragment fragment = new MenuListViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE_NUMBER, page);
        fragment.setArguments(args);


        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        return inflater.inflate(R.layout.menulistfragment, null, false);



    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        int groupId = getArguments().getInt(ARG_PAGE_NUMBER, 1);

        List<ParseObject> ob;

        try {

            ParseQuery<ParseObject> query = new ParseQuery<>(
                    "ls_menu").fromLocalDatastore();
            // Locate the column named "name" in Parse.com and order list
            // by ascending



            query.orderByAscending("sort").whereEqualTo("groupsort", groupId);


            ob = query.find();



            menuObject = new ArrayList<>();

            for (ParseObject menu : ob) {
                // Locate images in flag column


                MenuObject menuItem = new MenuObject();

                String tempItem = (String) menu.get("item");
                if ( tempItem != null ) { tempItem.trim();}
                menuItem.setItem(tempItem);

                String tempPrice = (String) menu.get("price");
                if ( tempPrice != null ) { tempPrice.trim();}
                menuItem.setPrice(tempPrice);

                String tempGroup = (String) menu.get("group");
                if ( tempGroup != null ) { tempGroup.trim();}
                menuItem.setGroup(tempGroup);

                String tempDesc = (String) menu.get("description");
                if ( tempDesc != null ) { tempDesc.trim();}
                menuItem.setDescription(tempDesc);

               // menuItem.setPrice((String) menu.get("price"));
               // menuItem.setGroup((String) menu.get("group"));
               // menuItem.setDescription((String) menu.get("description"));
                menuObject.add(menuItem);
            }


        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        MenuListViewAdapter adapter = new MenuListViewAdapter(getActivity(), menuObject);
        setListAdapter(adapter);


    }




    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setDivider(new ColorDrawable(Color.BLACK));
        getListView().setDividerHeight(0);
    }


}



