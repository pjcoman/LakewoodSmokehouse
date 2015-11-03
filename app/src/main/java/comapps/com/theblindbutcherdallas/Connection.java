package comapps.com.theblindbutcherdallas;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpledb.AmazonSimpleDB;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;

public class Connection {
	public static AmazonSimpleDB awsSimpleDB;


	// 1. Get Simple DB connection.
	public static AmazonSimpleDB getAwsSimpleDB()
	{
		if(awsSimpleDB==null)
		{
			BasicAWSCredentials credentials = new BasicAWSCredentials(
                    "AKIAJEVKZGFQB44HQMSA",
                    "r6c2SngmyzHW7kTqjFT+YGNDeKJoVu+AbcTBkpsS");

			awsSimpleDB= new AmazonSimpleDBClient(credentials);
		}
		return awsSimpleDB;
	}
	

}
