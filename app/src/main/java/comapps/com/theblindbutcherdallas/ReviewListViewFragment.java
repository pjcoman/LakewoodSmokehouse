package comapps.com.theblindbutcherdallas;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by me on 9/29/2015.
 */
public class ReviewListViewFragment extends ListFragment {


    private List<ReviewListObject> reviewObject;
    private ReviewListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.reviewlistfragment, null, false);
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        List<ParseObject> ob;

        try {
            // Locate the class table named "stansbeers" in Parse.com
            ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
                    "theblindbutcherreviews").fromLocalDatastore();
            // Locate the column named "name" in Parse.com and order list
            // by ascending

            query.orderByDescending("createdAt");


            ob = query.find();



            reviewObject = new ArrayList<>();

            for (ParseObject reviews : ob) {
                // Locate images in flag column




                ReviewListObject review = new ReviewListObject();
                review.setReviewName((String) reviews.get("reviewname"));
                review.setReviewRating((String) reviews.get("rating"));
                String rating = ((String) reviews.get("rating"));
                review.setReview((String) reviews.get("review"));

                int firstDigitOfRating = Integer.valueOf(rating.substring(0,1));

                if ( firstDigitOfRating > 2 ) { reviewObject.add(review);}
            }


        } catch (ParseException e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        adapter = new ReviewListViewAdapter(getActivity(), reviewObject);
        setListAdapter(adapter);


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getListView().setDivider(new ColorDrawable(Color.BLACK));
        getListView().setDividerHeight(5);
    }
}



