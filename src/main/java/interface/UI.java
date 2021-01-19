package app.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

// https://gist.github.com/jewelsea/6460130
public class UI extends Application {
  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Cognito Banking");
    //primaryStage.setScene(createScene(loadMainPane())); 
    Scene scene = new Scene(((new FXMLLoader(getClass().getResource("/resources/fxmls/LoginView.fxml"))).load()), 600, 400); 
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  //private Pane loadMainPane() throws IOException { 
    //FXMLLoader loader = new FXMLLoader(); 
    //Pane mainPane = (Pane) loader.load(getClass().getResourceAsStream(AppNavigator.MAIN));

    //MainController mainController = loader.getController();

    //AppNavigator.setMainController(mainController);
    //AppNavigator.loadApp(AppNavigator.LOGIN);

    //return mainPane;
  //}

  //private Scene createScene(Pane mainPane) { 
    //Scene scene = new Scene(mainPane); 
    ////scene.getStylesheets().setAll(getClass().getResource("Overlay.css").toExternalForm()); 
    //return scene;
  //}
  
  public static void main(String[] args) {
    launch();
  }

}
