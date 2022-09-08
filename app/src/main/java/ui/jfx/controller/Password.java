package ui.jfx.controller;

// Imports
import fareye.Account;
import ui.jfx.Global;

// JavaFX
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Password {

    // Template Fields
    @FXML private AnchorPane ap_pass;
    @FXML private Button btn_pass;
    @FXML private HBox hb_pass;
    @FXML private Label lbl_pass;
    @FXML private TextField tf_current;
    @FXML private TextField tf_new;
    @FXML private TextField tf_pin;
    @FXML private VBox vb_pass;

    private void changePassword() {
        var acct = Global.getAcct();
        var currentPass = tf_current.getText();
        var newPass = tf_new.getText();
        acct.changePassword(currentPass, newPass);
    }

    @FXML
    public void initialize() {
        this.btn_pass.setOnMouseClicked(e -> { changePassword(); });
    }
}
