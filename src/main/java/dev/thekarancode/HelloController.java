package dev.thekarancode;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initialized..!");
    }
    /*????????????????????????????????????????????????????????????????????????????????????????????????????????????*/
    @FXML
    private TextField dayField;

    @FXML
    private TextField monthField;

    @FXML
    private TextField yearField;

    @FXML
    void keyTypedDayField(KeyEvent event) {
        try {
            int dateFieldInt = Integer.parseInt(dayField.getText());
            if (dayField.getText().length() >= 2) {
                if (!(dateFieldInt >= 1 && dateFieldInt <= 31 && dayField.getText().length() <= 2)) {
                    dayField.setText("");
                }
            }
        } catch (Exception e) {
            dayField.setText("");
        }
    }

    @FXML
    void keyTypedMonthField(KeyEvent event) {
        try {
            int dateFieldInt = Integer.parseInt(monthField.getText());
            if (monthField.getText().length() >= 2) {
                if (!(dateFieldInt >= 1 && dateFieldInt <= 12 && monthField.getText().length() <= 2)) {
                    monthField.setText("");
                }
            }
        } catch (Exception e) {
            monthField.setText("");
        }
    }

    @FXML
    void keyTypedYearField(KeyEvent event) {
        try {
            int dateFieldInt = Integer.parseInt(yearField.getText());
            if (yearField.getText().length() >= 4) {
                if (!(dateFieldInt >= 1900 && dateFieldInt <= 2100 && yearField.getText().length() <= 4)) {
                    yearField.setText("");
                }
            }
        } catch (Exception e) {
            yearField.setText("");
        }
    }


    public static int generateRandomNumber(int minValue, int maxValue) {
        if (minValue > maxValue) {
            throw new IllegalArgumentException("minValue must be less than or equal to maxValue");
        }

        Random random = new Random();
        return random.nextInt((maxValue - minValue) + 1) + minValue;
    }

    /*????????????????????????????????????????????????????????????????????????????????????????????????????????????*/
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private AnchorPane closeButton;
    @FXML
    private AnchorPane titleBar;

    @FXML
    void closeButtonClicked(MouseEvent event) {
        ((Stage) closeButton.getScene().getWindow()).close();
    }

    @FXML
    void titleBarClicked(MouseEvent event) {
        xOffset = event.getSceneX();
        yOffset = event.getSceneY();
    }

    @FXML
    void titleBarDragged(MouseEvent event) {
        Stage primaryStage = (Stage) titleBar.getScene().getWindow();
        primaryStage.setX(event.getScreenX() - xOffset);
        primaryStage.setY(event.getScreenY() - yOffset);
    }

    @FXML
    private TextField selectedPathLabel;

    @FXML
    void browseClicked(MouseEvent event) throws IOException {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("SELECT A DIRECTORY TO SAVE VCF FILE : ");

        File selectedDirectory = directoryChooser.showDialog(null);
        if (selectedDirectory != null) {
            selectedPathLabel.setText(selectedDirectory.getAbsolutePath() + selectedDirectory.getPath() + selectedDirectory.getCanonicalPath());
            selectedPathLabel.getStyleClass().remove("error");
        } else {
            selectedPathLabel.setText("NOT A VALID DIRECTORY.");
            selectedPathLabel.getStyleClass().add("error");
        }
    }


    /*????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????*/

//    @FXML
//    private ChoiceBox<Integer> dateday;
//    @FXML
//    private TextField datelabel;
//    @FXML
//    private ChoiceBox<Integer> datemonth;
//    @FXML
//    private ChoiceBox<Integer> dateyear;
//    private LocalDate DOB = LocalDate.of(2000, 3, 2);
//
//
//    @Override
//    public void initialize(URL location, ResourceBundle resources) {
//        for (int i = 1900; i <= 2100; i++) {
//            dateyear.getItems().add(i);
//        }
//        for (int i = 1; i <= 12; i++) {
//            datemonth.getItems().add(i);
//        }
//
//        dateday.setValue(2);
//        datemonth.setValue(3);
//        dateyear.setValue(2000);
//
//        dateyear.setOnAction(this::onyear);
//        datemonth.setOnAction(this::onmonth);
//        dateday.setOnAction(this::onday);
//        dateday.setDisable(true);
//    }
//
//    public void onyear(ActionEvent event) {
//        System.out.println("year : " + dateyear.getValue());
//        repopulateMonthComboBox();
//        DOB = DOB.withYear(dateyear.getValue());
//    }
//
//    public void onmonth(ActionEvent event) {
//        System.out.println("month : " + datemonth.getValue());
//        dateday.setDisable(false);
//        repopulateMonthComboBox();
//        DOB = DOB.withMonth(datemonth.getValue());
//    }
//
//    public void onday(ActionEvent event) {
//        System.out.println("in day method");
//        System.out.println("day : " + dateday.getValue());
//        DOB = DOB.withDayOfMonth(dateday.getValue());
//        datelabel.setText(DOB.toString());
//    }
//
//    public void repopulateMonthComboBox() {
//        System.out.println("in repopulate");
//        dateday.setOnAction(null);
//        dateday.getItems().clear();
//        for (int i = 1; i <= calculateDaysInMonth(datemonth.getValue(), dateyear.getValue()); i++) {
//            dateday.getItems().add(i);
//        }
//        dateday.setValue(1);
//        dateday.setOnAction(this::onday);
//    }
//
//    private int calculateDaysInMonth(int month, int year) {
//        switch (month) {
//            case 2: // February
//                return (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) ? 29 : 28;
//            case 4:
//            case 6:
//            case 9:
//            case 11: // April, June, September, November
//                return 30;
//            default:
//                return 31;
//        }
//    }

}