package comapps.com.lakewoodsmokehouse.menu;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import comapps.com.lakewoodsmokehouse.R;


class MenuListViewAdapter extends BaseAdapter {


    private final Context context;
    private final List<MenuObject> menuObject;



    public MenuListViewAdapter(Context context, List<MenuObject> menuObject) {

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
            view = inflater.inflate(R.layout.menulistrow, parent, false);

            holder = new ViewHolder();
            holder.item = (TextView) view.findViewById(R.id.itemTxt);
            holder.price = (TextView) view.findViewById(R.id.priceTxt);
            holder.description = (TextView) view.findViewById(R.id.descriptionTxt);
            holder.dot = (ImageView) view.findViewById(R.id.dotSeparator);






            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // S
        //
        MenuObject object = menuObject.get(position);

        holder.item.setText(object.getItem());
        holder.price.setText(object.getPrice());
        holder.description.setText(object.getDescription());





        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/MerriweatherSans-Light.ttf");
        holder.item.setTypeface(font);
        holder.price.setTypeface(font);
        holder.description.setTypeface(font);

        String checkForNull = object.getItem();
        if ( checkForNull == null ) {
            holder.item.setVisibility(View.GONE);
        } else if (checkForNull.equalsIgnoreCase("")){
            holder.item.setVisibility(View.GONE);
        } else {
            holder.item.setVisibility(View.VISIBLE);
        }

        checkForNull = object.getPrice();
        if ( checkForNull == null ) {
            holder.price.setVisibility(View.GONE);
        } else if (checkForNull.equalsIgnoreCase("")){
            holder.price.setVisibility(View.GONE);
        } else {
            holder.price.setVisibility(View.VISIBLE);
        }

        checkForNull = object.getDescription();
        if ( checkForNull == null ) {
            holder.description.setVisibility(View.GONE);
        } else if (checkForNull.equalsIgnoreCase("")){
            holder.description.setVisibility(View.GONE);
        } else {
            holder.description.setVisibility(View.VISIBLE);
        }



         String text = object.getGroup();


       switch (text) {
            case ("KIDS"):
                holder.price.setVisibility(View.GONE);

                break;
            case ("SIDES"):
                holder.price.setVisibility(View.GONE);
                break;


            default:
                Log.v("Not KIDS or SIDES", text);



        }



        if ( position == (getCount() - 1)) {
            holder.dot.setVisibility(View.GONE);
        }


        return view;


    }


    public class ViewHolder {
        TextView item;
        TextView price;
        TextView description;
        ImageView dot;
        CheckBox chk;



    }


}