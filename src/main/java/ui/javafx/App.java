package app.javafx;

import java.io.IOException;

import javafx.application.Application;
//import javafx.scene.Scene;
import javafx.scene.*;

import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class App extends Application {

  @Override
  //public void start(Stage stage) throws Exception {
  public void start(Stage stage) {
    //Scene scene = new Scene(((new FXMLLoader(getClass().getResource("/resources/fxmls/LoginView.fxml"))).load()), 600, 400);
    Scene scene = new Scene(new Group(), 600, 400);
    stage.setTitle("Fareye Financial");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
