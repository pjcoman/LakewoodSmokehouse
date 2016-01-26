package comapps.com.lakewoodsmokehouse.menu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import comapps.com.lakewoodsmokehouse.R;

/**
 * Created by me on 1/25/2016.
 */
public class ToGoRecyclerAdapter extends RecyclerView.Adapter<ToGoRecyclerAdapter.ViewHolder> {

    private List<MenuListObject> menuListObject;
    public ToGoRecyclerAdapter(List<MenuListObject> menuListObjects) {
        menuListObject = menuListObjects;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView itemTV;
        public TextView groupTV;
        public TextView priceTV;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            itemTV = (TextView) itemView.findViewById(R.id.itemTxt);
            groupTV = (TextView) itemView.findViewById(R.id.groupTxt);
            priceTV = (TextView) itemView.findViewById(R.id.priceTxt);
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
    public void onBindViewHolder(ToGoRecyclerAdapter.ViewHolder holder, int position) {

        MenuListObject menuObject = menuListObject.get(position);
        TextView textView = holder.itemTV;
        textView.setText(menuObject.getItem());
        textView = holder.groupTV;
        textView.setText(menuObject.getGroup());
        textView = holder.priceTV;
        textView.setText(menuObject.getPrice());

    }

    @Override
    public int getItemCount() {
        return menuListObject.size();
    }
}
