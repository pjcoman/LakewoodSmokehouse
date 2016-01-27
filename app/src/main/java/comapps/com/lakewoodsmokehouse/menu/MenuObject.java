package comapps.com.lakewoodsmokehouse.menu;

class MenuObject {

    private String item;
    private String group;
    private String price;
    private String description;
    private Integer quantity = 0;
    private String selection;
    private String selection2;
    private String selection3;




    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }


    public String getSelection2() {
        return selection2;
    }

    public void setSelection2(String selection2) {
        this.selection2 = selection2;
    }


    public String getSelection3() {
        return selection3;
    }

    public void setSelection3(String selection3) {
        this.selection3 = selection3;
    }


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }



    @Override
    public String toString() {
        return "MenuList [item=" + item +
                ", price=" + price +
                ". group=" + group +
                ". description=" + description +
                ", quantity=" + quantity +
                ", selection=" + selection +
                ", selection2=" + selection2 +
                ", selection3=" + selection3 +"]";
    }


}
