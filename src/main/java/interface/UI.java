package app.ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class UI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
      primaryStage.setTitle("My First JavaFX App");
      //Scene scene = new Scene(((new FXMLLoader(getClass().getResource("/resources/fxmls/LoginView.fxml"))).load()), 600, 400);
      //primaryStage.setScene(scene);
      //primaryStage.show();

      primaryStage.setScene(createScene(loadMainPane()));
      primaryStage.show();
    }

    //private GridPane loadMainPane() throws IOException { 
    private Pane loadMainPane() throws IOException { 
      FXMLLoader loader = new FXMLLoader(); 
      //System.out.println(AppNavigator.LOGIN);
      //GridPane mainPane = (GridPane) loader.load(getClass().getResourceAsStream(AppNavigator.LOGIN));
      //GridPane mainPane = (GridPane) loader.load(getClass().getResource(AppNavigator.LOGIN));
      //GridPane mainPane = (GridPane) loader.load(getClass().getResource(AppNavigator.LOGIN));
      //GridPane mainPane = (GridPane) loader.load(getClass().getResourceAsStream(AppNavigator.LOGIN));
      //GridPane mainPane = (GridPane) loader.load(getClass().getResourceAsStream(AppNavigator.MENU_BAR));
      //GridPane mainPane = (GridPane) loader.load(getClass().getResourceAsStream(AppNavigator.MAIN));
      Pane mainPane = (Pane) loader.load(getClass().getResourceAsStream(AppNavigator.MAIN));

      //MainController mainController = (MainController) loader.getController();
      MainController mainController = loader.getController();

      AppNavigator.setMainController(mainController);
      AppNavigator.loadApp(AppNavigator.LOGIN);

      return mainPane;
    }

    //private Scene createScene(GridPane mainPane) { 
    private Scene createScene(Pane mainPane) { 
      Scene scene = new Scene(mainPane); 

      //scene.getStylesheets().setAll(getClass().getResource("Overlay.css").toExternalForm()); 
      return scene;
    }
    
    public static void main(String[] args) {
      launch();
    }

}
