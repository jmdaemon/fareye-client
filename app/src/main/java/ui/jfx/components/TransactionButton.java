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
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;

public class TransactionButton extends HBox {
    private TextField textField;
    private Button    button;

    private StringProperty textFieldLabel = new SimpleStringProperty();
    private StringProperty buttonLabel = new SimpleStringProperty();

    // Constructor
    public TransactionButton() {
        getStylesheets().add(TransactionButton.class.getResource("/fxmls/css/transactionbutton.css").toExternalForm());
        initGraphics();
        registerListeners();

        textField.textProperty().bindBidirectional(textFieldLabel);
        button.textProperty().bindBidirectional(buttonLabel);
    }

    // Properties
    public String getTextFieldLabel() { return textFieldLabel.get(); }
    public StringProperty textFieldProperty() { return textFieldLabel; }
    public void setTextFieldLabel(String label) { textFieldLabel.set(label); }

    public String getButtonLabel() { return buttonLabel.get(); }
    public StringProperty buttonProperty() { return buttonLabel; }
    public void setButtonLabel(String label) { buttonLabel.set(label); }

    // Return the button, and textField for use in setting up callbacks
    public Button getButton() { return button; }
    public TextField getTextField() { return textField; }

    // Init
    private void initGraphics() {
        getStyleClass().add("/fxmls/css/transaction-button");

        // TextField
        textField = new TextField();
        textField.setPromptText("Amount: ");
        textField.setFocusTraversable(false);
        // Enter only numbers
        textField.setTextFormatter(new TextFormatter<>(change -> change.getText().matches("[0-9]*(\\.[0-9]*)?") ? change : null));

        // Button
        button = new Button("Submit");
        button.setFocusTraversable(false);
        setSpacing(0);
        setFocusTraversable(true);
        setFillHeight(false);
        setAlignment(Pos.CENTER);

        getChildren().addAll(textField, button);
    }

    private void registerListeners() { }

    // Methods
    public BigDecimal getAmount() {
        var logger = Global.getLogger();
        var amount = this.getTextFieldLabel();
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
