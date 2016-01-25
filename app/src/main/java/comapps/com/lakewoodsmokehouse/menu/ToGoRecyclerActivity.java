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

    private List<MenuListObject> menuObjectList = new ArrayList<MenuListObject>();
    private RecyclerView mRecyclerView;
    private ToGoRecyclerAdapter adapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_togomenulist);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);








        updateList();





    }


    public void updateList() {


        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ToGoRecyclerActivity.this);
        mRecyclerView.setLayoutManager(linearLayoutManager);


        adapter = new ToGoRecyclerAdapter(ToGoRecyclerActivity.this, menuObjectList);
        mRecyclerView.setAdapter(adapter);
        adapter.clearAdapter();



            List<ParseObject> ob;


            try {


                // Locate the class table named "stansdata" in Parse.com
                ParseQuery<ParseObject> query = new ParseQuery<>(
                        "ls_menu").fromLocalDatastore();
                // Locate the column named "name" in Parse.com and order list
                // by ascending



                query.orderByAscending("groupsort").addAscendingOrder("sort");


                ob = query.find();



                menuObjectList = new ArrayList<>();

                for (ParseObject menu : ob) {
                    // Locate images in flag column


                    MenuListObject menuItem = new MenuListObject();

                    if (menu.get("item") != null && !menu.get("item").equals("") && !menu.get("item").equals("null")) {
                        String tempItem = (String) menu.get("item");
                        Log.i("tempItem is ", tempItem);
                        menuItem.setItem(tempItem);
                    }

                    if (menu.get("group") != null && !menu.get("group").equals("") && !menu.get("group").equals("null"))  {
                        String tempGroup = (String) menu.get("group");
                        Log.i("tempGroup is ", tempGroup);
                        menuItem.setGroup(tempGroup);
                    }

                    if (menu.get("price") != null && !menu.get("price").equals("") && !menu.get("price").equals("null")) {
                        String tempPrice = (String) menu.get("price");
                        Log.i("tempPrice is ", tempPrice);
                        menuItem.setPrice(tempPrice);
                    }

                    menuItem.setQuantity(0);

                    menuObjectList.add(menuItem);
                }


            } catch (ParseException e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

        }


    }




