package ui.jfx;

// Standard Library
import java.io.IOException;

// JavaFX Imports
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) throws IOException {
    var loader = (new FXMLLoader(getClass().getResource("/fxmls/LoginView.fxml")));
    var root = (VBox) loader.load();
    stage.setTitle("Fareye Financial Client");
    stage.setScene(new Scene(root, 860, 640));
    stage.show();
  }
}
