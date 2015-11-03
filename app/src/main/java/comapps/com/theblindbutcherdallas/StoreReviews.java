package comapps.com.theblindbutcherdallas;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class StoreReviews extends Activity {
	 TextView nameText;
	 RatingBar ratingValue;
	 TextView descriptionText; 
     
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_store);
		Button addButton;
		 addButton= (Button) findViewById(R.id.addButton);
		  nameText=(TextView)findViewById(R.id.name);
		  ratingValue=(RatingBar)findViewById(R.id.ratingBar1);
		  descriptionText=(TextView)findViewById(R.id.comment);;//Description
	     addButton.setOnClickListener(new View.OnClickListener() {

			 @Override
			 public void onClick(View arg0) {

				 try {// TODO Auto-generated method stub
					 Review object = new Review();
					 object.reviewName = nameText.getText().toString();
					 object.reviewDescription = descriptionText.getText().toString();
					 object.reviewRating = ratingValue.getRating();
					 //DbOperation.saveMovie(object);

					 new StoreToDB().execute(object).get();
					 Toast.makeText(getApplicationContext(), "New review added", Toast.LENGTH_LONG).show();
				 } catch (Exception e) {

				 }
			 }
		 });
	}



}
