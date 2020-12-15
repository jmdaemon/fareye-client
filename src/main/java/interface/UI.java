package app.ui;

//import java.util.HashMap;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.layout.Pane; 
import javafx.scene.layout.TilePane; 
import javafx.scene.layout.BorderPane; 
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane; 
import javafx.scene.layout.ColumnConstraints; 
import javafx.scene.layout.RowConstraints; 
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.geometry.Pos;

public class UI extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
      primaryStage.setTitle("My First JavaFX App");
      //FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxmls/LoginView.fxml"));
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
