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
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxmls/DashboardView.fxml"));
      GridPane root = loader.load();
      root.getStylesheets().add(getClass().getResource("/resources/assets/Dashboard.css").toExternalForm());

      Scene scene = new Scene(root, 600, 400);

      primaryStage.setScene(scene);
      primaryStage.show();
      //ScenicView.show(scene);
    }

    public static void main(String[] args) {
      launch();
    }

}
