package ui.jfx.controller;

// Standard Library
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

// Imports
//import ui.jfx.ObservableAccount;
import ui.jfx.Global;

// JavaFX
import javafx.application.Platform;
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
import javafx.beans.value.ChangeListener;
//import javafx.beans.binding.Binding;
//import javafx.beans.binding.Bindings;
//import javafx.beans.property.StringProperty;
//import javafx.beans.property.SimpleStringProperty;

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
    @FXML private Label lbl_time;
    @FXML private VBox vb_home;
    @FXML private VBox vb_info;

    private ChangeListener<String> nameListener = (obs, ov, nv) -> { displayName(); };
    //private ChangeListener<String> avatarListener = (obs, ov, nv) -> { displayAvatar(); };

    // Displaying changes
    private void displayName() {
        var acct = Global.getAcct();
        this.lbl_name.setText(String.format("%s %s %s",
                    acct.getFirstName(), acct.getMiddleName(), acct.getLastName()));
        //var fullName = acct.getFirstName() + " " + acct.getMiddleName() + " " + acct.getLastName();
        //this.lbl_name.setText(fullName);
        //this.lbl_name.setText(String.format("%s %s %s",
//));
    }

    private void displayAvatar(Image image) {
        iv_avatar.setImage(image);
    }

    private void displayBalance() {
        var acct = Global.getAcct();
        this.lbl_balance.setText(acct.getBalance().toString());
    }

    private void displayCountry(String oldCountry, String newCountry) {
        // TODO: Lookup country flag in resources and set country image
        // TODO: Convert balance from oldCountry to newCountry currency, and set balance
        // TODO: Lookup new country currency symbol
    }

    // Modifying properties
    public void changeAvatar() {
        // TODO: Implement file chooser widget, get filepath, create Image from file, displayAvatar
    }

    private String getDateAndTime() {
        SimpleDateFormat date = new SimpleDateFormat("EEEE h:mm a");
        return date.format(new Date());
    }

    private volatile boolean enough = false;
    private Thread timer = new Thread(() -> {
        while (!enough) {
            Platform.runLater(()-> {
                this.lbl_time.setText(getDateAndTime());
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { }
        }
    });
    private static String DEFAULT_AVATAR_IMAGE = "/img/default-user.png";

    @FXML
    public void initialize() {
        //InputStream stream = null;
        //getClass().getResource(DEFAULT_AVATAR_IMAGE);
        //try {
            //stream = new FileInputStream("app/src/main/resources/default-user.png");
            //stream = new FileInputStream(getClass().getResource(DEFAULT_AVATAR_IMAGE).getPath());
            //stream = getClass().getResource(DEFAULT_AVATAR_IMAGE).openStream();
            //stream = getClass().getResource(DEFAULT_AVATAR_IMAGE).openStream();
            //stream = new FileInputStream(getClass().getResource(DEFAULT_AVATAR_IMAGE).getPath());
            //URL avatar = getClass().getResource(DEFAULT_AVATAR_IMAGE);
            //avatar.getFile();
            //stream = new FileInputStream(getClass().getResource(DEFAULT_AVATAR_IMAGE).getPath());
            //stream = new FileInputStream(avatar.getFile());
            //stream = getClass().getResourceAsStream(DEFAULT_AVATAR_IMAGE);
        //} catch (Exception e) {
            //e.printStackTrace();
        //}
        //Image image = new Image(stream);
        //Image image = new Image(DEFAULT_AVATAR_IMAGE);

        // Show current date and time
        timer.setDaemon(true);
        timer.start();

        var acct = Global.getAcct();

        displayName();
        displayBalance();

        // Update account pin whenever it changes
        lbl_pin.textProperty().bind(acct.getPinProperty());

        //lbl_name.textProperty().bind(acct.getFNameProperty());
        //lbl_name.textProperty().or(acct.getFNameProperty());
            //.or(acct.getMNameProperty());acct.getFNameProperty()
        //lbl_name.textProperty().bind(
                //Bindings.or(acct.getFNameProperty(), acct.getMNameProperty()));
         //ChangeListener<Number> listener = (obs, ov, nv) -> update();

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
