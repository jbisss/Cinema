package main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Random;

public class Film {
    public ArrayList<String> schedule = new ArrayList<>();
    public ArrayList<String> cinemaHall_1 = new ArrayList<>();
    public ArrayList<String> cinemaHall_2 = new ArrayList<>();
    public ArrayList<Review> reviews = new ArrayList<>();
    private final Image image;
    private final String name;
    private final double rating;
    private final int ticketCost;
    public final Button button;
    public final Label labelRating;
    public final Label labelCost;
    public Film(Image image, String name, double rating,int ticketCost){
        this.image = image;
        this.name = name;
        this.rating = rating;
        this.ticketCost = ticketCost;
        this.button = new Button(name);
        this.labelRating = new Label(String.format("%.1f",rating) + "\t");
        this.labelCost = new Label(Integer.toString(ticketCost));
        this.button.setPrefWidth(80);
        this.labelRating.setPrefWidth(72);
        this.labelRating.setAlignment(Pos.CENTER);
        this.labelCost.setPrefWidth(52);
        this.labelCost.setAlignment(Pos.CENTER);
        this.button.setOnAction(actionEvent -> {
            CurrentObjects.filmName.setText(name);
            CurrentObjects.filmCost.setText(Integer.toString(ticketCost));
            CurrentObjects.filmRating.setText(String.format("%.1f", rating));
            CurrentObjects.image.setImage(image);
            ObservableList<String> times = FXCollections.observableArrayList(schedule);
            ObservableList<String> seats_1 = FXCollections.observableArrayList(cinemaHall_1);
            ObservableList<String> seats_2 = FXCollections.observableArrayList(cinemaHall_2);
            CurrentObjects.timeList.setItems(times);
            CurrentObjects.reviewText.setText(toString());
            CurrentObjects.hall_1.setItems(seats_1);
            CurrentObjects.hall_2.setItems(seats_2);
            CurrentObjects.currentFilm = this;
        });

        for (int i = 0; i < 5;i ++) {
            Random randHours = new Random();
            Random randMinutes = new Random();
            int hours = randHours.nextInt(24);
            String hoursString = "";
            int minutes = randMinutes.nextInt(60);
            String minutesString = "";
            if (hours < 10) {
                hoursString = "0" + hours;
            } else {
                hoursString = String.valueOf(hours);
            }
            if (minutes < 10) {
                minutesString = "0" + minutes;
            } else {
                minutesString = String.valueOf(minutes);
            }
            String result = hoursString + ":" + minutesString;
            if (!this.schedule.contains(result)){
                this.schedule.add(result);
                Random rand = new Random();
                this.cinemaHall_1.add(Integer.toString(1));
                this.cinemaHall_2.add(Integer.toString(rand.nextInt(30) + 2));
            }
        }
        CurrentObjects.films.add(this);
    }

    public void addReview(User user, String textReview){
        this.reviews.add(new Review(user, textReview));
    }

    public void changeTimeOnSchedule(String oldTime, String newTime){
        schedule.remove(oldTime);
        schedule.add(newTime);
    }

    public double getRating() {
        return rating;
    }
    public int getTicketCost(){
        return this.ticketCost;
    }
    public String getName(){
        return this.name;
    }
    public Image getImage(){
        return this.image;
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        for (Review review : reviews) {
            result.append(review);
        }
        result.append("\n");
        return result.toString();
    }
}
