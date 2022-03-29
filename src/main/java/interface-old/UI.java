package app.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class UI extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Fareye Financial");
    Scene scene = new Scene(((new FXMLLoader(getClass().getResource("/resources/fxmls/LoginView.fxml"))).load()), 600, 400); 
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch();
  }

}
