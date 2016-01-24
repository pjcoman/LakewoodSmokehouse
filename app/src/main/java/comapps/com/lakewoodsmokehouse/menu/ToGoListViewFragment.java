package comapps.com.lakewoodsmokehouse.menu;


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
import android.widget.TextView;
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
public class ToGoListViewFragment extends ListFragment {


    private List<MenuListObject> menuObject;


    public ToGoListViewFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.togolistfragment, container, false);
        return v;

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        String item = ((TextView) v.findViewById(R.id.itemTxt)).getText().toString();

        Toast sort = Toast.makeText(getActivity(), item, Toast.LENGTH_SHORT);
        sort.setGravity(Gravity.CENTER, 0, 0);
        sort.show();



    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



        List<ParseObject> ob;

        try {

            ParseQuery<ParseObject> query = new ParseQuery<>(
                    "ls_menu").fromLocalDatastore();
            // Locate the column named "name" in Parse.com and order list
            // by ascending



            query.orderByAscending("groupsort").addAscendingOrder("sort");


            ob = query.find();



            menuObject = new ArrayList<>();

            for (ParseObject menu : ob) {
                // Locate images in flag column


                MenuListObject menuItem = new MenuListObject();

                String tempItem = (String) menu.get("item");
                if ( tempItem != null ) {
                    tempItem.trim();
                    menuItem.setItem(tempItem);

                    System.out.println("tempItem if " + tempItem);

                } else {
                    System.out.println("tempItem else " + tempItem);
                }


                String tempPrice = (String) menu.get("price");
                if ( tempPrice != null ) { tempPrice.trim();}
                menuItem.setPrice(tempPrice);


                menuObject.add(menuItem);
            }


        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        ToGoListViewAdapter adapter = new ToGoListViewAdapter(getActivity(), menuObject);
        setListAdapter(adapter);


    }



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setDivider(new ColorDrawable(Color.BLACK));
        getListView().setDividerHeight(0);
    }


}



