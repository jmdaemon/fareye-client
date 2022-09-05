package ui.jfx.components;

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

    // ******************** Constructors **************************************
    public TransactionButton() {
        getStylesheets().add(TransactionButton.class.getResource("/fxmls/css/transactionbutton.css").toExternalForm());
        initGraphics();
        registerListeners();

        textField.textProperty().bindBidirectional(textFieldLabel);
        button.textProperty().bindBidirectional(buttonLabel);
    }

    public String getTextFieldLabel() { return textFieldLabel.get(); }
    public StringProperty textFieldProperty() { return textFieldLabel; }
    public void setTextFieldLabel(String label) { textFieldLabel.set(label); }

    public String getButtonLabel() { return buttonLabel.get(); }
    public StringProperty buttonProperty() { return buttonLabel; }
    public void setButtonLabel(String label) { buttonLabel.set(label); }

    // Return the button to use for setting up callbacks in the components
    public Button getButton() { return button; }

    // ******************** Initialization ************************************
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

    private void registerListeners() {
    }
}
