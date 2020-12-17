package app.ui;

import app.bankAccount.*;

import java.util.ResourceBundle;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage; 
import javafx.stage.WindowEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class DashboardController implements Initializable {
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

  public void setUser(BankAccount user) {
    this.user = user;
    acct_number.setText(String.valueOf(this.user.getAcctNum()));
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    timer.setDaemon(true);
    timer.start(); 
  }
}
