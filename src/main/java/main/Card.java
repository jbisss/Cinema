package main;

public class Card {
    private int money;
    public Card(){}
    public Card(int money){
        this.money = money;
    }

    public int getMoney(){
        return this.money;
    }

    public void withdrawMoney(int drawMoney){
        this.money-=drawMoney;
    }
}
