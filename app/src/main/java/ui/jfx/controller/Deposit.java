package ui.jfx.controller;

// Imports
import ui.jfx.components.TransactionButton;
import ui.jfx.Global;

// Standard Library
import java.math.BigDecimal;
import java.lang.NumberFormatException;

// JavaFX
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Deposit {

    // Template fields
    @FXML private AnchorPane ap_deposit;
    @FXML private VBox vb_deposit;
    @FXML private TransactionButton tb_deposit;

    @FXML
    public void initialize() {
        this.tb_deposit.getButton().setOnMouseClicked(e -> {
            var logger = Global.getLogger();
            var amount = tb_deposit.getTextFieldLabel();
            var amt = BigDecimal.valueOf(0);
            // TODO: Check if amount is properly formatted
            try {
                amt = BigDecimal.valueOf(Integer.parseInt(amount));
                logger.debug("Amount Entered: " + amt);
            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }

            if (amt.compareTo(BigDecimal.valueOf(0)) != 0) {
                //Integer.parseInt(amount);
                var acct = Global.getAcct();
                acct.deposit(amt);

                // Since we cannot actually see the bank account updates yet, this will do for now.
                logger.debug("Account Balance: " + Global.currencyFormat(acct.getBalance()));
            }
        });
    }
}

