package ru.retail;

public class Delivery {

    private String id;
    private String deliveryDate;
    private String deliveryAdress;
    private Float money = 0f;

    public Delivery(String id, String deliveryDate, String deliveryAdress) {
        this.id = id;
        this.deliveryDate = deliveryDate;
        this.deliveryAdress = deliveryAdress;
    }

    public String getId() {
        return id;
    }

    public void deliver(Client client) {
        System.out.println("Доствлено клиенту " + client.getName() + " по адресу " + deliveryAdress);
    }

    public void getPay(Order order, Client client) {
        float orderSum = order.getPayment().getSum();
        client.getBankAccount().takeMoney(orderSum);
        money += orderSum;
        order.setState("Оплачено");
    }

    public Float getMoney() {
        return money;
    }
}