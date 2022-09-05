package ui.jfx.controller;

// Imports
import ui.jfx.components.TransactionButton;
import ui.jfx.Global;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Withdraw {

    // Template fields
    @FXML private AnchorPane ap_withdraw;
    @FXML private TransactionButton tb_withdraw;
    @FXML private VBox vb_withdraw;

    @FXML
    public void initialize() {
        this.tb_withdraw.getButton().setOnMouseClicked(e -> {
            var logger = Global.getLogger();
            var amt = tb_withdraw.getAmount();

            if (!TransactionButton.isZero(amt)) {
                var acct = Global.getAcct();
                acct.withdraw(amt);

                // Since we cannot actually see the bank account updates yet, this will do for now.
                logger.debug("Account Balance: " + Global.currencyFormat(acct.getBalance()));
            }
        });
    }


}

