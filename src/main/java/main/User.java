package main;

import java.util.ArrayList;

public class User {
    public final Card card;
    private final String name;
    private String login;
    private String password;
    private String feedback;

    public User(String name, String login, String password, int money){
        this.name = name;
        this.login = login;
        this.password = password;
        this.card = new Card(money);
        this.feedback = "";
        CurrentObjects.users.add(this);
    }

    String getLogin(){
        return this.login;
    }
    String getPassword(){
        return this.password;
    }
    public String getName(){
        return this.name;
    }

    private void setLoginPassword(String login, String password){
        this.login = login;
        this.password = password;
    }
    public void setFeedback(String feedback){
        this.feedback = feedback;
    }

    @Override
    public String toString(){
        return this.name + "@" + this.login;
    }
}
