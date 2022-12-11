package main;

import java.util.ArrayList;

public class User {
    public static final ArrayList<User> users = new ArrayList<>();
    public final Card card;
    private final String name;
    private String login;
    private String password;
    private String feedback;

    public User(String name, String login, String password){
        this.name = name;
        this.login = login;
        this.password = password;
        this.card = new Card();
        this.feedback = "";
        users.add(this);
    }

    public User findUser(String login, String password){
        for (User user : users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private String getLogin(){
        return this.login;
    }
    private String getPassword(){
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
}
