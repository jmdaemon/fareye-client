package ui.jfx.controller;

// Imports
//import ui.jfx.ObservableAccount;
import ui.jfx.Global;

// JavaFX
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
    @FXML private Label lbl_recent;
    @FXML private Label lbl_time;
    @FXML private VBox vb_home;
    @FXML private VBox vb_info;

    private ChangeListener<String> nameListener = (obs, ov, nv) -> { updateName(); };
    //private ChangeListener<String> avatarListener = (obs, ov, nv) -> { updateAvatar(); };

    // Displaying changes
    private void updateName() {
        var acct = Global.getAcct();
        this.lbl_name.setText(String.format("%s %s %s",
                acct.getFirstName(), acct.getMiddleName(), acct.getLastName()));
    }

    private void updateAvatar(Image image) {
        iv_avatar.setImage(image);
    }

    private void updateCountry(String oldCountry, String newCountry) {
        // TODO: Lookup country flag in resources and set country image
        // TODO: Convert balance from oldCountry to newCountry currency, and set balance
        // TODO: Lookup new country currency symbol
    }

    // Modifying properties
    public void changeAvatar() {
        // TODO: Implement file chooser widget, get filepath, create Image from file, updateAvatar
    }

    @FXML
    public void initialize() {
        var acct = Global.getAcct();

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
        iv_avatar.imageProperty().addListener((obs, ov, nv) -> { updateAvatar(nv); });

        // TODO: Update balance, country flag on country / nationality change
        cb_country.valueProperty().bind(acct.getCountryProperty());
        cb_country.valueProperty().addListener((obs, ov, nv) -> { updateCountry(ov, nv); });

        // TODO: Implement generic logout button

    }
}
