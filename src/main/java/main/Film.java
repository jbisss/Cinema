package main;

import javafx.scene.image.Image;

import java.util.ArrayList;

public class Film {
    public static final ArrayList<Film> films = new ArrayList<>();
    public ArrayList<String> schedule = new ArrayList<>();
    private final Image image;
    private final String name;
    private final int ticketCost;

    public Film(Image image, String name, int ticketCost){
        this.image = image;
        this.name = name;
        this.ticketCost = ticketCost;
        films.add(this);
    }

    public void changeTimeOnSchedule(String newTime, int index){
        schedule.set(index, newTime);
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
}
