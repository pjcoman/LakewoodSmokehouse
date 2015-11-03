package comapps.com.theblindbutcherdallas;

import android.os.AsyncTask;

import com.amazonaws.services.simpledb.model.CreateDomainRequest;
import com.amazonaws.services.simpledb.model.PutAttributesRequest;
import com.amazonaws.services.simpledb.model.ReplaceableAttribute;

import java.util.ArrayList;
import java.util.List;

public class StoreToDB extends AsyncTask<Review, Void, Void>  {
	
	// 2. Create Domain and save movie information in domain
	public static void saveReview(String name, float rating, String description)
	{
		try {

			 Connection.getAwsSimpleDB().createDomain(new CreateDomainRequest("review_info"));
			 List<ReplaceableAttribute> attribute= new ArrayList<ReplaceableAttribute>(1);
			 attribute.add(new ReplaceableAttribute().withName("reviewName").withValue(name));
			 attribute.add(new ReplaceableAttribute().withName("reviewRating").withValue(Float.toString(rating)));
			 attribute.add(new ReplaceableAttribute().withName("reviewDescription").withValue(description));
			 Connection.awsSimpleDB.putAttributes(new PutAttributesRequest("review_info",name, attribute));
			
		} catch (Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	@Override
	protected Void doInBackground(Review... params) {
		// TODO Auto-generated method stub
		saveReview(params[0].reviewName, params[0].reviewRating, params[0].reviewDescription);
		return null;
	}



	
}
