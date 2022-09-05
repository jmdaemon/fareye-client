package ui.jfx.controller;

// Imports
import fareye.Account;
import ui.jfx.Global;

// Standard Library
import java.io.IOException;

// JavaFX Imports
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.text.Text;

public class Login {

    // Template fields
    @FXML private Button btn_login;
    @FXML private HBox hb_login_status;
    @FXML private ImageView iv_avatar;
    @FXML private Text t_login_status;
    @FXML private TextField tf_pass;
    @FXML private TextField tf_pin;
    @FXML private VBox vb_login;
    @FXML private VBox vb_login_root;
    @FXML private MenuBar vb_menubar;

    private static boolean isValidUser(String pin, String pass) {
        boolean isUser = true;
        // TODO: Finish validation function
        // - Send http request
        // - If request shows no results
        // - Return false
        // - If there is a result
        // - Check the pin, pass
        // - If match, navigate to main layout,
        // - Else update failed status.
        return isUser;
    }

    public void login() {
        // Parse the inputs
        String s_pin = tf_pin.getText();
        String s_pass = tf_pass.getText();

        // Validate the inputs
        var isUser = isValidUser(s_pin, s_pass);

        if (isUser) {
            System.out.println("Login Successful");
            //Account acct = new Account("", "", "");
            Account acct = Global.getAcct();
            acct.setPin(Integer.parseInt(s_pin));

            // Navigate to MainView
            System.out.println("Navigating to MainView / Dashboard");

            // Load function
            var loader = (new FXMLLoader(getClass().getResource("/fxmls/MainView.fxml")));
            VBox root = null;
            try {
                root = (VBox) loader.load();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }

            Scene scene = this.vb_login_root.getScene();
            scene.setRoot(root);
        } else {
            t_login_status.setText("Login Failed");
            }
        }

    @FXML
    public void initialize() {
        // Login using the button
        this.btn_login.setOnMouseClicked(e -> {
            this.login();
        });

        // Login with enter KeyPress on password field
        this.tf_pass.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                this.login();
            }
        });
    }
}
