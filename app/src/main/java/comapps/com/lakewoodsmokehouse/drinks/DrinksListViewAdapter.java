package comapps.com.lakewoodsmokehouse.drinks;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
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
            holder.drinkInfo = (TextView) view.findViewById(R.id.infoTxt);

            holder.abvlayout = (LinearLayout) view.findViewById(R.id.abvLayout);
                holder.abvlabel = (TextView) view.findViewById(R.id.abvTxtlabel);
                holder.drinkabv = (TextView) view.findViewById(R.id.abvTxt);
                holder.ibulabel = (TextView) view.findViewById(R.id.IBUTxtlabel);
                holder.drinkibu = (TextView) view.findViewById(R.id.IBUTxt);

            holder.drinkprice = (TextView) view.findViewById(R.id.priceTxt);

            holder.dot = (ImageView) view.findViewById(R.id.dotSeparator);



            //holder.beerimage = (ImageView) view.findViewById(R.id.beerimage);

            view.setTag(holder);

        } else {

            holder = (ViewHolder) view.getTag();

        }


        // Set the results into TextViews
        DrinkListObject object = drinkObject.get(position);

        String tempName;
        String tempABV;
        String tempIBUString;
        String tempInfo;
        String tempPrice;


        tempName = object.getDrinkName();
        if ( tempName == null ) {
            tempName = "";
        } else {
            tempName = (object.getDrinkName());
        }

        tempABV = object.getDrinkAbv().toString();
        Log.d("tempABV is ", tempABV);

        if ( tempABV == "0.0" ) {
            tempABV = "?";
        } else {
            tempABV = (object.getDrinkAbv().toString());
        }


        if ( object.getDrinkIBU() == null ) {
            tempIBUString = "N/A";
        } else {
            tempIBUString = (object.getDrinkIBU().toString());
        }

        tempInfo = object.getDrinkInfo();
        if ( tempInfo == null ) {
            tempInfo = "";
        } else {
            tempInfo = (object.getDrinkInfo());
        }

        tempPrice = object.getDrinkPrice();
        if ( tempPrice == null ) {
            tempPrice = "";
        } else {
            tempPrice = (object.getDrinkPrice());
        }





        if ( tempName.equals("")) {
            holder.drinkname.setVisibility(View.GONE);
        } else {
            holder.drinkname.setVisibility(View.VISIBLE);
        }

        if ( tempInfo.equals("")) {
            holder.drinkInfo.setVisibility(View.GONE);
        } else {
            holder.drinkInfo.setVisibility(View.VISIBLE);
        }

        if ( tempABV.equals("")) {
            holder.drinkabv.setVisibility(View.GONE);
        } else {
            holder.drinkabv.setVisibility(View.VISIBLE);
        }

        if ( tempIBUString.equals("")) {
            holder.drinkibu.setVisibility(View.GONE);
        } else {
            holder.drinkibu.setVisibility(View.VISIBLE);
        }

        if ( tempPrice.equals("")) {
            holder.drinkprice.setVisibility(View.GONE);
        } else {
            holder.drinkprice.setVisibility(View.VISIBLE);
        }

        if ( object.getDrinkGroup().equals("COCKTAILS")) {
            holder.abvlayout.setVisibility(View.GONE);

        } else if ( object.getDrinkGroup().equals("WINES")){
            holder.ibulabel.setVisibility(View.GONE);
            holder.drinkibu.setVisibility(View.GONE);
        }
        else {
            holder.ibulabel.setVisibility(View.VISIBLE);
            holder.drinkibu.setVisibility(View.VISIBLE);
            holder.abvlayout.setVisibility(View.VISIBLE);
        }




        holder.drinkname.setText(tempName);
        holder.drinkInfo.setText(tempInfo);
        holder.drinkabv.setText(tempABV);
        holder.drinkibu.setText(tempIBUString);
        holder.drinkprice.setText(tempPrice);




        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/MerriweatherSans-Light.ttf");
        holder.drinkname.setTypeface(font);
        holder.drinkabv.setTypeface(font);
        holder.drinkprice.setTypeface(font);
        holder.abvlabel.setTypeface(font);
        holder.drinkibu.setTypeface(font);
        holder.ibulabel.setTypeface(font);
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
        TextView drinkibu;
        TextView ibulabel;
        TextView drinkInfo;
        LinearLayout abvlayout;
        ImageView dot;
    }


}