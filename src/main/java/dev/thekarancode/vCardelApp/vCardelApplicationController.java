package dev.thekarancode.vCardelApp;


import dev.thekarancode.coreClasses.vCard;
import dev.thekarancode.coreClasses.vCardFile;
import dev.thekarancode.coreClasses.vCardNative;
import dev.thekarancode.customExceptions.vCardNotAddedException;
import dev.thekarancode.logUtilityClasses.Log;
import dev.thekarancode.logUtilityClasses.LogCategory;
import dev.thekarancode.utilityClasses.Handyman;
import dev.thekarancode.utilityClasses.JavaFX_Handyman;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

public class vCardelApplicationController implements Initializable {
    /*
    ┬ ┬┬  ┌┐┌┌─┐┌┬┐┌─┐┌─┐
    │ ││  ││││ │ ││├┤ └─┐
    └─┘┴  ┘└┘└─┘─┴┘└─┘└─┘
    UI NODES
    */
    @FXML
    private VBox addToVCFButton;
    @FXML
    private TextField appLog;
    @FXML
    private VBox browseButton;
    @FXML
    private AnchorPane closeButton;
    @FXML
    private VBox createVCFButton;
    @FXML
    private VBox deleteAllVCardsButton;
    @FXML
    private VBox deleteLastVCardButton;
    @FXML
    private TextField department;
    @FXML
    private TextField dob;
    @FXML
    private TextField fileDirectory;
    @FXML
    private TextField fileName;
    @FXML
    private TextField firstName;
    @FXML
    private TextField gender;
    @FXML
    private TextField homeCity;
    @FXML
    private TextField homeCountry;
    @FXML
    private TextField homePostalCode;
    @FXML
    private TextField homeState;
    @FXML
    private TextField homeStreetAddress;
    @FXML
    private TextField homeTelephoneNumber;
    @FXML
    private TextField io;
    @FXML
    private TextField labels;
    @FXML
    private TextField lastName;
    @FXML
    private TextField middleName;
    @FXML
    private TextField mobileNumberI;
    @FXML
    private TextField mobileNumberII;
    @FXML
    private VBox nextLogButton;
    @FXML
    private TextField nickname;
    @FXML
    private TextField note;
    @FXML
    private TextField organization;
    @FXML
    private TextField personalEmailAddress;
    @FXML
    private TextField preferredMobileNumber;
    @FXML
    private TextField prefix;
    @FXML
    private VBox prevLogButton;
    @FXML
    private TextField role;
    @FXML
    private TextField suffix;
    @FXML
    private TextField title;
    @FXML
    private AnchorPane titleBar;
    @FXML
    private TextField website;
    @FXML
    private TextField workCity;
    @FXML
    private TextField workCountry;
    @FXML
    private TextField workEmailAddress;
    @FXML
    private TextField workPostalCode;
    @FXML
    private TextField workState;
    @FXML
    private TextField workStreetAddress;
    @FXML
    private TextField workTelephoneNumber;
    @FXML
    private GridPane mainPane;


    /*
    ┌┬┐┬┌┬┐┬  ┌─┐  ┌┐ ┌─┐┬─┐  ┌─┐┌─┐┌┐┌┌┬┐┬─┐┌─┐┬  ┬  ┌─┐┬─┐
     │ │ │ │  ├┤   ├┴┐├─┤├┬┘  │  │ ││││ │ ├┬┘│ ││  │  ├┤ ├┬┘
     ┴ ┴ ┴ ┴─┘└─┘  └─┘┴ ┴┴└─  └─┘└─┘┘└┘ ┴ ┴└─└─┘┴─┘┴─┘└─┘┴└─
     TITLE BAR CONTROLLER
    */
    private double xCoordinate = 0;
    private double yCoordinate = 0;

    @FXML
    void mouseClickedCloseButton(MouseEvent event) {
        Platform.exit();
    }

    @FXML
    void titleBarMousePressed(MouseEvent event) {
        xCoordinate = event.getSceneX();
        yCoordinate = event.getSceneY();
    }

    @FXML
    void titleBarMouseDragged(MouseEvent event) {
        Stage mainUI = (Stage) titleBar.getScene().getWindow();
        mainUI.setX(event.getScreenX() - xCoordinate);
        mainUI.setY(event.getScreenY() - yCoordinate);
    }


    /*
    ┌┬┐┌─┐┌─┐┬ ┬┌┐ ┌─┐┌─┐┬─┐┌┬┐  ┌─┐┌─┐┌┐┌┌┬┐┬─┐┌─┐┬  ┬  ┌─┐┬─┐
     ││├─┤└─┐├─┤├┴┐│ │├─┤├┬┘ ││  │  │ ││││ │ ├┬┘│ ││  │  ├┤ ├┬┘
    ─┴┘┴ ┴└─┘┴ ┴└─┘└─┘┴ ┴┴└──┴┘  └─┘└─┘┘└┘ ┴ ┴└─└─┘┴─┘┴─┘└─┘┴└─
    DASHBOARD CONTROLLER
    */
    vCardFile vCardFile = new vCardFile();

    @FXML
    void add_To_vCard_File_Button_MouseClicked(MouseEvent event) {
        vCardNative vCardNative = new vCardNative();
        vCardNative.setIdentificationDetails(prefix.getText(), firstName.getText(), middleName.getText(), lastName.getText(), suffix.getText(), nickname.getText(), Handyman.toLocaleDateObj(dob.getText()));
        vCardNative.setCommunicationDetails(preferredMobileNumber.getText(), mobileNumberI.getText(), mobileNumberII.getText(), homeTelephoneNumber.getText(), personalEmailAddress.getText(), workTelephoneNumber.getText(), workEmailAddress.getText());
        vCardNative.setAddressDetails(homeStreetAddress.getText(), homeCity.getText(), homeState.getText(), homePostalCode.getText(), homeCountry.getText(), workStreetAddress.getText(), workCity.getText(), workState.getText(), workPostalCode.getText(), workCountry.getText());
        vCardNative.setOrganizationDetails(role.getText(), title.getText(), department.getText(), organization.getText());
        vCardNative.setNote(note.getText());
        vCardNative.setUrl(website.getText());
        vCardNative.setLabels(labels.getText().split(","));
        vCardNative.setGender(gender.getText());
        vCardFile.add_vCard(new vCard(vCardNative));
        logger.log(new Log(LogCategory.INFO, "vCard added to vCard File Successfully.", ""));
    }

    @FXML
    void delete_Last_vCard_button_MouseClicked(MouseEvent event) {
        if (vCardFile.deleteLast_vCard()) {
            logger.log(new Log(LogCategory.INFO, "Last vCard deleted Successfully", ""));
        } else {
            logger.log(new Log(LogCategory.WARNING, "vCard File is empty.", ""));
        }
    }

    @FXML
    void delete_All_vCards_button_MouseClicked(MouseEvent event) {
        if (vCardFile.deleteAll_vCard()) {
            logger.log(new Log(LogCategory.INFO, "All vCards deleted Successfully", ""));
        } else {
            logger.log(new Log(LogCategory.WARNING, "vCard File is empty.", ""));
        }
    }

    @FXML
    void browse_button_MouseClicked(MouseEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("vCardel: Select directory to save vCard file - ");
        File selectedDirectory = directoryChooser.showDialog(null);

        if (selectedDirectory != null) {
            Path fileDirectoryAsPath = Paths.get(selectedDirectory.getAbsolutePath());
            vCardFile.setFileDirectory(fileDirectoryAsPath);
            fileDirectory.setText(fileDirectoryAsPath.toAbsolutePath().toString());
            logger.log(new Log(LogCategory.INFO, "Successfully updated vCard file directory to {" + fileDirectoryAsPath.toAbsolutePath() + "}.", ""));
        } else {
            logger.log(new Log(LogCategory.WARNING, "No directory selected. Please try again.", ""));
        }
    }

    @FXML
    void create_vCard_File_button_MouseClicked(MouseEvent event) {
        try {
            vCardFile.crete_vCardFile();
            String successMessage = "Successfully created file at: " + vCardFile.getFileDirectory() + " with file name: \"" + vCardFile.getFileName() + "\"";
            logger.log(new Log(LogCategory.INFO, successMessage, ""));
        } catch (IOException | vCardNotAddedException e) {
            String errorMessage = "Error occurred during file creation: " + e.getMessage();
            logger.log(new Log(LogCategory.ERROR, errorMessage, e.toString()));
        }
    }


    /*
    ┌─┐┌─┐┌─┐  ┬  ┌─┐┌─┐  ┌─┐┌─┐┌┐┌┌┬┐┬─┐┌─┐┬  ┬  ┌─┐┬─┐
    ├─┤├─┘├─┘  │  │ ││ ┬  │  │ ││││ │ ├┬┘│ ││  │  ├┤ ├┬┘
    ┴ ┴┴  ┴    ┴─┘└─┘└─┘  └─┘└─┘┘└┘ ┴ ┴└─└─┘┴─┘┴─┘└─┘┴└─
    APP LOG CONTROLLER
    */
    vCardelApplicationLogger logger;

    @FXML
    void nextLogButton_MouseClicked(MouseEvent event) {
        logger.displayNextLog();
    }

    @FXML
    void prevLogButton_MouseClicked(MouseEvent event) {
        logger.displayPrevLog();
    }


    /*
    ┬┌┐┌┬┌┬┐┬┌─┐┬  ┬┌─┐┌─┐┌┬┐┬┌─┐┌┐┌
    │││││ │ │├─┤│  │┌─┘├─┤ │ ││ ││││
    ┴┘└┘┴ ┴ ┴┴ ┴┴─┘┴└─┘┴ ┴ ┴ ┴└─┘┘└┘
    INITIALIZATION
    */
    JavaFX_Handyman jfx = new JavaFX_Handyman();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logger = new vCardelApplicationLogger(appLog);

        fileName.setText(vCardFile.getFileName());
        fileDirectory.setText(vCardFile.getFileDirectory());

        setTextFormattersByStyleClass(jfx.getAllDescendentList(mainPane));

        addFocusedPropertyListener_ValidateDate(dob);
        addFocusedPropertyListener_ValidateEmail(personalEmailAddress);
        addFocusedPropertyListener_ValidateEmail(workEmailAddress);

        fileName.setTextFormatter(new TextFormatter<>((change) -> {
            if ((change.getText().matches("[^\\u005C\\u002F\\u003A\\u002A\\u003F\\u0022\\u003C\\u003E\\u007C]+") || !change.isAdded()) && change.getControlNewText().length() <= 30) {
                return change;
            }
            return null;
        }));
        addFocusedPropertyListener_FileName(fileName);

        logger.logSessionStart();
        logger.log(new Log(LogCategory.INFO, "vCardel UI initialized.", ""));

        System.out.println("Process Completed : vCardel started.");
    }

    public void setTextFormattersByStyleClass(List<Node> nodeList) {
        for (Node node : nodeList) {
            if (node instanceof TextField) {
                if (node.getStyleClass().contains("vCardelNormalTextField")) {
                    ((TextField) node).setTextFormatter(new TextFormatter<>((change) -> {
                        if ((!jfx.hasUnsupportedChar(change.getText()) || !change.isAdded()) && change.getControlNewText().length() <= 101) {
                            return change;
                        }
                        return null;
                    }));
                } else if (node.getStyleClass().contains("vCardelNormalNumberField")) {
                    ((TextField) node).setTextFormatter(new TextFormatter<>((change) -> {
                        if ((change.getText().matches("[\\u002B\\u002D\\u0028\\u0029 0-9]+") || !change.isAdded()) && change.getControlNewText().length() <= 30) {
                            return change;
                        }
                        return null;
                    }));
                } else if (node.getStyleClass().contains("vCardelNormalEmailField")) {
                    ((TextField) node).setTextFormatter(new TextFormatter<>((change) -> {
                        if ((change.getText().matches("[\\u002B\\u002D\\u005F\\u002E\\u0040A-Za-z0-9]+") || !change.isAdded()) && change.getControlNewText().length() <= 101) {
                            return change;
                        }
                        return null;
                    }));
                } else if (node.getStyleClass().contains("vCardelNormalDateField")) {
                    ((TextField) node).setTextFormatter(new TextFormatter<>((change) -> {
                        if ((change.getText().matches("[0-9/]") || !change.isAdded()) && change.getControlNewText().length() <= 10) {
                            return change;
                        }
                        return null;
                    }));
                } else if (node.getStyleClass().contains("vCardelNormalGenderField")) {
                    ((TextField) node).setTextFormatter(new TextFormatter<>((change) -> {
                        if ((change.getText().matches("[MFOmfo]") || !change.isAdded()) && change.getControlNewText().length() <= 1) {
                            change.setText(change.getText().toUpperCase());
                            return change;
                        }
                        return null;
                    }));
                } else if (node.getStyleClass().contains("vCardelNormalNoteField")) {
                    ((TextField) node).setTextFormatter(new TextFormatter<>((change) -> {
                        if ((!jfx.hasUnsupportedChar(change.getText()) || !change.isAdded()) && change.getControlNewText().length() <= 202) {
                            return change;
                        }
                        return null;
                    }));
                }
            }
        }
        System.out.println("Task completed, Text formatters have been set according to style classes.");
    }

    public void addFocusedPropertyListener_ValidateDate(TextField textField) {
        textField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {

                String textFieldValue = textField.getText();

                if (textFieldValue.matches("[0-9]{2}/[0-9]{2}(/[0-9]{4})?")) {

                    String[] dobSegStrArray = textFieldValue.split("/");
                    int[] dobSegIntArray = new int[dobSegStrArray.length];

                    for (int i = 0; i < dobSegIntArray.length; i++) {
                        dobSegIntArray[i] = Integer.parseInt(dobSegStrArray[i]);
                    }

                    boolean isValidDate = switch (dobSegIntArray.length) {
                        case 2 -> Handyman.isValidDate(dobSegIntArray[0], dobSegIntArray[1]);
                        case 3 -> Handyman.isValidDate(dobSegIntArray[0], dobSegIntArray[1], dobSegIntArray[2]);
                        default -> false;
                    };

                    if (isValidDate) {
                        logger.log(new Log(LogCategory.INFO, (textFieldValue + " is a valid date."), ""));
                    } else {
                        textField.clear();
                        logger.log(new Log(LogCategory.WARNING, (textFieldValue + " is not a valid date."), ""));
                    }

                } else {
                    textField.clear();
                    logger.log(new Log(LogCategory.ERROR, (textFieldValue + " is not a valid date format."), ""));
                }
            }
        });
        System.out.println("Task completed, Focused property listeners have been added for date validation: Text Field ID - " + textField.getId());
    }

    public void addFocusedPropertyListener_ValidateEmail(TextField textField) {
        textField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                String textFieldValue = textField.getText();
                if (textFieldValue.matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
                    logger.log(new Log(LogCategory.INFO, textFieldValue + " is a valid email format.", ""));
                } else {
                    textField.clear();
                    logger.log(new Log(LogCategory.ERROR, textFieldValue + " is not a valid email format.", ""));
                }
            }
        });
        System.out.println("Task completed, Focused property listener have been added for email validation: Text Field ID - " + textField.getId());
    }

    public void addFocusedPropertyListener_FileName(TextField textField) {
        textField.focusedProperty().addListener((o, oldVal, newVal) -> {
            if (!newVal) {
                String textFieldValue = textField.getText();
                if (!textFieldValue.isBlank()) {
                    logger.log(new Log(LogCategory.INFO, ("\"" + textFieldValue + "\" is set as file name."), ""));
                    vCardFile.setFileName(textField.getText());
                } else {
                    fileName.setText(vCardFile.getFileName());
                    logger.log(new Log(LogCategory.WARNING, ("Please provide a valid file name. Resetting the file name to: \"" + vCardFile.getFileName() + "\""), ""));
                }
            }
        });
        System.out.println("Task completed, Focused property listener have been added for file Name : Text Field ID - " + textField.getId());
    }

}