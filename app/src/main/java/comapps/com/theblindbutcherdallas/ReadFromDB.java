package comapps.com.theblindbutcherdallas;

import android.os.AsyncTask;

import com.amazonaws.services.simpledb.model.SelectRequest;

import java.util.List;

public class ReadFromDB extends AsyncTask<Void, Void, Review[]> {

	
	
	public static Review [] getAllReviews() throws Exception
	{
		SelectRequest selectRequest=  new SelectRequest("select * from review_info").withConsistentRead(true);
		
		List<com.amazonaws.services.simpledb.model.Item> items  = Connection.getAwsSimpleDB().select(selectRequest).getItems();
		
		try
		{
		com.amazonaws.services.simpledb.model.Item temp1;
		int size= items.size();
		Review [] reviewList= new Review[size];
		
		for(int i=0; i<size;i++)
		{
			temp1= ((com.amazonaws.services.simpledb.model.Item)items.get( i ));
			
			List<com.amazonaws.services.simpledb.model.Attribute> tempAttribute= temp1.getAttributes();
			reviewList[i]= new Review();
			for(int j=0; j< tempAttribute.size();j++)
			{
				if(tempAttribute.get(j).getName().equals("reviewName"))
				{
					reviewList[i].reviewName= tempAttribute.get(j).getValue();
				}
				else if(tempAttribute.get(j).getName().equals("reviewDescription"))
				{
					reviewList[i].reviewDescription = tempAttribute.get(j).getValue();
				}
				else if(tempAttribute.get(j).getName().equals("reviewRating"))
				{
					reviewList[i].reviewRating = Float.valueOf(tempAttribute.get(j).getValue());
				}
			}
		}
		return reviewList;
		}
		catch( Exception eex)
		{
			throw new Exception("FIRST EXCEPTION", eex);
		}
	}
	
	@Override
	protected Review[] doInBackground(Void... params) {
		// TODO Auto-generated method stub
		try {
			return getAllReviews();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
