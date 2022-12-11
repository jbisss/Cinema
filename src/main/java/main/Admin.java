package main;

public class Admin extends User{
    public Admin(String name, String login, String password) {
        super(name, login, password);
    }

    public void changeSchedule(String newTime, Film film, int sessionIndex) {
        film.changeTimeOnSchedule(newTime, sessionIndex);
    }
}
