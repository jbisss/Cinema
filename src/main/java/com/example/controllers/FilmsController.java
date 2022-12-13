package com.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import main.Admin;
import main.CurrentObjects;
import main.Film;

import java.io.IOException;

public class FilmsController {
    public ImageView image;
    public Label nameUserAdmin;
    public Label userAdminText;
    public ScrollPane filmsScroll;
    public VBox vBoxFilmsList;
    public TextField filmName;
    public TextField filmRating;
    public TextField filmCost;
    public ComboBox<String> timesComboBox;
    public Button buyButton;
    public TextArea reviewsText;
    public TextField ticketsCount;
    public TextField textToPay;
    public AnchorPane back;
    public TextArea reportField;
    public Button approveButton;
    public TextArea reviewField;
    public Button backButton;
    public Label oldTimeText;
    public TextField oldTimeField;
    public Label newTimeText;
    public TextField newTimeField;
    public Button changesButton;

    public void initialize(){
        if (!(CurrentObjects.currentUser instanceof Admin)) {
            oldTimeText.setVisible(false);
            oldTimeField.setVisible(false);
            newTimeText.setVisible(false);
            newTimeField.setVisible(false);
            changesButton.setVisible(false);
            reportField.setPrefHeight(337);
            reportField.setLayoutY(228);
        }
        BackgroundImage myBI= new BackgroundImage(new Image("Back_2.jpg",2560,1440,false,true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        back.setBackground(new Background(myBI));
        CurrentObjects.filmName = filmName;
        CurrentObjects.filmRating = filmRating;
        CurrentObjects.filmCost = filmCost;
        CurrentObjects.timeList = timesComboBox;
        CurrentObjects.image = image;
        CurrentObjects.reviewText = reviewsText;
        Image image_1 = new Image("Mirage.jpg");
        image.setImage(image_1);
        if (CurrentObjects.currentUser instanceof Admin) {
            userAdminText.setText("Admin:");
        } else {
            userAdminText.setText("User:");
        }
        nameUserAdmin.setText(String.valueOf(CurrentObjects.currentUser));
        for (Film film : CurrentObjects.films) {
            HBox hBox = new HBox(film.button, film.labelRating, film.labelCost);
            vBoxFilmsList.getChildren().add(hBox);
        }
        ticketsCount.textProperty().addListener(
                (observable, oldValue, newValue) -> {
                    try {
                        if(Integer.parseInt(ticketsCount.getText()) > 0
                                && Integer.parseInt(ticketsCount.getText()) < Integer.MAX_VALUE) {
                            textToPay.setText(Integer.toString(Integer.parseInt(ticketsCount.getText()) *
                                    CurrentObjects.currentFilm.getTicketCost()));
                        } else {
                            textToPay.setText("");
                        }
                    } catch (Exception exception) {
                        System.out.println("Invalid input!");
                        textToPay.setText("");
                    }
                });
    }
    public void changeTime(){
        String oldTime = oldTimeField.getText();
        String newTime = newTimeField.getText();
        if(timesComboBox.getItems().contains(oldTime) && newTimeField.getText().length() == 5) {
            char[] newTimeChar = newTime.toCharArray();
            boolean flag = true;
            if(newTimeChar[2] == ':'){
                for (int i = 0; i < newTimeChar.length / 2; i++){
                    int x_1 = newTimeChar[i] - 48;
                    int x_2 = newTimeChar[newTimeChar.length - 1 - i] - 48;
                    if (x_1 > 9 && x_2 > 9) {
                        reportField.setText("Неверный формат\nнового времени!");
                        flag = false;
                    }
                }
            }
            if (flag) {
                if (!(CurrentObjects.currentFilm.schedule.contains(newTime))) {
                    CurrentObjects.currentFilm.changeTimeOnSchedule(oldTime, newTime);
                    ObservableList<String> times = FXCollections.observableArrayList(CurrentObjects.currentFilm.schedule);
                    timesComboBox.setItems(times);
                } else {
                    reportField.setText("Такое время уже есть!");
                }
            }
        }
    }
    private int countReports = 1;
    public void buyButtonClick(){
        try {
            int moneyToPay = Integer.parseInt(textToPay.getText());
            if (moneyToPay <= CurrentObjects.currentUser.card.getMoney() && timesComboBox.getValue() != null) {
                CurrentObjects.currentUser.card.withdrawMoney(moneyToPay);
                reportField.setText(reportField.getText() + "#" + countReports + "\nВы купили "
                        + ticketsCount.getText() + " билета\nна фильм: "
                        + filmName.getText() + "\nПо цене: " + textToPay.getText() + "\nВремя сеанса: "
                        + timesComboBox.getValue() + "\n");
            } else if (moneyToPay > CurrentObjects.currentUser.card.getMoney()){
                reportField.setText(reportField.getText() + "#" + countReports + "\nНе хватает денег на карте!\n");
            } else if (timesComboBox.getValue() == null) {
                reportField.setText(reportField.getText() + "#" + countReports +  "\nВыберите время сеанса!\n");
            }
            countReports++;
        } catch (Exception exception){
            System.out.println("Invalid input!");
        }
    }
    public void approveButtonClick(){
        if (!reviewField.getText().equals("")) {
            CurrentObjects.currentFilm.addReview(CurrentObjects.currentUser, reviewField.getText());
            reviewField.setText("");
            reviewsText.setText(String.valueOf(CurrentObjects.currentFilm));
        }
    }
    public void backButtonClick(){
        closeWindow(backButton);
    }
    private void closeWindow(Button button){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("register.fxml"));
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
        stage.setTitle("Регистрация и вход");
        stage.show();
    }
}
