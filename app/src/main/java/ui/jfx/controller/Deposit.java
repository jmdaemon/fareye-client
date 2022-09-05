package ui.jfx.controller;

// Imports
import ui.jfx.Global;

// Standard Library
import java.math.BigDecimal;

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

    @FXML
    public void initialize() {
        this.btn_deposit.setOnMouseClicked(e -> {
            var logger = Global.getLogger();
            var amount = tf_amount.getText();
            var amt = BigDecimal.valueOf(Integer.parseInt(amount));
            logger.debug("Amount Entered: " + amt);

            // TODO: Check if amount is properly formatted
            // TODO: Add filtering to text field to automatically filter out invalid numbers
            //Integer.parseInt(amount);
            var acct = Global.getAcct();
            acct.deposit(amt);

            // Since we cannot actually see the bank account updates yet, this will do for now.
            logger.debug("Account Balance: " + Global.currencyFormat(acct.getBalance()));
        });
    }
}

