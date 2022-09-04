package jfx.controller;

// Standard Library
import java.io.IOException;
import java.util.List;
import java.util.Map;

// JavaFX
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Main {
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

    private AnchorPane setupController(String fxmlPath) {
        var loader = new FXMLLoader(getClass().getResource(fxmlPath));
        AnchorPane pane = null;
        try {
            pane = (AnchorPane) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pane;
    }

    /** Switches the main stack pane to the new pane */
    public void load(String view) {
        // Remove the old view
        System.out.println("Clearing old view");
        this.stackpane_main.getChildren().clear();

        // Navigate to new pane
        System.out.println("Showing new view");
        this.stackpane_main.getChildren().addAll(setupController(view));
    }

    @FXML public void navigate(MouseEvent e) {
        var view = this.lv_sidebar.getSelectionModel().getSelectedItem();
        System.out.println(view);
        load(getFXMLPath(view));
    }

    @FXML public void initialize() {
    // Resize the list view
    //this.lv_sidebar.prefHeightProperty().bind(this.ap_sidebar.heightProperty());

    //this.lv_sidebar.minHeightProperty().bind(this.ap_sidebar.heightProperty());
    //this.lv_sidebar.maxHeightProperty().bind(this.ap_sidebar.heightProperty());

    //this.lv_sidebar.minHeightProperty().bind(this.ap_sidebar.heightProperty());
    //this.lv_sidebar.maxHeightProperty().bind(this.ap_sidebar.heightProperty());

    // Expand the split pane with the main vbox

    //this.splitpane_main.minHeightProperty().bind(this.vb_main.maxHeightProperty());
    //this.scrollpane_main.minHeightProperty().bind(this.vb_main.maxHeightProperty());
    //this.ap_sidebar.minHeightProperty().bind(this.vb_main.maxHeightProperty());

    //this.lv_sidebar.minHeightProperty().bind(this.vb_main.maxHeightProperty());

    //this.lv_sidebar.minHeightProperty().bind(this.ap_sidebar.prefHeightProperty());
    //this.lv_sidebar.maxHeightProperty().bind(this.ap_sidebar.prefHeightProperty());


    //this.lv_sidebar.minHeightProperty().bind(this.ap_sidebar.maxHeightProperty());
    //this.lv_sidebar.maxHeightProperty().bind(this.ap_sidebar.maxHeightProperty());

    //this.lv_sidebar.prefHeightProperty().bind(this.ap_sidebar.maxHeightProperty());




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
