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
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
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
    @FXML private Font x1;
    @FXML private Color x2;
    @FXML private Font x3;
    @FXML private Color x4;

    // HashMap<Sidebar Display Name, FXML Resource Files>
    private static ObservableMap<String, String> Views = FXCollections.observableHashMap();

    private String getFXMLPath(String displayName) {
        return "/fxmls/" + Views.get(displayName) + ".fxml";
    }

    /** Switches the main stack pane to the new pane */
    public void load(String view) {
        Global global = new Global();
        // Remove the old view
        logger.debug("Clearing old view");
        this.stackpane_main.getChildren().clear();

        // Navigate to new pane
        logger.debug("Showing new view");
        //this.stackpane_main.getChildren().addAll(setupController(view));
        this.stackpane_main.getChildren().addAll((AnchorPane) global.loadFXML(view));
    }

    @FXML public void navigate(MouseEvent e) {
        var view = this.lv_sidebar.getSelectionModel().getSelectedItem();
        logger.debug("Navigating to: " + view);
        load(getFXMLPath(view));
    }

    @FXML
    public void initialize() {
        // Initialize the sidebar
        Views.put("Deposit", "Deposit");
        Views.put("Withdraw", "Withdraw");
        Views.put("History", "History");
        Views.put("Change Password", "ResetPassword");
        Views.put("Transfer From", "TransferFrom");
        Views.put("Transfer To", "TransferTo");

        // Construct list of displayed names from hashmap keys
        ObservableList<String> list = FXCollections.observableArrayList(Views.keySet());
        this.lv_sidebar.setItems(list);
        this.lv_sidebar.setOnMouseClicked(e -> { this.navigate(e); });
    }
}
