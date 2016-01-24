package comapps.com.lakewoodsmokehouse.menu;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
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



            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }


        MenuListObject object = menuObject.get(position);

        holder.item.setText(object.getItem());
        holder.price.setText(object.getPrice());

        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/MerriweatherSans-Light.ttf");
        holder.item.setTypeface(font);
        holder.price.setTypeface(font);


        String checkForNull = object.getItem();
        if ( checkForNull == null ) {
            holder.item.setVisibility(View.GONE);

            holder.price.setVisibility(View.GONE);

        } else if (checkForNull.equalsIgnoreCase("")){
            holder.item.setVisibility(View.GONE);

            holder.price.setVisibility(View.GONE);

        } else {
            holder.item.setVisibility(View.VISIBLE);

            holder.price.setVisibility(View.VISIBLE);

        }



        return view;


    }


    public class ViewHolder {
        TextView item;
        TextView price;
        Spinner spinner;






    }


}