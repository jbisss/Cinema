package main;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class CurrentObjects {
    public static final ArrayList<Film> films = new ArrayList<>();
    public static final ArrayList<User> users = new ArrayList<>();
    public static User currentUser;
    public static Film currentFilm;
    public static TextField filmName;
    public static TextField filmRating;
    public static TextField filmCost;
    public static ComboBox<String> hall_1;
    public static ComboBox<String> hall_2;
    public static ComboBox<String> timeList;
    public static ImageView image;
    public static TextArea reviewText;
    static {
        User user_1 = new User("Mark","mark_best","1234", 1000);
        User user_2 = new User("Tony", "tiny_tony", "4321", 2000);
        new Admin("Admin","login","password", 10000);

        int minI = 100; int maxI = 500; int diffI = maxI - minI;
        Random randCost = new Random();
        double minD = 2.0; double maxD = 10.0; double diffD = maxD - minD;
        Random randRating = new Random();
        new Film(new Image("Batman.jpg"), "Batman", randRating.nextDouble(diffD) + minD,
                randCost.nextInt(diffI + 1) + minI).
                addReview(user_1, "Фильм, не оставивший после просмотра никаких чувств...");
        new Film(new Image("Hobbit.jpg"), "Hobbit", randRating.nextDouble(diffD) + minD,
                randCost.nextInt(diffI + 1) + minI).
                addReview(user_2, "Если только у тебя есть глаза, ты всюду увидишь сверкающие цукатные рощи, прозрачные марципановые замки — словом, всякие чудеса и диковинки.");
        new Film(new Image("Logan.jpg"), "Logan", randRating.nextDouble(diffD) + minD,
                randCost.nextInt(diffI + 1) + minI).
                addReview(user_1, "Саванны - это не только красивая природа, но и места, таящие опасности на каждом шагу");
        new Film(new Image("Troll.jpg"), "Troll", randRating.nextDouble(diffD) + minD,
                randCost.nextInt(diffI + 1) + minI).
                addReview(user_2, "Иногда самый страшный монстр находится внутри тебя");
        new Film(new Image("Valerian.jpg"), "Valerian", randRating.nextDouble(diffD) + minD,
                randCost.nextInt(diffI + 1) + minI).
                addReview(user_1, "Слабые женщины от горя опустошают холодильники, а сильные - становятся ведьмами.");
        new Film(new Image("Venom.jpg"), "Venom", randRating.nextDouble(diffD) + minD,
                randCost.nextInt(diffI + 1) + minI).
                addReview(user_2, "Что делает человека человеком?");
        new Film(new Image("X-men.jpg"), "X-men", randRating.nextDouble(diffD) + minD,
                randCost.nextInt(diffI + 1) + minI).
                addReview(user_1, "Ребенок в восторге, особенно ребенок внутри меня!");
    }
    public static User findUser(String login, String password){
        for (User user : CurrentObjects.users) {
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    public static User findUser(String login){
        for (User user : CurrentObjects.users) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }
}
