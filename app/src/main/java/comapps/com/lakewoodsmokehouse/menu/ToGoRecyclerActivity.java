package comapps.com.lakewoodsmokehouse.menu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import comapps.com.lakewoodsmokehouse.R;


public class ToGoRecyclerActivity extends AppCompatActivity {

    public static final String TAG = "LakewoodSmokehouse";

    public List<MenuListObject> menuObjectList;
    public RecyclerView mRecyclerView;
    public ToGoRecyclerAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.togolist_recyclerview);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        adapter = new ToGoRecyclerAdapter(getData());

        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    public static List<MenuListObject> getData() {

        List<MenuListObject> menuObjectList = new ArrayList<>();

        try {

            ParseQuery<ParseObject> query = new ParseQuery<>(
                    "ls_menu").fromLocalDatastore();

            query.orderByAscending("groupsort").addAscendingOrder("sort");

            List<ParseObject> ob;

            ob = query.find();

            menuObjectList = new ArrayList<>();

            for (ParseObject menu : ob) {

                String tempItem = (String) menu.get("item");
                String tempGroup = (String) menu.get("group");
                String tempPrice = (String) menu.get("price");


                MenuListObject menuItem = new MenuListObject();

                if (menu.get("item") != null && !menu.get("item").equals("") && !menu.get("item").equals("null")) {
                    Log.i("tempItem is ", tempItem);
                    menuItem.setItem(tempItem);
                }

                if (menu.get("group") != null && !menu.get("group").equals("") && !menu.get("group").equals("null")) {
                    Log.i("tempGroup is ", tempGroup);
                    menuItem.setGroup(tempGroup);
                }

                if (menu.get("price") != null && !menu.get("price").equals("") && !menu.get("price").equals("null")) {
                    Log.i("tempPrice is ", tempPrice);
                    menuItem.setPrice(tempPrice);
                }

                menuItem.setQuantity(0);

                if (tempItem != null) { menuObjectList.add(menuItem); }
                
                }


        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        return menuObjectList;

    }
}