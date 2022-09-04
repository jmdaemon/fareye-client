//package app.ui.javafx;
//package app.ui.jfx;
package jfx;

//import app.ui.javafx.NavButtonsComponent;
//import app.ui.javafx.controller.AppController;
//import app.ui.jfx.controller.AppController;
//import jfx.controller.AppController;
import jfx.controller.Main;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.*;

import javafx.stage.Stage;

import javafx.scene.Group;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.AnchorPane;

public class App extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  //public void start(Stage primaryStage) throws IOException {
  public void start(Stage stage) throws IOException {
    // Load app
    //Scene scene = null;

    //var loader = (new FXMLLoader(getClass().getResource("/fxmls/MainWindow.fxml")));

    //var app = new Main();
    //loader.setController(app);

    var loader = (new FXMLLoader(getClass().getResource("/fxmls/MainView.fxml")));
    var root = (VBox) loader.load();
    stage.setTitle("Fareye Financial Client");
    stage.setScene(new Scene(root, 860, 640));
    stage.show();
    //loader.load();



    //var app = new AppController();

    // Create main
    //Scene scene = null;
    //FXMLLoader loader = (new FXMLLoader(getClass().getResource("/fxmls/MainWindow.fxml")));
    //loader.setController(app);
    //var root = (BorderPane) loader.load();
    //loader.load();

    //primaryStage.setTitle("Fareye Financial Client");
    //primaryStage.setScene(new Scene(root, 600, 400));
    //stage.show();

    //try {
    //scene = new Scene(((new FXMLLoader(getClass().getResource("/fxmls/MainWindow.fxml"))).load()), 600, 400);
    //} catch (IOException e) {
      //e.printStackTrace();
    //}

    //var mw = new MainWindow();
    ////Scene scene = new Scene(new MainWindow());
    //Scene scene = new Scene(mw);
    //var navButtonsComponent = new NavButtonsComponent();
    //Scene scene = new Scene(navButtonsComponent);

    // Load the StackPane
    //StackPane stackPane = null;
    ////stackPane = (StackPane) scene.getRoot().getChildrenUnmodifiable().get(0);
    //stackPane = mw.getStackPane();

    // Load our main window
    //AnchorPane homePane = null;
    //try {
      //homePane = (new FXMLLoader(getClass().getResource("/fxmls/NavButtons.fxml"))).load();
    //} catch (IOException e) {
      //e.printStackTrace();
    //}

    //NavButtonsComponent navButtonsComponent = new NavButtonsComponent();
    ////stackPane.getChildren().add((Node) navButtonsComponent);
    //stackPane.getChildren().add(navButtonsComponent);
    //navButtonsComponent.show();

    //stage.setTitle("Fareye Financial");
    //stage.setScene(scene);
    //stage.show();
  }
}
