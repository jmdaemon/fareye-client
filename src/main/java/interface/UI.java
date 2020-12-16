package app.ui;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.layout.GridPane;

public class UI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
      primaryStage.setTitle("My First JavaFX App");
      Scene scene = new Scene(((new FXMLLoader(getClass().getResource("/resources/fxmls/LoginView.fxml"))).load()), 600, 400);
      primaryStage.setScene(scene);
      primaryStage.show();
    }

    public static void main(String[] args) {
      launch();
    }

}
