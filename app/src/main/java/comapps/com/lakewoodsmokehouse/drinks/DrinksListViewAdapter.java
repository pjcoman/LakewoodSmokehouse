package comapps.com.lakewoodsmokehouse.drinks;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import comapps.com.lakewoodsmokehouse.R;


class DrinksListViewAdapter extends BaseAdapter {


    private final Context context;
    private final List<DrinkListObject> drinkObject;


    public DrinksListViewAdapter(Context context, List<DrinkListObject> drinkObject) {

        this.context = context;
        this.drinkObject = drinkObject;

    }


    @Override
    public int getCount() {
        return drinkObject.size();
    }

    @Override
    public Object getItem(int position) {
        return drinkObject.get(position);
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
            view = inflater.inflate(R.layout.drinkslistrow, parent, false);

            holder = new ViewHolder();

            holder.drinkname = (TextView) view.findViewById(R.id.drinkTxt);
            holder.abvlabel = (TextView) view.findViewById(R.id.abvTxtlabel);
            holder.drinkabv = (TextView) view.findViewById(R.id.abvTxt);
            holder.drinkprice = (TextView) view.findViewById(R.id.priceTxt);
            holder.abvlayout = (LinearLayout) view.findViewById(R.id.abvLayout);
            holder.drinkIBU = (TextView) view.findViewById(R.id.IBUTxt);
            holder.dot = (ImageView) view.findViewById(R.id.dotSeparator);
            holder.drinkIBUTV = (TextView) view.findViewById(R.id.IBUTxtlabel);
            holder.drinkInfo = (TextView) view.findViewById(R.id.infoTxt);

            //holder.beerimage = (ImageView) view.findViewById(R.id.beerimage);

            view.setTag(holder);

        } else {

            holder = (ViewHolder) view.getTag();

        }


        // Set the results into TextViews
        DrinkListObject object = drinkObject.get(position);

        String tempAbv = "";

        if ( object.getDrinkAbv() == null ) {
            tempAbv = "0.0";
        } else {
            tempAbv = (object.getDrinkAbv().toString());
        }

        if ( tempAbv == "0.0") {
            holder.abvlayout.setVisibility(View.GONE);
        } else {
            holder.abvlayout.setVisibility(View.VISIBLE);
        }

        String tempPrice = "";

        if ( object.getDrinkPrice() == null ) {
            tempPrice = " ";
        } else {
            tempPrice = (object.getDrinkPrice());
        }



        if ( tempPrice == " ") {
            holder.drinkprice.setVisibility(View.GONE);
        } else {
            holder.drinkprice.setVisibility(View.VISIBLE);
        }

        String tempIBU;


        if ( object.getDrinkIBU() == null ) {
     //       Log.i("LOGTAG", "IBU is null");
            tempIBU = "00";
        } else {
            tempIBU = (object.getDrinkIBU().toString());
        }

        if ( tempIBU == "00") {
            holder.drinkIBU.setVisibility(View.GONE);
            holder.drinkIBUTV.setVisibility(View.GONE);

        } else {
            holder.drinkIBU.setVisibility(View.VISIBLE);
            holder.drinkIBUTV.setVisibility(View.VISIBLE);
        }



        holder.drinkIBU.setText(tempIBU);
        holder.drinkname.setText(object.getDrinkName());
        holder.drinkabv.setText(tempAbv);
        holder.drinkprice.setText(tempPrice);
        holder.drinkInfo.setText(object.getDrinkInfo());



        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/MerriweatherSans-Light.ttf");
        holder.drinkname.setTypeface(font);
        holder.drinkabv.setTypeface(font);
        holder.drinkprice.setTypeface(font);
        holder.abvlabel.setTypeface(font);
        holder.drinkIBU.setTypeface(font);
        holder.drinkIBUTV.setTypeface(font);
        holder.drinkInfo.setTypeface(font);

      //  Picasso.with(context).load(object.getBeerImage()).resize(200, 400).into(holder.beerimage);

        if ( position == (getCount() - 1)) {
            holder.dot.setVisibility(View.INVISIBLE);
        }


        return view;


    }


    private static class ViewHolder {
        TextView drinkname;
        TextView drinkabv;
        TextView drinkprice;
        TextView abvlabel;
        TextView drinkIBU;
        TextView drinkIBUTV;
        TextView drinkInfo;
        LinearLayout abvlayout;
        ImageView dot;
    }


}