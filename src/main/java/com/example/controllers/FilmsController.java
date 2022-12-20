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
    public Label labelError;
    public Button buttonToTickets;
    public Label labelSeats_1;
    public Label labelSeats_2;
    public ComboBox<String> comboHall_1;
    public ComboBox<String> comboHall_2;
    public TextField hallNumber;
    public Label labelChosing;

    public void buttonToTicketsClick(){
        closeWindow(buttonToTickets, "tickets.fxml");
    }

    public void initialize(){
        CurrentObjects.hall_1 = comboHall_1;
        CurrentObjects.hall_2 = comboHall_2;
        if (!(CurrentObjects.currentUser instanceof Admin)) {
            oldTimeText.setVisible(false);
            oldTimeField.setVisible(false);
            newTimeText.setVisible(false);
            newTimeField.setVisible(false);
            changesButton.setVisible(false);
            if (!CurrentObjects.flagHall){
                comboHall_2.setDisable(true);
            }
        } else {
            buyButton.setDisable(true);
            labelSeats_1.setVisible(false);
            labelSeats_2.setVisible(false);
            comboHall_1.setVisible(false);
            comboHall_2.setVisible(false);
            hallNumber.setVisible(false);
            labelChosing.setVisible(false);
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
                        labelError.setText("");
                    } catch (Exception exception) {
                        labelError.setText("Invalid input!");
                        textToPay.setText("");
                    }
                });
    }
    public void changeTime(){
        String oldTime = oldTimeField.getText();
        String newTime = newTimeField.getText();
        CurrentObjects.flagHall = false;
        if(timesComboBox.getItems().contains(oldTime) && newTimeField.getText().length() == 5) {
            char[] newTimeChar = newTime.toCharArray();
            boolean flag = true;
            if(newTimeChar[2] == ':'){
                    int x_1 = newTimeChar[0] - 48;
                    x_1*=10;
                    int x_11 = newTimeChar[1] - 48;
                    int x_2 = newTimeChar[3] - 48;
                    x_2*=10;
                    int x_22 = newTimeChar[4] - 48;
                    if (x_1 + x_11 > 23 || x_2 + x_22 > 59) {
                        reportField.setText("Неверный формат\nнового времени!");
                        flag = false;
                }
            }
            if (flag) {
                if (!(CurrentObjects.currentFilm.schedule.contains(newTime))) {
                    CurrentObjects.currentFilm.changeTimeOnSchedule(oldTime, newTime);
                    ObservableList<String> times = FXCollections.observableArrayList(CurrentObjects.currentFilm.schedule);
                    timesComboBox.setItems(times);
                    reportField.setText("Установлено\nновое время!");
                } else {
                    reportField.setText("Такое время уже есть!");
                }
            }
        } else {
            reportField.setText("Неверный ввод времени!");
        }
    }
    private int countReports = 1;
    public void buyButtonClick(){
        labelError.setText("");
        try {
            int moneyToPay = Integer.parseInt(textToPay.getText());
            if (moneyToPay <= CurrentObjects.currentUser.card.getMoney() && timesComboBox.getValue() != null) {
                int hallNumberIndex = 0;
                try{
                    if (Integer.parseInt(hallNumber.getText()) > 2 || Integer.parseInt(hallNumber.getText()) < 1){
                        labelError.setText("Invalid number!");
                    } else {
                        hallNumberIndex = Integer.parseInt(hallNumber.getText());
                    }
                } catch (Exception exception){
                    labelError.setText("Invalid input!");
                }
                if (hallNumberIndex!=0){
                    int index = CurrentObjects.currentFilm.schedule.indexOf(timesComboBox.getValue());
                    if (hallNumberIndex == 1){
                        if (Integer.parseInt(CurrentObjects.currentFilm.cinemaHall_1.get(index))
                                >= Integer.parseInt(ticketsCount.getText()) && comboHall_1.getValue()!=null) {
                            String result = "#" + countReports + "\nВы купили "
                                    + ticketsCount.getText() + " билета\nна фильм: "
                                    + filmName.getText() + "\nПо цене: " + textToPay.getText() + "\nВремя сеанса: "
                                    + timesComboBox.getValue() + "\n";
                            CurrentObjects.currentUser.card.withdrawMoney(moneyToPay);
                            CurrentObjects.currentUser.setTickets(result);
                            reportField.setText(reportField.getText() + result);
                            int o = Integer.parseInt(String.valueOf(Integer.parseInt(CurrentObjects.currentFilm.cinemaHall_1.get(index))));
                            int o_1 = Integer.parseInt(ticketsCount.getText());
                            int res = o - o_1;
                            CurrentObjects.currentFilm.cinemaHall_1.remove(index);
                            // CurrentObjects.currentFilm.cinemaHall_1.add(index, Integer.toString(res));
                            ObservableList<String> seats_1 = FXCollections.observableArrayList(CurrentObjects.currentFilm.cinemaHall_1);
                            CurrentObjects.hall_1.setItems(seats_1);
                            comboHall_1.setPromptText("");
                        } else {
                            labelError.setText("No seats!");
                        }
                    }
                    if (hallNumberIndex == 2) {
                        if (Integer.parseInt(CurrentObjects.currentFilm.cinemaHall_2.get(index))
                                > Integer.parseInt(ticketsCount.getText()) && comboHall_1.getValue()!=null) {
                            String result = "#" + countReports + "\nВы купили "
                                    + ticketsCount.getText() + " билета\nна фильм: "
                                    + filmName.getText() + "\nПо цене: " + textToPay.getText() + "\nВремя сеанса: "
                                    + timesComboBox.getValue() + "\n";
                            CurrentObjects.currentUser.card.withdrawMoney(moneyToPay);
                            CurrentObjects.currentUser.setTickets(result);
                            reportField.setText(reportField.getText() + result);
                            CurrentObjects.currentFilm.cinemaHall_2.remove(index);
                            /* CurrentObjects.currentFilm.cinemaHall_2.add(index, Integer.toString(
                                    Integer.parseInt(String.valueOf(Integer.parseInt(CurrentObjects.
                                            currentFilm.cinemaHall_2.get(index)) - Integer.parseInt(ticketsCount.getText())))));*/
                            System.out.println( CurrentObjects.currentFilm.cinemaHall_2);
                            ObservableList<String> seats_2 = FXCollections.observableArrayList( CurrentObjects.currentFilm.cinemaHall_2);
                            CurrentObjects.hall_2.setItems(seats_2);
                            comboHall_2.setPromptText("");
                        }
                    }
                }

            } else if (moneyToPay > CurrentObjects.currentUser.card.getMoney()){
                reportField.setText(reportField.getText() + "#" + countReports + "\nНе хватает денег на карте!\n");
            } else if (timesComboBox.getValue() == null) {
                reportField.setText(reportField.getText() + "#" + countReports +  "\nВыберите время сеанса!\n");
            }
            textToPay.setText("");
            ticketsCount.setText("");
            hallNumber.setText("");
            countReports++;
        } catch (Exception exception){
            labelError.setText("Invalid input!");
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
        closeWindow(backButton, "register.fxml");
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
        stage.setTitle("Регистрация и вход");
        stage.show();
    }
}
