package comapps.com.theblindbutcherdallas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GetReviews extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_get);
		try {
			ListView reviewListView = (ListView) findViewById(R.id.listView1);
			final Review [] reviewList= new ReadFromDB().execute().get();
			String[] list = new String[reviewList.length];
			for (int i = 0; i < list.length; i++) {
				list[i] = reviewList[i].reviewName;
				}
		    ArrayAdapter<String> adapter = new ArrayAdapter<String>( this ,R.layout.activity_listview, R.id.label, list);
			reviewListView.setAdapter(adapter);

			reviewListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					Intent intent = new Intent(GetReviews.this, ShowReview.class);
					intent.putExtra("NAME", reviewList[position].reviewName);
					intent.putExtra("RATING", reviewList[position].reviewRating).toString();
					intent.putExtra("DESC", reviewList[position].reviewDescription);
					startActivity(intent);
				}
			});
	       
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


}
