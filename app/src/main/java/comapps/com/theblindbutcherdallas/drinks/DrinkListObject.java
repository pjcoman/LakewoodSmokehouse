package comapps.com.theblindbutcherdallas.drinks;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpledb.AmazonSimpleDB;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.services.simpledb.model.CreateDomainRequest;
import com.amazonaws.services.simpledb.model.PutAttributesRequest;
import com.amazonaws.services.simpledb.model.ReplaceableAttribute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DrinkListObject {

    private String drinkname;
    private Double drinkabv;
    private String drinkgroup;
    private String drinkprice;

    private static String domain;
    private static Properties properties;

    private static AmazonSimpleDB sdb;

    public static void save(String drinkname, Double drinkabv, String drinkgroup, String drinkprice) {

        DrinkListObject.getSimpleDB().createDomain(new CreateDomainRequest(DrinkListObject.getDomain()));
        List<ReplaceableAttribute> attributes = new ArrayList<ReplaceableAttribute>(1);
        attributes.add(new ReplaceableAttribute().withName("name").withValue(drinkname));
        attributes.add(new ReplaceableAttribute().withName("abv").withValue(Double.toString(drinkabv)));
        attributes.add(new ReplaceableAttribute().withName("group").withValue(drinkgroup));
        attributes.add(new ReplaceableAttribute().withName("price").withValue(drinkprice));

        sdb.putAttributes(new PutAttributesRequest(DrinkListObject.getDomain(), drinkname, attributes));
    }

    public static String[] getDrinks() {
        String[] drinks = new String[0];
        return drinks;
    }



    private static AmazonSimpleDB getSimpleDB() {
        if (sdb == null) {
            BasicAWSCredentials credentials = new BasicAWSCredentials(getProperties().getProperty("accessKey"),
                     DrinkListObject.getProperties().getProperty("secretKey"));
            sdb = new AmazonSimpleDBClient(credentials);
        }

        return sdb;
    }

    private static String getDomain() {
        if (domain == null) {
            domain = getProperties().getProperty("domain");
        }
        return domain;
    }

    private static Properties getProperties() {
        if (properties == null) {
            properties = new Properties();

            try {
                properties.load(DrinkListObject.class.getResourceAsStream("AwsCredentials.properties"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return properties;

    }




    public String getDrinkName() {
        return drinkname;
    }

    public void setDrinkName(String drinkname) {
        this.drinkname = drinkname;
    }


    public Double getDrinkAbv() {
        return drinkabv;
    }

    public void setDrinkAbv(Double drinkabv) {
        this.drinkabv = drinkabv;
    }

    public String getDrinkGroup() {
        return drinkgroup;
    }

    public void setDrinkGroup(String drinkgroup) {
        this.drinkgroup = drinkgroup;
    }

    public String getDrinkPrice() {
        return drinkprice;
    }

    public void setDrinkPrice(String drinkprice) {
        this.drinkprice = drinkprice;
    }



    @Override
    public String toString() {
        return "DrinkList [drinkname=" + drinkname + ", drinkabv=" + drinkabv.toString() +
                ", drinkgroup=" + drinkgroup + ", drinkprice=" + drinkprice + ", beerabout=" + "]";
    }


}
