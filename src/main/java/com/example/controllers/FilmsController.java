package com.example.controllers;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FilmsController {
    public ImageView image;

    public void initialize(){
        Image image_1 = new Image("Batman.jpg");
        image.setImage(image_1);
    }
}
