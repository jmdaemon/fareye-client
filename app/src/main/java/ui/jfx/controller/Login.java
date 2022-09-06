package ui.jfx.controller;

// Imports
import fareye.Account;
import ui.jfx.Global;
import ui.jfx.components.EnterField;

// JavaFX Imports
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Scene;
import javafx.scene.text.Text;

public class Login {

    // Template fields
    @FXML private Button btn_login;
    @FXML private Button btn_signup;
    @FXML private HBox hb_login_status;
    @FXML private ImageView iv_avatar;
    @FXML private Text t_login_status;
    @FXML private EnterField ef_pass;
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
        String s_pass = ef_pass.getText();

        // Validate the inputs
        var isUser = isValidUser(s_pin, s_pass);
        var logger = Global.getLogger();

        if (isUser) {
            logger.info("Login Successful");
            Account acct = Global.getAcct();
            acct.setPin(Integer.parseInt(s_pin));

            // Navigate to MainView
            logger.info("Navigating to MainView / Dashboard");

            // Load function
            Global global = new Global();
            VBox root = (VBox) global.loadFXML("/fxmls/MainView.fxml");

            if (root != null) {
                Scene scene = this.vb_login_root.getScene();
                scene.setRoot(root);
            } else {
                logger.error("Could not load MainView");
            }
        } else {
            t_login_status.setText("Login Failed");
            }
        }
    public void toSignupPage() {
        var logger = Global.getLogger();
        Global global = new Global();
        AnchorPane root = (AnchorPane) global.loadFXML("/fxmls/Signup.fxml");

        if (root != null) {
            Scene scene = this.vb_login_root.getScene();
            scene.setRoot(root);
        } else {
            logger.error("Could not load SignUp Page");
        }

    }

    @FXML
    public void initialize() {
        // Login using the button
        this.btn_login.setOnMouseClicked(e -> { this.login(); });

        // Login with enter KeyPress on password field
        this.ef_pass.handleEnter(() -> { this.login(); } );

        // Navigate to SignUp page from Login
        this.btn_signup.setOnMouseClicked(e -> { this.toSignupPage(); });
    }
}
