package comapps.com.theblindbutcherdallas;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseObject;

/**
 * Created by me on 11/7/2015.
 */
public class AddReview extends Activity {
    TextView reviewName;
    RatingBar ratingBar;
    TextView reviewText;
    String ratingString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addreview);
        Button addButton;
        addButton = (Button) findViewById(R.id.addButton);
        reviewName = (TextView) findViewById(R.id.name);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        Drawable stars = ratingBar.getProgressDrawable();
        stars.setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);


        reviewText = (TextView)findViewById(R.id.comment);


        addButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                try {// TODO Auto-generated method stub

                    ParseObject review = new ParseObject("theblindbutcherreviews");
                    review.put("reviewname", reviewName.getText().toString());
                    review.put("rating", String.valueOf(ratingBar.getRating()));
                    review.put("review", reviewText.getText().toString());
                    review.saveInBackground();

                    Toast.makeText(getApplicationContext(), "New review added", Toast.LENGTH_LONG).show();
                } catch (Exception e) {

                }
            }
        });
    }



}
