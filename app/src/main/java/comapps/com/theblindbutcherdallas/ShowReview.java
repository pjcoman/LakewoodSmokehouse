package comapps.com.theblindbutcherdallas;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by me on 11/2/2015.
 */
public class ShowReview extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = this.getIntent();
        Bundle extras = intent.getExtras();
        String reviewName = intent.getStringExtra("NAME");
        Float reviewRating = extras.getFloat("RATING");
        String reviewDescription = intent.getStringExtra("DESC");

        Log.d("review name is" , reviewName);


        Log.d("review rating is ", reviewRating.toString());
        Log.d("review description is ", reviewDescription);

        setContentView(R.layout.activity_showreview);
        setTitle(reviewName);

        TextView nameView = (TextView) findViewById(R.id.textViewName);
        RatingBar rb = (RatingBar) findViewById(R.id.ratingBar);
        TextView descriptionView = (TextView) findViewById(R.id.textViewDescription);

        LayerDrawable stars = (LayerDrawable) rb.getProgressDrawable();
        stars.setTint(Color.YELLOW);

        nameView.setText(reviewName);
        rb.setRating(reviewRating);
        descriptionView.setText(reviewDescription);

    }
}
