package comapps.com.lakewoodsmokehouse.menu;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import comapps.com.lakewoodsmokehouse.R;


class ToGoListViewAdapter extends BaseAdapter {


    private final Context context;
    private final List<MenuListObject> menuObject;

    public ToGoListViewAdapter(Context context, List<MenuListObject> menuObject) {

        this.context = context;
        this.menuObject = menuObject;

    }


    @Override
    public int getCount() {
        return menuObject.size();
    }

    @Override
    public Object getItem(int position) {
        return menuObject.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;




        if (view == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.togo_row, parent, false);

            holder = new ViewHolder();
            holder.item = (TextView) view.findViewById(R.id.itemTxt);
            holder.price = (TextView) view.findViewById(R.id.priceTxt);
         //   holder.spinner = (Spinner) view.findViewById(R.id.spinner);
            holder.group = (TextView) view.findViewById(R.id.groupTxt);
            holder.ll = (LinearLayout) view.findViewById(R.id.linearlayouttogorow);
            holder.buttonminus = (Button) view.findViewById(R.id.buttonminus);
            holder.buttonplus = (Button) view.findViewById(R.id.buttonplus);
            holder.quantity = (TextView) view.findViewById(R.id.quantityTV);

            holder.buttonplus.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    //  Use position parameter of your getView() in this method it will current position of Clicked row button
                    // code for current Row deleted...
                    MenuListObject object = menuObject.get(position);
                    Integer q = object.getQuantity();

                // Integer q = Integer.valueOf(String.valueOf(holder.quantity.getText().toString()));

                    Log.i("Counter is ", Integer.toString(q));



                    Log.i("Counter is ", Integer.toString(q));

                    q++;

                    Log.i("Counter is after q++ ", Integer.toString(q));

                   // holder.quantity.setText(Integer.toString(q));

                    object.setQuantity(q);

                    holder.quantity.setText(object.getQuantity().toString());


                    Log.i("Counter after setText ", holder.quantity.getText().toString());

                  

                }
            });

            holder.buttonminus.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                    MenuListObject object = menuObject.get(position);
                    Integer q = object.getQuantity();

                    // Integer q = Integer.valueOf(String.valueOf(holder.quantity.getText().toString()));

                    Log.i("-Counter is ", Integer.toString(q));



                    Log.i("-Counter is ", Integer.toString(q));

                    q--;

                    Log.i("-Counter is after q++ ", Integer.toString(q));

                    // holder.quantity.setText(Integer.toString(q));

                    object.setQuantity(q);

                    if ( object.getQuantity() < 0 ) { object.setQuantity(0); }

                    holder.quantity.setText(object.getQuantity().toString());


                    Log.i("-Counter after setText ", holder.quantity.getText().toString());


                }
            });




            view.setTag(holder);

        } else {

            holder = (ViewHolder) view.getTag();

        }


        MenuListObject object = menuObject.get(position);

        holder.item.setText(object.getItem());
        holder.price.setText(object.getPrice());
        holder.group.setText(object.getGroup());
    //    holder.quantity.setText(object.getQuantity().toString());

        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/MerriweatherSans-Light.ttf");
        holder.item.setTypeface(font);
        holder.price.setTypeface(font);
        holder.group.setTypeface(font);
        holder.quantity.setTypeface(font);


        String checkForNull = object.getItem();
        if ( checkForNull == null ) {
            holder.item.setVisibility(View.GONE);
            holder.price.setVisibility(View.GONE);
            holder.group.setVisibility(View.GONE);
            holder.ll.setVisibility(View.GONE);
            holder.quantity.setVisibility(View.GONE);
    //        holder.spinner.setVisibility(View.GONE);
        } else if (checkForNull.equalsIgnoreCase("")){
            holder.item.setVisibility(View.GONE);
            holder.price.setVisibility(View.GONE);
            holder.group.setVisibility(View.GONE);
            holder.ll.setVisibility(View.GONE);
            holder.quantity.setVisibility(View.GONE);
    //        holder.spinner.setVisibility(View.GONE);
        } else {
            holder.item.setVisibility(View.VISIBLE);
            holder.price.setVisibility(View.VISIBLE);
            holder.group.setVisibility(View.VISIBLE);
            holder.ll.setVisibility(View.VISIBLE);
            holder.quantity.setVisibility(View.VISIBLE);
    //        holder.spinner.setVisibility(View.VISIBLE);


        }



        return view;


    }


    static class ViewHolder {
        public TextView item;
        public TextView price;
        public TextView group;
        public Spinner spinner;
        public LinearLayout ll;
        public Button buttonplus;
        public Button buttonminus;
        public TextView quantity;






    }


}