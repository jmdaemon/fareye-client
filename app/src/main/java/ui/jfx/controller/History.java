package ui.jfx.controller;

// Imports
import ui.jfx.Global;

// JavaFX
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class History {

    // Template fields
    @FXML private AnchorPane ap_history;
    @FXML private HBox hb_history;
    @FXML private Text t_history;
    @FXML private TextArea ta_history;
    @FXML private TextField tf_history;
    @FXML private VBox vb_history;

    private static ObservableList<String> messages = FXCollections.observableArrayList();
    private StringProperty history = new SimpleStringProperty();

    private void display() { }

    @FXML
    public void initialize() {
        var acct = Global.getAcct();
        var list = acct.getTransactions();

        messages = FXCollections.observableArrayList(list);
        history.set(String.join("\n", messages));
        this.ta_history.textProperty().bind(history);
    }
}
