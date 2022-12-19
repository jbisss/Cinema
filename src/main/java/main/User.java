package main;

import java.util.ArrayList;

public class User {
    public final Card card;
    private final String name;
    private final String login;
    private final String password;
    private String tickets;

    public User(String name, String login, String password, int money){
        this.name = name;
        this.login = login;
        this.password = password;
        this.card = new Card(money);
        this.tickets="";
        CurrentObjects.users.add(this);
    }

    public String getLogin(){
        return this.login;
    }
    public String getPassword(){
        return this.password;
    }
    public String getName(){
        return this.name;
    }

    public String getTickets() {
        return tickets;
    }

    public void setTickets(String tickets) {
        this.tickets += tickets + "\n";
    }

    @Override
    public String toString(){
        return this.name + "@" + this.login;
    }
}
