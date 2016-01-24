package comapps.com.lakewoodsmokehouse.drinks;

class DrinkListObject {

    private String drinkname;
    private Double drinkabv;
    private String drinkgroup;
    private String drinkprice;
    private Integer drinkIBU;
    private String drinkinfo;



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

    public Integer getDrinkIBU() {
        return drinkIBU;
    }

    public void setDrinkIBU(Integer drinkIBU) {
        this.drinkIBU = drinkIBU;
    }

    public String getDrinkInfo() {
        return drinkinfo;
    }

    public void setDrinkInfo(String drinkinfo) {
        this.drinkinfo = drinkinfo;
    }



    @Override
    public String toString() {
        return "DrinkList [drinkname=" + drinkname +
                ", drinkabv=" + drinkabv.toString() +
                ", drinkgroup=" + drinkgroup +
                ", drinkprice=" + drinkprice +
                ", drinkIBU=" + drinkIBU.toString() + ", drinkinfo=" + drinkinfo + "]";
    }


}
