package ru.retail;

public class BarCode {

    private String id;
    private float price;

    public BarCode(String id, float price){
        this.id = id;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    // пример вызова toy.getBarCode().changePrice(100f);
    public void changePrice(float delta) {
        float oldPrice = price;
        price = price + delta;
        System.out.println("Изменили цену продукта " + id + " Было " + oldPrice + " Стало " + price);
    }

    // пример вызова milk.changePriceToPercent((byte)10);
    public void changePriceToPercent(byte percent) {
        float oldPrice = price;
        price = price + price * (percent / 100);
        System.out.println("Изменили цену продукта " + id + " Было " + oldPrice + " Стало " + price);
    }
}