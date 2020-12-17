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
  private Stage rootStage;

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

  //public void setUserData(BankAccount acct) {
    //this.user = acct;
  //}

  //public void setUserData(String userName, String password) {
    //this.user = new BankAccount(userName, password);
    ////this.user = acct;
  //}
  public void setAcctNum(BankAccount acct) {
    this.acct_number.setText(String.valueOf(acct.getAcctNum()));
  }
  public void setBalance(BankAccount acct) {
    this.acct_number.setText(acct.getBalance().toString());
  }

  public void setStage(Stage stage) {
     rootStage = stage; 
     this.user = (BankAccount) stage.getUserData(); 
     //this.user = stage.getUserData(); 
     acct_number.setText(String.valueOf(this.user.getAcctNum()));
  }

  @Override
  public void initialize(URL url, ResourceBundle rb) {
    timer.setDaemon(true);
    timer.start();

    //acct_number.setOnWindowEvent(event -> {
      //Node node = (Node) event.getSource();
      //Stage stage = (Stage) node.getScene().getWindow();
      //this.user = (BankAccount) stage.getUserData();
      //acct_number.setText(String.valueOf(this.user.getAcctNum()));
    //});
    //this.user = 
  }
}
