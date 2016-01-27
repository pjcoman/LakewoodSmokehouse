package comapps.com.lakewoodsmokehouse.menu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import comapps.com.lakewoodsmokehouse.R;

/**
 * Created by me on 1/25/2016.
 */
public class ToGoRecyclerAdapter extends RecyclerView.Adapter<ToGoRecyclerAdapter.ViewHolder> implements Filterable {

    private List<MenuObject> menuListObject;
    private Context mContext;
    private ArrayAdapter<String> meats;



    public ToGoRecyclerAdapter(List<MenuObject> menuListObjects) {
        menuListObject = menuListObjects;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView itemTV;
        public TextView groupTV;
        public TextView priceTV;
        public TextView quantityTV;
        public Button buttonMinus;
        public Button buttonPlus;
        public AutoCompleteTextView selection;
        public AutoCompleteTextView selection2;
        public AutoCompleteTextView selection3;




        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            itemTV = (TextView) itemView.findViewById(R.id.itemTxt);
            groupTV = (TextView) itemView.findViewById(R.id.groupTxt);
            priceTV = (TextView) itemView.findViewById(R.id.priceTxt);
            quantityTV = (TextView) itemView.findViewById(R.id.quantityTV);
            buttonMinus = (Button) itemView.findViewById(R.id.buttonminus);
            buttonPlus = (Button) itemView.findViewById(R.id.buttonplus);
            selection = (AutoCompleteTextView) itemView.findViewById(R.id.autoCompleteTextView);
            selection2 = (AutoCompleteTextView) itemView.findViewById(R.id.autoCompleteTextView2);
            selection3 = (AutoCompleteTextView) itemView.findViewById(R.id.autoCompleteTextView3);


        }
    }


    @Override
    public ToGoRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.togo_row, parent, false);
        RecyclerView.ViewHolder viewHolder = new ViewHolder(itemView);


        return (ViewHolder) viewHolder;
    }

    @Override
    public void onBindViewHolder(final ToGoRecyclerAdapter.ViewHolder holder, final int position) {



        final MenuObject tempMenuObject = menuListObject.get(position);

        TextView textView = holder.itemTV;
        textView.setText(tempMenuObject.getItem());
        textView = holder.groupTV;
        textView.setText(tempMenuObject.getGroup());
        textView = holder.priceTV;
        textView.setText(tempMenuObject.getPrice());

        Log.i("item is ", tempMenuObject.getItem().toString());


           if ( tempMenuObject.getItem().toString().contains("ONE MEAT")) {
               holder.selection.setVisibility(View.VISIBLE);
           } else if ( tempMenuObject.getItem().toString().contains("TWO MEAT")) {
                    holder.selection.setVisibility(View.VISIBLE);
                    holder.selection2.setVisibility(View.VISIBLE);
           } else if ( tempMenuObject.getItem().toString().contains("THREE MEAT")) {
                        holder.selection.setVisibility(View.VISIBLE);
                        holder.selection2.setVisibility(View.VISIBLE);
                        holder.selection3.setVisibility(View.VISIBLE);
           } else {
                            holder.selection.setVisibility(View.GONE);
                            holder.selection2.setVisibility(View.GONE);
                            holder.selection3.setVisibility(View.GONE);
           }


            holder.buttonPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer quantity = tempMenuObject.getQuantity();
                    quantity++;
                //    tempMenuObject.setQuantity(quantity);
                    menuListObject.get(position).setQuantity(quantity);
                    holder.quantityTV.setText(quantity.toString());
                    notifyDataSetChanged();

                    Log.i("position, quantity  ", String.valueOf(position) + ", " + String.valueOf(quantity));

                }
            });

            holder.buttonMinus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer quantity = tempMenuObject.getQuantity();
                    quantity--;
                    if (quantity < 0) {
                        quantity = 0;
                    }
                //    tempMenuObject.setQuantity(quantity);
                    menuListObject.get(position).setQuantity(quantity);
                    holder.quantityTV.setText(quantity.toString());
                    notifyDataSetChanged();

                    Log.i("position, quantity  ", String.valueOf(position) + ", " + String.valueOf(quantity));

                }
            });


            holder.quantityTV.setText(tempMenuObject.getQuantity().toString());






        }


        @Override
        public int getItemCount () {
            return menuListObject.size();
        }

    public void setFilter(List<MenuObject> menuListObjects) {
        menuListObject = new ArrayList<>();
        menuListObject.addAll(menuListObject);

        notifyDataSetChanged();
    }

    private List<MenuObject> filter(List<MenuObject> menuObject, String query) {
        query = "0";

        final List<MenuObject> filteredMenuList = new ArrayList<>();
        for (MenuObject menuOject : menuObject) {
            final String text = menuOject.getQuantity().toString();
            if (text.contentEquals(query)) {

            } else {
                filteredMenuList.add(menuOject);
            }
        }
        return filteredMenuList  ;
    }







}
