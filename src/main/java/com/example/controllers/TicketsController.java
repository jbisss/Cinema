package com.example.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.CurrentObjects;

import java.io.IOException;

public class TicketsController {
    public TextArea textTickets;
    public TextField fieldUserInfo;
    public Button buttonBack;

    public void initialize(){
        fieldUserInfo.setText(String.valueOf(CurrentObjects.currentUser));
        textTickets.setText(CurrentObjects.currentUser.getTickets());
    }
    public void buttonBackClick(){
        closeWindow(buttonBack, "films.fxml");
    }
    private void closeWindow(Button button, String scene){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(scene));
        LoaderFxml(loader);
        Stage stagePrev = (Stage) button.getScene().getWindow();
        stagePrev.hide();
    }
    private void LoaderFxml(FXMLLoader loader) {
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Фильмы");
        stage.show();
    }
}
