package ru.retail;

import ru.retail.product.Product;

public class Order {

    private Product[] productList;
    private int currentLineOrder;
    private String state;
    private Client client;
    private Payment payment;
    private String deliveryDate;
    private String deliveryAdress;

    public Order() {
        productList = new Product[10];
        currentLineOrder = 1;
    }

    public void addToOrder(Product product) {
        if (currentLineOrder > 10) {
            System.out.println("Корзина переполнена");
            return;
        }
        productList[currentLineOrder] = product;
        currentLineOrder += 1;
    }

    public float getOrderSum() {
        float sum = 0;
        for (Product currentProduct : productList) {
            if (currentProduct != null) {
                sum += currentProduct.getPrice();
            }
        }
        return sum;
    }

    public void sortOrderBySum(){
        boolean wasMove;
        Product buffer;
        do {
            wasMove = false;
            for (int i = 0, j = 1; j < productList.length; ++i, ++j) {
                if (productList[j] != null) {
                    if (productList[i] == null) {
                        productList[i] = productList[j];
                        productList[j] = null;
                        wasMove = true;
                    } else if (productList[j].getBarCode().getPrice() > productList[i].getBarCode().getPrice()){
                        buffer = productList[i];
                        productList[i] = productList[j];
                        productList[j] = buffer;
                        wasMove = true;
                    }
                }
            }

        } while (wasMove);
    }

    public boolean existFreePlace(){
        int i = 0;
        while (i < productList.length){
            if (productList[i] == null){
                return true;
            }
            i++;
        }
        return false;
    }

    public void createPackingTask(Storage storage) {
        System.out.println("Создано задание на упаковку заказа для клиента " + client.getName());
        storage.setTaskExists(true);
    }

    public void packProducts(Storage storage){
        setProductsState("Упакован для доставки на месте хранения " + storage.getName());
        state = "Заказ собран";
    }

    public Delivery sendToDelivery() {
        System.out.println("Заказ передан в службу доставки");
        setProductsState("Передан в службу доставки");
        state = "В службе доставки";
        return new Delivery("Доставка № 1", deliveryAdress, deliveryDate); //TODO: подумать над формированием ид доставки
    }

    public void sendToClient() {
        System.out.println("Заказ передан клиенту " + client.getName());
        setProductsState("Передан клиенту ");
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void setProductsState(String state) {
        for (int i =0; i < productList.length; ++i) {
            if ( productList[i] != null) {
                productList[i].setState(state);
            }
        }
    }

//    public void setProductsState(String state) {
//        for (product currentProduct : productList) {
//            if (currentProduct != null) {
//                currentProduct.setState(state);
//            }
//        }
//    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDeliveryAdress() {
        return deliveryAdress;
    }

    public void setDeliveryAdress(String deliveryAdress) {
        this.deliveryAdress = deliveryAdress;
    }

    public String getState() {
        return state;
    }
}