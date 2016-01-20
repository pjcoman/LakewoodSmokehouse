package comapps.com.lakewoodsmokehouse;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

/**
 * Created by me on 11/7/2015.
 */
public class ShowReview extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showreview);

        Bundle extras = getIntent().getExtras();

        String review = extras.getString("reviewtext");



        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.6), (int)(height*.6));

        TextView reviewTV = (TextView) findViewById(R.id.textViewReview);
        reviewTV.setText(review);

    }
}
