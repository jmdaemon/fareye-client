package ui.jfx.controller;

// Imports
import fareye.Account;
import ui.jfx.Global;
import ui.jfx.components.PasswordRevealer;
import ui.jfx.components.NavButton;
import ui.jfx.components.filters.NaturalsFilter;

// JavaFX Imports
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Login {

    // Template fields
    @FXML private Button btn_login;
    @FXML private NavButton btn_signup;
    @FXML private HBox hb_login_status;
    @FXML private ImageView iv_avatar;
    @FXML private Text t_login_status;
    @FXML private PasswordRevealer pr_passreveal;
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
        //String s_pass = pf_pass.getText();
        String s_pass = pr_passreveal.getPasswordField().getEnterField().getText();

        // Validate the inputs
        var isUser = isValidUser(s_pin, s_pass);
        var logger = Global.getLogger();

        if (isUser) {
            // TODO: Find out how to make text green/red
            t_login_status.setText("Login Successful");
            logger.info("Login Successful");
            Account acct = Global.getAcct();
            acct.setPin(Integer.parseInt(s_pin));

            // Navigate to MainView
            logger.info("Navigating to MainView / Dashboard");

            Global.changeScene(this.vb_login_root.getScene(), Global.getFXMLPath(Global.VIEW_MAIN), "Could not load MainView");
        } else {
            t_login_status.setText("Login Failed");
        }
    }

    @FXML
    public void initialize() {
        this.tf_pin.setTextFormatter(new NaturalsFilter().getFormatter()); // Filter only valid pin numbers

        // Login using the button
        this.btn_login.setOnMouseClicked(e -> { this.login(); });

        // Login with enter KeyPress on password field
        this.pr_passreveal.getPasswordField().handleEnter(() -> { this.login(); } );
    }
}
