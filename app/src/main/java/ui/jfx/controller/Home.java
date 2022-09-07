package ui.jfx.controller;

// Standard Library
import java.io.FileInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

// Imports
import ui.jfx.ObservableAccount;
import ui.jfx.components.TimeLabel;
import ui.jfx.Global;

// JavaFX
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Home {
    // Template fields
    @FXML private AnchorPane ap_home;
    @FXML private Button btn_avatar;
    @FXML private Button btn_logout;
    @FXML private Button btn_name;
    @FXML private ComboBox<String> cb_country;
    @FXML private GridPane gp_home;
    @FXML private HBox hb_home;
    @FXML private ImageView iv_avatar;
    @FXML private ImageView iv_flag;
    @FXML private Label lbl_balance;
    @FXML private Label lbl_home;
    @FXML private Label lbl_name;
    @FXML private Label lbl_pin;
    @FXML private Label lbl_display_pin;
    @FXML private Label lbl_display_name;
    @FXML private Label lbl_display_balance;
    @FXML private Label lbl_recent;
    @FXML private TimeLabel lbl_time;
    @FXML private VBox vb_home;
    @FXML private VBox vb_info;

    // Static fields
    private static ObservableAccount acct = Global.getAcct();
    private static String DEFAULT_AVATAR_IMAGE = "/img/default-user.png";

    // Nationality Name, Nation struct
    //private static ObservableMap<String, Nation> Nationalities = FXCollections.observableHashMap();

    private ChangeListener<String> nameListener = (obs, ov, nv) -> { displayName(); };

    // Diplaying fields
    private void displayName() {
        this.lbl_name.setText(String.format("%s %s %s", acct.getFirstName(), acct.getMiddleName(), acct.getLastName()));
    }
    private void displayAvatar(Image image) { iv_avatar.setImage(image); }
    private void displayBalance() { this.lbl_balance.setText(acct.getBalance().toString()); }

    private void displayCountry(String oldCountry, String newCountry) {
        // TODO: Lookup country flag in resources and set country image
        // TODO: Convert balance from oldCountry to newCountry currency, and set balance
        // TODO: Lookup new country currency symbol
    }

    // Modifying fields
    public void changeAvatar() {
        // TODO: Implement file chooser widget, get filepath, create Image from file, displayAvatar
    }

    @FXML
    public void initialize() {
        Image image = null;
        try {
            image = new Image(DEFAULT_AVATAR_IMAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        iv_avatar.setCache(true);
        iv_avatar.imageProperty().bindBidirectional(acct.getAvatarProperty());
        iv_avatar.setVisible(true);

        // Show all the fields
        displayName();
        displayBalance();

        // Update account pin whenever it changes
        lbl_pin.textProperty().bind(acct.getPinProperty());

        // Update name label on any name changes
        acct.getFNameProperty().addListener(nameListener);
        acct.getMNameProperty().addListener(nameListener);
        acct.getLNameProperty().addListener(nameListener);

        // Update balance on change
        lbl_balance.textProperty().bindBidirectional(acct.getBalanceProperty());

        // Update avatar image
        iv_avatar.imageProperty().addListener((obs, ov, nv) -> { displayAvatar(nv); });

        // TODO: Update balance, country flag on country / nationality change
        cb_country.valueProperty().bind(acct.getCountryProperty());
        cb_country.valueProperty().addListener((obs, ov, nv) -> { displayCountry(ov, nv); });

        // TODO: Implement generic logout button
    }
}
