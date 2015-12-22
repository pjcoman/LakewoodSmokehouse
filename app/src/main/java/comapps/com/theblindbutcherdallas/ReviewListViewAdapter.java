package comapps.com.theblindbutcherdallas;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

/**
 * Created by me on 11/7/2015.
 */
class ReviewListViewAdapter extends BaseAdapter {


    private final Context context;
    private final List<ReviewListObject> reviewObject;


    public ReviewListViewAdapter(Context context, List<ReviewListObject> reviewObject) {

        this.context = context;
        this.reviewObject = reviewObject;

    }


    @Override
    public int getCount() {
        return reviewObject.size();
    }

    @Override
    public Object getItem(int position) {
        return reviewObject.get(position);
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
            view = inflater.inflate(R.layout.reviewlistrow, parent, false);

            holder = new ViewHolder();
            holder.name = (TextView) view.findViewById(R.id.reviewname);
            holder.ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
            holder.review = (TextView) view.findViewById(R.id.reviewtext);

            view.setTag(holder);

        } else {

            holder = (ViewHolder) view.getTag();
        }


        ReviewListObject object = reviewObject.get(position);

        holder.name.setText(object.getReviewName());
        holder.ratingBar.setRating(Float.parseFloat(object.getReviewRating()));

        Drawable starsInRatingBar = holder.ratingBar.getProgressDrawable();
        DrawableCompat.setTint(starsInRatingBar, Color.YELLOW);

        holder.review.setText(object.getReview());

        Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/MerriweatherSans-Italic.ttf");
        holder.name.setTypeface(font);
        holder.review.setTypeface(font);


        String checkForNull = object.getReviewName();
        if ( checkForNull == null ) {
            holder.name.setVisibility(View.GONE);
        } else {
            holder.name.setVisibility(View.VISIBLE);
        }



        String text = object.getReviewRating();


        switch (text) {
            case ("5.0"):
                holder.name.setTextColor(Color.rgb(212, 175, 55));
                break;
            case ("4.5"):
                holder.name.setTextColor(Color.rgb(212, 175, 55));
                break;
            case ("4.0"):
                holder.name.setTextColor(Color.rgb(192, 192, 192));
                break;
            case ("3.5"):
                holder.name.setTextColor(Color.rgb(192, 192, 192));
                break;
            case ("3.0"):
                holder.name.setTextColor(Color.rgb(91, 57, 30));
                break;

            default:
                //   holder.item.setTextColor(Color.YELLOW);

        }





        return view;


    }


    public class ViewHolder {
        TextView name;
        RatingBar ratingBar;
        TextView review;


    }


}
