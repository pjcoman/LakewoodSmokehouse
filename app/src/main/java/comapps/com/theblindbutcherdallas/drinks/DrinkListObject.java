package comapps.com.theblindbutcherdallas.drinks;

class DrinkListObject {

    private String drinkname;
    private Double drinkabv;
    private String drinkgroup;
    private String drinkprice;



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
