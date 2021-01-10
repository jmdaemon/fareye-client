package app.ui;

import app.bankAccount.*;

import javafx.application.Platform;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.InputEvent;

import javafx.fxml.FXML;
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

  @FXML void loadDashboardView(InputEvent event) { AppNavigator.loadApp(AppNavigator.DASHBOARD); }
  @FXML void loadDepositView(InputEvent event) { AppNavigator.loadApp(AppNavigator.DEPOSIT); }

  @FXML
  private void initialize() {
    setUser(AppNavigator.getUser());
    timer.setDaemon(true);
    timer.start(); 
  } 
}
