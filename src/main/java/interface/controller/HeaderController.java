package app.ui;

import app.bankAccount.*;

import javafx.application.Platform;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.lang.InterruptedException; 

import javafx.scene.control.ListView;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import org.kordamp.ikonli.*;
import org.kordamp.ikonli.javafx.*;
import org.kordamp.ikonli.fontawesome5.*; 

public class HeaderController {
  //@FXML
  //private BankAccount user;
  @FXML
  private ListView<BankAccount> accountsList;

  private Account accounts = new Account();

  @FXML
  private Label date;

  @FXML
  private Label balance;

  @FXML
  private Label acct_number;

  @FXML
  private Label acct_balance;

  private String getDateAndTime() {
    SimpleDateFormat date = new SimpleDateFormat("EEEE h:mm a");
    return date.format(new Date());
  }

  //public void setUser(BankAccount user) {
    //this.user = user;
    //acct_number.setText(String.valueOf(this.user.getAcctNum()));
    //acct_balance.setText(this.user.getBalance().toString());
  //}

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

  @FXML
  private void initialize() {
    //setUser(Context.getInstance().currentUser()); 
    accounts.addAccount(Context.getInstance().currentUser());
    accountsList = new ListView<BankAccount>(accounts.getAccounts());
    accountsList.setItems(accounts.getAccounts());
    timer.setDaemon(true);
    timer.start(); 
  } 

}
