package ui.jfx.controller;

// Standard Library
import java.math.BigDecimal;

// Imports
import fareye.Account;
import ui.jfx.Global;
import ui.jfx.components.TransactionButton;

// JavaFX
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Transfer {
    // TODO:
    // - Make hstory widget
    // - Readd dashboard timer widget displaying account balance, name, etc
    // - Add http call to fareye-server database
    // - Add icons and themeing
    // - Remake more barebones version in Swing?
    // - Finish fareye-server
    // - Remove old dead code
    // - Finish unit tests

    // Template Fields
    @FXML private AnchorPane ap_transfer;
    @FXML private ComboBox<String> cb_transfer;
    @FXML private TransactionButton tb_transfer;
    @FXML private HBox hb_transfer_root;
    @FXML private Label lbl_transfer;
    @FXML private TextField tf_pass;
    @FXML private TextField tf_pin;
    @FXML private VBox vb_transfer_root;

    private Account getTarget() {
        var targetPin = tf_pin.getText();
        var targetPass = tf_pass.getText();
        // Retrieve the other fields
        // TODO: Complete http request here to retrieve account
        // TODO: Catch NumberFormatException
        var target = new Account("", "", "", targetPass, Integer.parseInt(targetPin), BigDecimal.valueOf(0));
        return target;
    }

    /** Transfers funds from our account to theirs */
    private void transferTo() {
        // TODO: Fix duplication with another callback function
        var logger = Global.getLogger();
        var acct = Global.getAcct();

        var amt = tb_transfer.getAmount();

        var target = getTarget();
        acct.transferTo(amt, target);

        logger.debug("Account Balance: " + Global.currencyFormat(acct.getBalance()));
        logger.debug("Target Balance: " + Global.currencyFormat(target.getBalance()));
    }

    /** Transfers funds from their account to ours */
    private void transferFrom() {
        var logger = Global.getLogger();
        var acct = Global.getAcct();

        var amt = tb_transfer.getAmount();

        var target = getTarget();
        target.transferTo(amt, acct);

        logger.debug("Account Balance: " + Global.currencyFormat(acct.getBalance()));
        logger.debug("Target Balance: " + Global.currencyFormat(target.getBalance()));
    }

    @FXML
    public void initialize() {
        var list = FXCollections.observableArrayList("Transfer To", "Transfer From");
        cb_transfer.setItems(list);

        // Register custom callbacks
        cb_transfer.valueProperty().addListener((obs, from, to) -> {
            // Remove old callbacks on button
            this.tb_transfer.getButton().setOnMouseClicked(null);

            // Switch between transfer from or transfer to
            if (to == "Transfer To")
                this.tb_transfer.getButton().setOnMouseClicked(e -> { transferTo(); });
            else if (to == "Transfer From")
                this.tb_transfer.getButton().setOnMouseClicked(e -> { transferFrom(); });
        });

        // Transfer funds to other accounts by default
        cb_transfer.valueProperty().set(list.get(0));
    }
}
