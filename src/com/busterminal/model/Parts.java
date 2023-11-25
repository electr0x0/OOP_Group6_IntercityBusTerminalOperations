package com.busterminal.model;

import java.io.Serializable;

public class Parts implements Serializable
{
    String name,model,category;
    int price;

    public Parts(String name, String model, int price,String category) {
        this.name = name;
        this.model = model;
        this.category = category;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }
    
    public static boolean validateInput(String partsName, String partsModel, String partsPrice, String catagory) {
        return !partsName.isEmpty() && !partsModel.isEmpty() && !partsPrice.isEmpty() && catagory != null;
    }

   
}
