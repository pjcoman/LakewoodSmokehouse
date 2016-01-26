package comapps.com.lakewoodsmokehouse.menu;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import comapps.com.lakewoodsmokehouse.R;


public class ToGoListViewActivity extends AppCompatActivity {

    private List<MenuListObject> menuObjectList;
    private ListView lv;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_togomenulist);


        lv = (ListView) findViewById(android.R.id.list);





        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String menuitem = menuObjectList.get(position).getItem();
                String qty = menuObjectList.get(position).getQuantity().toString();


                Toast sort = Toast.makeText(ToGoListViewActivity.this, menuitem + " (" + qty + ")", Toast.LENGTH_SHORT);
                sort.setGravity(Gravity.CENTER, 0, 0);
                sort.show();


            }
        });


        new RemoteDataTask().execute();


    }


    class RemoteDataTask extends AsyncTask<Void, Void, Void> {



        @Override
        protected Void doInBackground(Void... params) {

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
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Locate the listview in listview_main.xml
            lv = (ListView) findViewById(android.R.id.list);
            // Pass the results into ParseListViewAdapter.java
            ToGoListViewAdapter adapter = new ToGoListViewAdapter(ToGoListViewActivity.this, menuObjectList);
            lv.setAdapter(adapter);


        }
    }
}



