package ui.jfx.components;

// Standard Library
import java.io.IOException;
import java.net.URL;

// Imports
import ui.jfx.components.PassField;
//import ui.jfx.Global;

// JavaFX
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
//import javafx.scene.control.TextField;
//import javafx.scene.control.PasswordField;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

//public class PasswordRevealer {
public class PasswordRevealer extends VBox {
    //private PassField passwordField;
    //private CheckBox checkBox;
    //@FXML private CheckBox cb_passreveal;
    //@FXML private PassField pf_passreveal;
    private CheckBox cb_passreveal;
    private PassField pf_passreveal;

    private static String VIEW_PASSREVEAL = "/fxmls/components/PasswordRevealer.fxml";

    //private CheckBox cb_passreveal;
    //private PassField pf_passreveal;

    //@FXML private PasswordField pf_passreveal;

    //@FXML private VBox vb_passreveal;

    public PasswordRevealer() {
        //Global.changeScene(this.vb_passreveal.getScene(), "/fxmls/components/PasswordRevealer.fxml", "Could not load Password Revealer");
        //var global = new Global();
        //Global.changeScene(this.vb_passreveal.getScene(), "/fxmls/components/PasswordRevealer.fxml", "Could not load Password Revealer");
        //var global = new Global();
        //global.loadFXML("/fxmls/components/PasswordRevealer.fxml");
        //getClass().getResource
        //PasswordRevealer.class.getResource("/fxmls/components/PasswordRevealer.fxml").toExternalForm();
        //(this.vb_passreveal.getScene(), "/fxmls/components/PasswordRevealer.fxml", "Could not load Password Revealer");

        // Load the FXML template file
        //FXMLLoader loader;
        //loader.setRoot(this);
        //loader.setController(this);
        //loader.setLocation(VIEW_PASSREVEAL);
        //var loader = new FXMLLoader(this.getClass().getResource(VIEW_PASSREVEAL));
        //loader.setRoot(this);
        //loader.setController(this);

        //loader = new FXMLLoader(this.getClass().getResource(VIEW_PASSREVEAL));
        //loader.setRoot(this);
        //loader.setController(this);
        //try {
            //loader.setLocation(new URL(VIEW_PASSREVEAL));
        //} catch (Exception e) {
            //e.printStackTrace();
        //}

        //try {
            //loader.load();
        //} catch (IOException exception) {
            //throw new RuntimeException(exception);
        //}
        //getStylesheets().add(PasswordRevealer.class.getResource("/fxmls/css/passwordrevealer.css").toExternalForm());
        initGraphics();
        registerListeners();
    }

    //@FXML
    //private void initialize() {
        //pf_passreveal.setupRevealer(cb_passreveal.selectedProperty());
    //}

    private void initGraphics() {

        setAlignment(Pos.CENTER);
        setPrefHeight(400);
        setPrefWidth(600);
        setSpacing(6);

        //this.setPrefHeight(400);
        //this.setPrefWidth(600);
        //pf_passreveal.setText("Password ");
        pf_passreveal = new PassField();
        pf_passreveal.setPrefWidth(400);

        cb_passreveal = new CheckBox();
        pf_passreveal.setPrefWidth(400);
        cb_passreveal.setText("Show password");

        pf_passreveal.setupRevealer(cb_passreveal.selectedProperty());

        getChildren().addAll(pf_passreveal, cb_passreveal);
    }
    private void registerListeners() { }

    public CheckBox getCheckBox() { return cb_passreveal; }
    public PassField getPasswordField() { return pf_passreveal; }


    //@FXML
    //public void initialize() {
        ////var global = new Global();
        ////VBox root = (VBox) global.loadFXML("/fxmls/components/PasswordRevealer.fxml");
        ////global.changeScene(this.vb_passreveal.getScene(), "/fxmls/components/PasswordRevealer.fxml", "Could not load Password Revealer");

        //Global.changeScene(this.vb_passreveal.getScene(), "/fxmls/components/PasswordRevealer.fxml", "Could not load Password Revealer");
        //this.pf_passreveal.setupRevealer(cb_passreveal.selectedProperty());
    //}
}
