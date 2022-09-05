package ui.jfx.controller;

// Imports
import fareye.Account;
import ui.jfx.Global;
import java.math.BigDecimal;
import java.text.NumberFormat;

// JavaFX
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

// TODO: Implement Deposit, Withdraw as variants of a custom Composite Control
public class Deposit {

    // Template fields
    @FXML private AnchorPane ap_deposit;
    @FXML private Button btn_deposit;
    @FXML private HBox hb_deposit;
    @FXML private TextField tf_amount;
    @FXML private VBox vb_deposit;

    public static String currencyFormat(BigDecimal n) {
        return NumberFormat.getCurrencyInstance().format(n);
    }

    @FXML
    public void initialize() {
        this.btn_deposit.setOnMouseClicked(e -> {
            String amount = tf_amount.getText();

            BigDecimal amt = BigDecimal.valueOf(Integer.parseInt(amount));
            System.out.println("Amount Entered: " + amt);
            // TODO: Check if amount is properly formatted
            // TODO: Add filtering to text field to automatically filter out invalid numbers
            //Integer.parseInt(amount);
            Account acct = Global.getAcct();
            acct.deposit(amt);

            // Since we cannot actually see the bank account updates yet, this will do for now.
            //System.out.println("Account Balance: " + acct.getBalance());
            System.out.println("Account Balance: " + currencyFormat(acct.getBalance()));
            acct.getBalance().add(amt);
            System.out.println("Account Balance: " + currencyFormat(acct.getBalance()));
        });
    }
}

