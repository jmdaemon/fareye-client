package app.ui;

import app.bankAccount.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import org.kordamp.ikonli.*;
import org.kordamp.ikonli.javafx.*;
import org.kordamp.ikonli.fontawesome5.*;

public class OverlayController {
  private BankAccount user;

  @FXML
  private Button back;

  @FXML
  private Label acct_number;

  @FXML
  private Label balance;

  @FXML
  private Label acct_balance;

  @FXML
  private Label date;

  @FXML
  private Button deposit;

  @FXML
  private Button withdraw;

  private String getDateAndTime() {
    SimpleDateFormat date = new SimpleDateFormat("EEEE h:mm a");
    return date.format(new Date());
  }

  public void setUser(BankAccount user) {
    this.user = user;
    acct_number.setText("Account " + String.valueOf(this.user.getAcctNum()));
    acct_balance.setText(this.user.getBalance().toString());
  }
  private Thread timer = new Thread(() -> {
    Platform.runLater(()-> {
        this.date.setText(getDateAndTime()); 
    });
  }); 

  @FXML
  void loadDepositView(MouseEvent event) {
    Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/fxmls/DepositView.fxml"));
    GridPane root = null;
    try { 
      root = loader.load();
    } catch (IOException e) {
      e.printStackTrace();
    }
    Scene scene = new Scene(root, 600, 400); 
    scene.getStylesheets().add(getClass().getResource("/resources/assets/Overlay.css").toExternalForm());
    stage.setScene(scene);
  }

  @FXML
  private void initialize() {
    timer.setDaemon(true);
    timer.start(); 
  } 
}

