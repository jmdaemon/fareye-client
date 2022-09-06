package ui.jfx.controller;

// Standard Library
import java.math.BigDecimal;
import java.lang.NumberFormatException;

// Imports
//import ui.jfx.components.TransactionButton;
import ui.jfx.Global;
import fareye.Account;

// JavaFX
import javafx.collections.FXCollections;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ui.jfx.components.EnterField;

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
    @FXML private Button btn_transfer;
    //@FXML private ComboBox<?> cb_transfer;
    @FXML private ComboBox<String> cb_transfer;
    @FXML private EnterField ef_transfer; // TODO: Replace this with transaction button
    @FXML private HBox hb_transfer_amount;
    @FXML private HBox hb_transfer_choice;
    @FXML private HBox hb_transfer_root;
    @FXML private Label lbl_transfer;
    @FXML private TextField tf_pass;
    @FXML private TextField tf_pin;
    @FXML private VBox vb_transfer_root;

    private void transferTo() {
        var logger = Global.getLogger();
        var acct = Global.getAcct();
        var amount = ef_transfer.getText();

        var amt = BigDecimal.valueOf(0);
        try {
            amt = BigDecimal.valueOf(Integer.parseInt(amount));
            logger.debug("Amount Entered: " + amt);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

        // Get the other account
        var targetPin = tf_pin.getText();
        var targetPass = tf_pass.getText();

        // Retrieve the other fields
        // TODO: Complete http request here to retrieve account
        // TODO: Catch NumberFormatException
        var target = new Account("", "", "", targetPass, Integer.parseInt(targetPin), BigDecimal.valueOf(0));
        acct.transferTo(amt, target);

        logger.debug("Account Balance: " + Global.currencyFormat(acct.getBalance()));
        logger.debug("Target Balance: " + Global.currencyFormat(target.getBalance()));
    }
    private void transferFrom() { }

    @FXML
    public void initialize() {
        var list = FXCollections.observableArrayList("Transfer To", "Transfer From");
        cb_transfer.setItems(list);

        // Set default to transfer from
        cb_transfer.valueProperty().addListener((obs, from, to) -> {
            // Remove old callbacks on button
            //this.btn_transfer.removeEventHandler(MouseEvent.MOUSE_CLICKED, transferTo);
            //this.btn_transfer.removeEventHandler(MouseEvent.MOUSE_CLICKED, transferTo);
            this.btn_transfer.setOnMouseClicked(null);

            // Switch between transfer from or transfer to
            if (to == "Transfer To") {
                this.btn_transfer.setOnMouseClicked(e -> { transferTo(); });
            } else if (to == "Transfer From") {
                this.btn_transfer.setOnMouseClicked(e -> { transferFrom(); });
            }
        });
        //comboBox.setItems(
    }
}

