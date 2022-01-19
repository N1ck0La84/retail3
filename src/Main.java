package ru.retail;

import ru.retail.product.Eatable;
import ru.retail.product.Uneatable;

public class Main {

    public static void main(String[] args) {
        Storage storage = new Storage("Склад");
        BankAccount bankAccount = new BankAccount("Основной счет", 0);
        Eatable milk = new Eatable("Молоко", "260123435", 20.5f, "10.12.21");
        milk.mov(storage);
        milk.setState("В наличии");
        milk.getBarCode().changePriceToPercent((byte)10);
        Uneatable toy = new Uneatable("Кукла Барби", "263123312", 600, "Китай");
        toy.mov(storage);
        toy.setState("В наличии");
        toy.getBarCode().changePrice(100f);
        Client vasy = new Client("Вася");
        vasy.attachBankAccount(new BankAccount("Банковский счет Васи", 500));
        vasy.putInBasket(milk);
        vasy.putInBasket(toy);
        vasy.payOrder("Безналичная оплата", "Оплата при получении", bankAccount);
        Order order = vasy.getOrder();
        order.setDeliveryAdress("Удмуртская республика, г.Ижевск, Ленина 1");
        order.setDeliveryDate("31.12.2021");
        order.setState("Заказ в обработке");
        order.sortOrderBySum();
        order.createPackingTask(storage);
        if (storage.isTaskExists()) { // пока очень примитивно
            order.packProducts(storage);
            Delivery delivery = order.sendToDelivery();
            delivery.deliver(vasy);
            Payment payment = order.getPayment();
            payment.giveDiscount(100);
            if (payment.getState() != "Оплачено") {
                delivery.getPay(order, vasy);// курьер получает оплату
                payment.setState("Оплачено");
            }
            payment.printCheck("Полный расчет");
            if (delivery.getMoney() != 0) {
                bankAccount.putMoney(delivery.getMoney());// перевести деньги на расчетный счет
            }
            order.sendToClient();
            order.setState("Доставлено");
            System.out.println("Заказу выставлен статус " + order.getState());
            System.out.println("Заказ доставлен по адресу " + order.getDeliveryAdress() + " " + order.getDeliveryDate());
        }
    }
}