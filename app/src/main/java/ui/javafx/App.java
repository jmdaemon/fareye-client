package app.ui.javafx;

import java.io.IOException;

import javafx.application.Application;
//import javafx.scene.Scene;
import javafx.scene.*;

import javafx.stage.Stage;

import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.AnchorPane;

public class App extends Application {

  @Override
  //public void start(Stage stage) throws Exception {
  public void start(Stage stage) {
    //Scene scene = new Scene(((new FXMLLoader(getClass().getResource("/resources/fxmls/LoginView.fxml"))).load()), 600, 400);
    Scene scene = null;
    try {
    scene = new Scene(((new FXMLLoader(getClass().getResource("/resources/fxmls/MainWindow.fxml"))).load()), 600, 400);
    //Scene scene = new Scene(new Group(), 600, 400);
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Load the StackPane
    StackPane stackPane = null;
    stackPane = (StackPane) scene.getRoot().getChildrenUnmodifiable().get(0);
    //try {
      //stackPane = (new FXMLLoader(getClass().getResource("/resources/fxmls/MainWindow.fxml"))).load();
    //} catch (IOException e) {
      //e.printStackTrace();
    //}

    // Load our window
    AnchorPane homePane = null;
    try {
      homePane = (new FXMLLoader(getClass().getResource("/resources/fxmls/NavButtons.fxml"))).load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    stackPane.getChildren().add(homePane);

    stage.setTitle("Fareye Financial");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
