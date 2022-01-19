package ru.retail;

public class BankAccount {

    private String id;
    private float sum;

    public BankAccount(String id, float sum) {
        this.id = id;
        this.sum = sum;
    }

    public String getId() {
        return id;
    }

    public void putMoney(float sum) {
        this.sum += sum;
        System.out.println("На рассчетный счет " + id + " перечислили " + sum);
    }

    public boolean checkMoney(float sum){
        if (this.sum >= sum) {
            System.out.println("На расчетном счете хватает денег");
            return true;
        } else {
            System.out.println("На рачетном счете не хватает " + (sum - this.sum));
            return false;
        }
    }


    public void takeMoney(float sum){
        this.sum -= sum;
        System.out.println("С расчетного счета "+ id + " снято " + sum);

    }
}