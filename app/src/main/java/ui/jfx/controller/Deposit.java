package ui.jfx.controller;

// Imports
import ui.jfx.components.TransactionButton;
import ui.jfx.Global;

// JavaFX
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Deposit {

    // Template fields
    @FXML private AnchorPane ap_deposit;
    @FXML private VBox vb_deposit;
    @FXML private TransactionButton tb_deposit;

    private void deposit() {
        var logger = Global.getLogger();
        var amt = tb_deposit.getAmount();

        if (!TransactionButton.isZero(amt)) {
            var acct = Global.getAcct();
            acct.deposit(amt);

            // Since we cannot actually see the bank account updates yet, this will do for now.
            logger.debug("Account Balance: " + Global.currencyFormat(acct.getBalance()));
        }
    }

    @FXML
    public void initialize() {
        this.tb_deposit.getButton().setOnMouseClicked(e -> { deposit(); });
        this.tb_deposit.getEnterField().handleEnter(() -> { deposit(); });
    }
}

