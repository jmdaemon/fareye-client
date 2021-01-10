package app.ui;

import app.bankAccount.*;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class DepositController {

  @FXML
  private Button backButton;

  @FXML
  private TextField depositAmount;

  @FXML
  private Label depositAmtLabel;

  @FXML
  private Button depositButton;

  @FXML
  private Label currency;

  private Thread updateAccount = new Thread(()-> { 
    //BankAccount acct = AppNavigator.getUser();
       Platform.runLater(() -> { 
         double amount = Double.parseDouble(depositAmount.getText()); 
         (AppNavigator.getUser()).deposit(amount);        
         AppNavigator.loadApp(AppNavigator.DEPOSIT);
         //acct.deposit(amount);
       });
  });

  public void depositMoney() {
    updateAccount.start();
  }
}
