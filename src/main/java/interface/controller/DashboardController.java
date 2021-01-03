package app.ui;

import app.bankAccount.*;



import java.util.ResourceBundle;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.input.InputEvent;

public class DashboardController {
  private BankAccount user;

  @FXML
  private URL url;
  @FXML
  private ResourceBundle resources;

  private Thread timer = new Thread(() -> {
    Platform.runLater(()-> {
        this.date.setText(getDateAndTime()); 
    });
  }); 

  private String getDateAndTime() {
    SimpleDateFormat date = new SimpleDateFormat("EEEE h:mm a");
    return date.format(new Date());
  }

  @FXML
  private Label date;

  @FXML
  private Label acct_number;

  @FXML
  private Label balance;

  @FXML
  private Label acct_balance;

  @FXML
  private Button deposit;

  public void setUser(BankAccount user) {
    this.user = user;
    acct_number.setText("Account " + String.valueOf(this.user.getAcctNum()));
    acct_balance.setText(this.user.getBalance().toString());
  }

  public void loadDepositView(InputEvent event) {
    Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxmls/DepositView.fxml"));
    FXMLLoader loadOverlay = new FXMLLoader(getClass().getResource("/resources/fxmls/OverlayView.fxml"));
    GridPane root = null;
    GridPane overlay = null;
    try { 
      root = loader.load();
      overlay = loadOverlay.load();
      DepositController controller = loader.getController();
    } catch (IOException e) {
      e.printStackTrace();
    }

    overlay.getStylesheets().add(getClass().getResource("/resources/assets/Overlay.css").toExternalForm());
    root.getStylesheets().add(getClass().getResource("/resources/assets/Overlay.css").toExternalForm());
    root.getChildren().addAll(overlay);
    Scene scene = new Scene(root, 600, 400); 
    stage.setScene(scene);

  }

  //@Override
  //public void initialize(URL url, ResourceBundle rb) {
  @FXML
  private void initialize() {
    timer.setDaemon(true);
    timer.start(); 
  }
}
