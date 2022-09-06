package ui.jfx.controller;

// Third Party Libraries
import org.slf4j.Logger;

// Imports
import ui.jfx.Global;

// JavaFX
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Main {
    private static Logger logger = Global.getLogger();

    // Template fields
    @FXML private AnchorPane ap_right;
    @FXML private AnchorPane ap_sidebar;
    @FXML private HBox hb_main;
    @FXML private Label lbl_footer_left;
    @FXML private Label lbl_footer_right;
    @FXML private ListView<String> lv_sidebar;
    @FXML private MenuBar mb_main;
    @FXML private Pane pane_footer;
    @FXML private SplitPane splitpane_main;
    @FXML private StackPane stackpane_main;
    @FXML private VBox vb_main;
    @FXML private VBox vb_sidebar;
    @FXML private Button btn_logout;
    @FXML private Font x1;
    @FXML private Color x2;
    @FXML private Font x3;
    @FXML private Color x4;

    // HashMap<Sidebar Display Name, FXML Resource Files>
    private static ObservableMap<String, String> Views = FXCollections.observableHashMap();

    /** Switches the main stack pane to the new pane */
    public void load(String view) {
        Global global = new Global();
        // Remove the old view
        logger.debug("Clearing old view");
        this.stackpane_main.getChildren().clear();

        // Navigate to new pane
        logger.debug("Showing new view");
        this.stackpane_main.getChildren().addAll((AnchorPane) global.loadFXML(view));
    }

    @FXML public void navigate(MouseEvent e) {
        var view = this.lv_sidebar.getSelectionModel().getSelectedItem();
        logger.debug("Navigating to: " + view);
        //load(Views.get(view));
        //var fxml = Views.get(view);
        //logger.debug("FXML: " + fxml);
        //Global.getFXMLPath(Views.get(view));
        //load(fxml);
        load(Global.getFXMLPath(Views.get(view)));
    }

    public void toLoginPage() {
        Global.changeScene(this.vb_main.getScene(), "/fxmls/LoginView.fxml", "Could not load Login Page");
    }

    @FXML
    public void initialize() {
        // Initialize the sidebar
        Views.put("Home", Global.VIEW_HOME);
        Views.put("Deposit", Global.VIEW_DEPOSIT);
        Views.put("Withdraw", Global.VIEW_WITHDRAW);
        Views.put("History", Global.VIEW_HISTORY);
        Views.put("Change Password", Global.VIEW_PASSWORD);
        Views.put("Transfer Funds", Global.VIEW_TRANSFER);

        // Construct list of displayed names from hashmap keys
        ObservableList<String> list = FXCollections.observableArrayList(Views.keySet());
        this.lv_sidebar.setItems(list);
        this.lv_sidebar.setOnMouseClicked(e -> { this.navigate(e); });

        // Logout Button
        this.btn_logout.setOnMouseClicked(e -> { this.toLoginPage(); });
    }
}
