package com.example.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
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
    public AnchorPane back;

    public void initialize(){
        BackgroundImage myBI= new BackgroundImage(new Image("Back_2.jpg",2560,1440,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        back.setBackground(new Background(myBI));
    }

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
        CurrentObjects.currentUser = CurrentObjects.findUser(loginEnterField.getText(), passwordEnterField.getText());
        if (CurrentObjects.currentUser != null) {
            closeWindow(enterButton, "films.fxml");
        } else {
            enterSuccessLabel.setText("Неверный логин или пароль!");
            enterSuccessLabel.setLayoutX(127);
        }
    }
    public void registrationButtonClick(){
        Stage stagePrev = (Stage) registrationButton.getScene().getWindow();
        stagePrev.setHeight(480);
    }
    public void approveButtonClick(){
        if (CurrentObjects.findUser(loginRegisterField.getText()) == null && !loginRegisterField.getText().equals("")
                && !passwordRegisterField.getText().equals("")){
            new User(nameRegisterField.getText(), loginRegisterField.getText(), passwordRegisterField.getText(), 1000);
            enterSuccessLabel.setText("Вы успешно зарегистрировались! Выполните вход в систему.");
            enterSuccessLabel.setLayoutX(34);
            loginEnterField.setText("");
            passwordEnterField.setText("");
            Stage stagePrev = (Stage) registrationButton.getScene().getWindow();
            stagePrev.setHeight(250);
        } else {
            registerSuccessLabel.setText("Некорректный логин/пароль или уже есть пользователь с таким логином!");
            registerSuccessLabel.setLayoutX(1);
        }
    }
}