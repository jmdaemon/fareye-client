package ui.jfx.controller;

// Standard Library
import java.math.BigDecimal;

// Imports
import fareye.Account;
import ui.jfx.Global;

// JavaFX
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;

public class Signup {

    // Template fields
    @FXML private AnchorPane ap_signup;
    @FXML private Button btn_login;
    @FXML private Button btn_signup;
    @FXML private GridPane gp_signup;
    @FXML private HBox hb_signup;
    @FXML private Label lbl_signup;
    @FXML private TextField tf_fname;
    @FXML private TextField tf_lname;
    @FXML private TextField tf_mname;
    @FXML private TextField tf_pass;
    @FXML private Label tf_status;
    @FXML private VBox vb_signup;

    private void signup() {
        var logger = Global.getLogger();

        var fname = this.tf_fname.getText();
        var mname = this.tf_mname.getText();
        var lname = this.tf_lname.getText();
        var pass = this.tf_pass.getText();

        //var acct = new Account(fname, mname, lname);
        //var pin =
        //var pass = Account.generatePassword(Account.DEFAULT_PASS_LENGTH);
        var pin = Account.generatePin(Account.MAX_ACCTNUM_LENGTH);
        var bal = BigDecimal.valueOf(0);
        var acct = new Account(fname, mname, lname, pass, pin, bal);
        logger.info("New Account Created");
        this.tf_status.setText("Account Creation Successful!\nYour new account pin is: #" + pin);
        Global.setAcct(acct);
    }

    public void toLoginPage() {
        var logger = Global.getLogger();
        Global global = new Global();
        AnchorPane root = (AnchorPane) global.loadFXML("/fxmls/LoginView.fxml");

        if (root != null) {
            Scene scene = this.ap_signup.getScene();
            scene.setRoot(root);
        } else {
            logger.error("Could not load Login Page");
        }
    }

    @FXML
    public void initialize() {
        this.btn_signup.setOnMouseClicked(e -> { signup(); });
        this.btn_login.setOnMouseClicked(e -> { this.toLoginPage(); });
    }
}
