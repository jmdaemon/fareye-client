package app.ui;

import app.bankAccount.*;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;

public class DepositController {
  @FXML
  private OverlayController overlayController;

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

  //private Thread updateAccount = new Thread(()-> { 
    ////BankAccount acct = AppNavigator.getUser();
       //Platform.runLater(() -> { 
         //double amount = Double.parseDouble(depositAmount.getText()); 
         //(Context.getInstance().currentUser()).deposit(amount);
       //});
  //});
   private Thread updateAccount = new Thread(() -> { 
     double amount = Double.parseDouble(depositAmount.getText()); 
     Platform.runLater(() -> { 
       (Context.getInstance().currentUser()).deposit(amount); 
     });
   });

  public void depositMoney(InputEvent event) { 
    updateAccount.start();
    //updateAccount.start();
    //Platform.runLater(() -> {
      //Navigator.loadScene(event, Navigator.DEPOSIT);
    //});
  }

  @FXML
  public void initialize() {
  }
}
