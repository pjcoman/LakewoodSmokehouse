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


class ToGoListViewAdapterNoConvert extends BaseAdapter {


    private final Context context;
    private final List<MenuListObject> menuObject;

    public ToGoListViewAdapterNoConvert(Context context, List<MenuListObject> menuObject) {

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {



            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.togo_row, parent, false);

            holder = new ViewHolder();
            holder.item = (TextView) convertView.findViewById(R.id.itemTxt);
            holder.price = (TextView) convertView.findViewById(R.id.priceTxt);
            holder.group = (TextView) convertView.findViewById(R.id.groupTxt);
            holder.ll = (LinearLayout) convertView.findViewById(R.id.linearlayouttogorow);
            holder.buttonminus = (Button) convertView.findViewById(R.id.buttonminus);
            holder.buttonplus = (Button) convertView.findViewById(R.id.buttonplus);
            holder.quantity = (TextView) convertView.findViewById(R.id.quantityTV);

            holder.buttonplus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MenuListObject object = menuObject.get(position);
                    Integer q = object.getQuantity();
                    q++;
                    object.setQuantity(q);
                    holder.quantity.setText(q.toString());





                }
            });

            holder.buttonminus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MenuListObject object = menuObject.get(position);
                    Integer q = object.getQuantity();

                    if ( q <= 0) {
                        q = 0; } else {
                        q--;

                        }

                    Log.i("-Counter is after q++ ", Integer.toString(q));
                    object.setQuantity(q);
                    holder.quantity.setText(q.toString());




                }
            });


            convertView.setTag(holder);

        } else {

            holder = (ViewHolder) convertView.getTag();

        }

        holder.quantity.setText(menuObject.get(position).getQuantity().toString());
        holder.position = position;

        MenuListObject object = menuObject.get(position);

        if (object != null) {

            holder.item.setText(object.getItem());
            holder.price.setText(object.getPrice());
            holder.group.setText(object.getGroup());
          //  holder.quantity.setText(object.getQuantity().toString());

            Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/MerriweatherSans-Light.ttf");
            holder.item.setTypeface(font);
            holder.price.setTypeface(font);
            holder.group.setTypeface(font);
            holder.quantity.setTypeface(font);


            String checkForNull = object.getItem();
            if (checkForNull == null) {
                holder.item.setVisibility(View.GONE);
                holder.price.setVisibility(View.GONE);
                holder.group.setVisibility(View.GONE);
                holder.ll.setVisibility(View.GONE);
                holder.quantity.setVisibility(View.GONE);
                //        holder.spinner.setVisibility(View.GONE);
            } else if (checkForNull.equalsIgnoreCase("")) {
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
        }


            return convertView;


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
        public int position;






    }



}