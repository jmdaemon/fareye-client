package ui.jfx.controller;

// Imports
import ui.jfx.components.TransactionButton;
import ui.jfx.Global;

// Standard Library
//import java.math.BigDecimal;
//import java.lang.NumberFormatException;

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
            var amt = tb_deposit.getAmount();
            logger.debug("Amount Entered: " + amt);

            if (!TransactionButton.isZero(amt)) {
                var acct = Global.getAcct();
                acct.deposit(amt);

                // Since we cannot actually see the bank account updates yet, this will do for now.
                logger.debug("Account Balance: " + Global.currencyFormat(acct.getBalance()));
            }
        });
    }
}

