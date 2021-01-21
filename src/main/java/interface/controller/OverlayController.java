package app.ui;

import app.bankAccount.*;

import javafx.application.Platform;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.InterruptedException; 

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.InputEvent;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

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
    acct_number.setText(String.valueOf(this.user.getAcctNum()));
    acct_balance.setText(this.user.getBalance().toString());
  }

  private volatile boolean enough = false;
  private Thread timer = new Thread(() -> {
    while (!enough) {
      try {
        Thread.sleep(1000); 
      } catch (InterruptedException e) {} 
      Platform.runLater(()-> { 
        this.date.setText(getDateAndTime()); 
      });
    }
  }); 

  String loadScreen = null;

  private Thread update = new Thread(() -> {
    Platform.runLater(() -> {
    //AppNavigator.loadApp(loadScreen);
    });
  });

  private void refreshScreen(String fxml) { 
    loadScreen = fxml;
    update.start(); 
  }

  @FXML public void loadDashboardView(InputEvent event) { Navigator.loadScene(event, Navigator.DASHBOARD); }
  @FXML public void loadDepositView(InputEvent event) { Navigator.loadScene(event, Navigator.DEPOSIT); }
  
  @FXML
  private void initialize() {
    //setUser(AppNavigator.getUser());
    //setUser(Context.getInstance().currentUser());
    timer.setDaemon(true);
    timer.start(); 
  } 
}

