package ru.retail;

import ru.retail.product.Product;

public class Client {

    private String name;
    private Order order;
    private BankAccount bankAccount;

    public Client(String name) {
        this.name = name;
        order = new Order();
        order.setClient(this); // ??? есть ли другая возможность
    }

    public void putInBasket(Product product) {
        if (product.getState() == "В наличии") {
            System.out.println("Клиент " + name + " положил товар " + product.getName() + " в корзину");
            product.setState("Выбран");
            order.addToOrder(product);
        } else {
            System.out.printf("Товара " + product.getName() + " нет в наличии.");
        }
    }

    public void payOrder(String paymentType, String paymentState, BankAccount bankAccount) {
        float orderSum = order.getOrderSum();
        if (order.getPayment() != null) {
            System.out.println("Заказ уже оплачен");
            return;
        }
        Payment payment = new Payment(paymentType, orderSum, paymentState, order);
        if (payment.getState() != "Оплата при получении") {
            payment.printCheck("Предоплата 100%");
            System.out.println("Клиент " + name + " оплатил заказ на сумму " + orderSum);
            bankAccount.putMoney(orderSum);
        }
        order.setPayment(payment);
        order.setState("Заказ в обработке"); //  ??? может быть стоит в другой класс перенести
        order.setProductsState("Зарезервирован");
    }

    public Order getOrder() {
        return order;
    }

    public String getName() {
        return name;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void attachBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }
}