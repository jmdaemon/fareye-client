package ui.jfx.components;

// Imports
import ui.jfx.Global;

// Standard Library
import java.math.BigDecimal;
import java.lang.NumberFormatException;

// JavaFX
import javafx.scene.layout.Priority;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextFormatter;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class TransactionButton extends HBox {
    // Required. Scenebuilder cannot detect the ui.jfx.Global import so we must duplicate this field here.
    // The alternative is ensuring that the Globals import is included in every fxml file.
    public static String NUMERIC_REGEX  = "[0-9]*(\\.[0-9]*)?";
    private EnterField  enterField;
    private Button      button;

    private StringProperty enterFieldLabel = new SimpleStringProperty();
    private StringProperty buttonLabel = new SimpleStringProperty();

    // Constructor
    public TransactionButton() {
        getStylesheets().add(TransactionButton.class.getResource("/fxmls/css/transactionbutton.css").toExternalForm());
        initGraphics();
        registerListeners();

        enterField.textProperty().bindBidirectional(enterFieldLabel);
        button.textProperty().bindBidirectional(buttonLabel);
    }

    // Properties
    public String getEnterFieldLabel() { return enterFieldLabel.get(); }
    public StringProperty enterFieldProperty() { return enterFieldLabel; }
    public void setEnterFieldLabel(String label) { enterFieldLabel.set(label); }

    public String getButtonLabel() { return buttonLabel.get(); }
    public StringProperty buttonProperty() { return buttonLabel; }
    public void setButtonLabel(String label) { buttonLabel.set(label); }

    // Return the button, and enterField for use in setting up callbacks
    public Button getButton() { return button; }
    public EnterField getEnterField() { return enterField; }

    // Init
    private void initGraphics() {
        getStyleClass().add("/fxmls/css/transaction-button");

        // EnterField
        enterField = new EnterField();
        enterField.setPromptText("Amount: ");
        enterField.setFocusTraversable(false);
        setHgrow(enterField, Priority.ALWAYS);

        // Enter only numbers
        enterField.setTextFormatter(new TextFormatter<String>(change -> change.getText().matches(NUMERIC_REGEX) ? change : null));

        // Button
        button = new Button("Submit");
        button.setFocusTraversable(false);
        setSpacing(0);
        setFocusTraversable(true);
        setFillHeight(false);
        setAlignment(Pos.CENTER);
        setHgrow(button, Priority.ALWAYS);

        getChildren().addAll(enterField, button);
    }

    private void registerListeners() { }

    // Methods
    public BigDecimal getAmount() {
        var logger = Global.getLogger();
        var amount = this.getEnterFieldLabel();
        var amt = BigDecimal.valueOf(0);
        try {
            amt = BigDecimal.valueOf(Integer.parseInt(amount));
            logger.debug("Amount Entered: " + amt);
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
        return amt;
    }

    public static boolean isZero(BigDecimal amt) {
        boolean result = amt.compareTo(BigDecimal.valueOf(0)) == 0;
        return result;
    }
}
