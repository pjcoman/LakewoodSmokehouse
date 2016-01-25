package comapps.com.lakewoodsmokehouse.menu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

import comapps.com.lakewoodsmokehouse.R;


class ToGoRecyclerAdapter extends RecyclerView.Adapter<ToGoRecyclerAdapter.MyRowViewHolder> {

    private LayoutInflater inflater;
    List<MenuListObject> menuOjectList;
    private Context mContext;
    private int focusedItem = 0;

    public ToGoRecyclerAdapter(Context context, List<MenuListObject> menuOject) {
        LayoutInflater.from(context);
        this.menuOjectList = menuOjectList;
        this.mContext = context;
    }
    @Override
    public MyRowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.togo_row, parent, false);
        MyRowViewHolder holder = new MyRowViewHolder(view);



        return holder;
    }

    @Override
    public void onBindViewHolder(MyRowViewHolder holder, int position) {

        MenuListObject current = menuOjectList.get(position);
        holder.item.setText(current.getItem());
        holder.group.setText(current.getGroup());
        holder.price.setText(current.getPrice());

    }

    public  void clearAdapter () {
        menuOjectList.clear();
        notifyDataSetChanged();

    }



    @Override
    public int getItemCount() {
        return (null != menuOjectList ? menuOjectList.size() : 0);
    }

    class MyRowViewHolder extends RecyclerView.ViewHolder {

        TextView item;
        TextView group;
        TextView price;
        TableRow tablerow;
        TextView quantity;
        Button minus;
        Button plus;



        public MyRowViewHolder(View itemView) {
            super(itemView);

            item = (TextView) itemView.findViewById(R.id.itemTxt);
            group = (TextView) itemView.findViewById(R.id.groupTxt);
            price = (TextView) itemView.findViewById(R.id.priceTxt);
            tablerow = (TableRow) itemView.findViewById(R.id.tableRow);
            quantity = (TextView) itemView.findViewById(R.id.quantityTV);
            minus = (Button) itemView.findViewById(R.id.buttonminus);
            plus = (Button) itemView.findViewById(R.id.buttonplus);
        }
    }
}