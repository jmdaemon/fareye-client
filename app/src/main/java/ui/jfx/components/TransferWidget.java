package ui.jfx.components;

// Imports
import ui.jfx.Global;

// Standard Library
import java.math.BigDecimal;
import java.lang.NumberFormatException;

// JavaFX
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;

public class TransferWidget extends HBox {
    // TODO:
    // - Align transfer button in TransferTo.fxml
    // - Expand the fields slightly on fullscreen resize (UI Change).
    // - Create initial transfer widget
    // LATER:
    // - Make hstory widget
    // - Readd dashboard timer widget displaying account balance, name, etc
    // - Add http call to fareye-server database
    // - Add icons and themeing
    // - Remake more barebones version in Swing?
    // - Finish fareye-server
    // - Remove old dead code
    // - Finish unit tests
    private Label lbl_title;
    private Label lbl_secondary;
    private Button btn_transfer;

    public TransferWidget() { }
    private void initGraphics() { }
    private void registerListeners() { }
}
