package dev.thekarancode.vCardelApp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class vCardelApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(vCardelApplication.class.getResource("vCardel_UI.fxml"));
        Font.loadFont("JetBrainsMonoNL-Light.ttf", 12);

        Parent root = fxmlLoader.load();
        root.setStyle("-fx-background-color: rgba(0, 0, 0, 0);");

        Scene mainScene = new Scene(root);
        mainScene.setFill(Color.TRANSPARENT);

        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("vCardel");
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}
