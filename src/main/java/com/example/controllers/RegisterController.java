package com.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.CurrentObjects;
import main.User;

import java.io.IOException;

public class RegisterController {
    public TextField loginEnterField;
    public Button enterButton;
    public PasswordField passwordEnterField;
    public Button registrationButton;
    public TextField loginRegisterField;
    public PasswordField passwordRegisterField;
    public Button approveButton;
    public Label registerSuccessLabel;
    public TextField nameRegisterField;
    public Label enterSuccessLabel;

    private void closeWindow(Button button, String file){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(file));
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
    public void passwordEnterButtonClick(){
        CurrentObjects.currentUser = User.findUser(loginEnterField.getText(), passwordEnterField.getText());
        if (CurrentObjects.currentUser != null) {
            enterSuccessLabel.setText("Успешно");
            closeWindow(enterButton, "films.fxml");
        } else {
            enterSuccessLabel.setText("Неверный логин или пароль");
        }
    }
    public void registrationButtonClick(){
        Stage stagePrev = (Stage) registrationButton.getScene().getWindow();
        stagePrev.setHeight(450);
    }
    public void approveButtonClick(){

    }
}