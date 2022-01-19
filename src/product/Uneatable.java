package ru.retail.product;

import ru.retail.product.Product;

public class Uneatable extends Product {

    private String country;

    public Uneatable(String name, String id, float price, String country){
        super(name, id, price);
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}