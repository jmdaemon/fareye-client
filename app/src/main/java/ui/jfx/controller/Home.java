package ui.jfx.controller;

// Standard Library
import java.io.FileInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

// Imports
import ui.jfx.ObservableAccount;
import ui.jfx.Global;

// JavaFX
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableMap;
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
    @FXML private Label lbl_time;
    @FXML private VBox vb_home;
    @FXML private VBox vb_info;

    // Static fields
    private static ObservableAccount acct = Global.getAcct();
    private static String DEFAULT_AVATAR_IMAGE = "/img/default-user.png";

    // Nationality Name, Nation struct
    private static ObservableMap<String, Nation> Nationalities = FXCollections.observableHashMap();

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

    // Helper Functions
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
        //InputStream stream = null;
        //try {
        //stream = new FileInputStream("app/src/main/resources/default-user.png");
        //} catch (Exception e) {
            //e.printStackTrace();
        //}
        //Image image = new Image("file:home/jmd/mytest/java/fareye-client/app/src/main/resources/img/default-user.png");
        //Image image = new Image("file:home/jmd/mytest/java/fareye-client/app/src/main/resources/img/avatar-large.png");
        //Image image = new Image(getClass().getResourceAsStream("/img/avatar-large.png"));
        //Image image = new Image(getClass().getResourceAsStream("app/src/main/resources/img/avatar-large.png"));
        //Image image = new Image(getClass().getResourceAsStream("file:/img/avatar-large.jpg"));
        //Image image = new Image(getClass().getResourceAsStream("app/src/main/resources/img/avatar-large.jpg"));
        //Image image = new Image(getClass().getResourceAsStream("/img/avatar-large.jpg"));
        //Image image = new Image(getClass().getResourceAsStream("app/src/main/resources/img/avatar-large.jpg"));
        Image image = null;
        FileInputStream stream = null;
        try {
            //image = new Image(getClass().getResourceAsStream("/assets/img/avatar-large.jpg"));
            //image = new Image(getClass().getResourceAsStream("/assets/img/avatar-large.jpg"));
            //image = new Image(getClass().getResourceAsStream("file:assets/img/avatar-large.jpg"));
            //image = new Image(getClass().getResourceAsStream("/img/avatar-large.jpg"));
            //image = new Image(getClass().getResourceAsStream("/img/default-user.png"));
            //stream = new FileInputStream("file:app/src/main/resources/img/default-user.png");
            //File file = new File("/img/default-user.png");

            //File file = new File("app/src/main/resources/img/default-user.png");
            //if (file.exists()) {
                //stream = new FileInputStream(file.getAbsoluteFile());
            //}

            image = new Image("file:default-user.png");
        } catch (Exception e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        iv_avatar.setCache(true);
        iv_avatar.imageProperty().bindBidirectional(acct.getAvatarProperty());
        iv_avatar.setVisible(true);

        //iv_avatar.setImage(image);
        acct.setAvatar(image);

        // Show current date and time
        timer.setDaemon(true);
        timer.start();

        // Show all the fields
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
