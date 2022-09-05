package ui.jfx.components;

// Standard Library
import java.util.Locale;

// JavaFX
import javafx.fxml.FXML;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.HBox;

public class CombinedControl extends HBox {
    private TextField textField;
    private Button    button;

    private StringProperty textFieldLabel = new SimpleStringProperty();
    private StringProperty buttonLabel = new SimpleStringProperty();

    // ******************** Constructors **************************************
    public CombinedControl() {
        getStylesheets().add(CombinedControl.class.getResource("/fxmls/css/combined.css").toExternalForm());
        initGraphics();
        registerListeners();

        //textField.textProperty().bindBidirectional(textFieldLabel);
        //button.textProperty().bindBidirectional(buttonLabel);
        textField.textProperty().bindBidirectional(this.textFieldLabel);
        button.textProperty().bindBidirectional(this.buttonLabel);
    }

    //@FXML
    //public void initialize() {
        //getStylesheets().add(CombinedControl.class.getResource("/fxmls/css/combined.css").toExternalForm());
        //initGraphics();
        //registerListeners();

        //textField.textProperty().bindBidirectional(textFieldLabel);
        //button.textProperty().bindBidirectional(buttonLabel);
    //}

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
        getStyleClass().add("/fxmls/css/combined-control");
        textField = new TextField();
        //textField.setText("Amount: ");
        textField.setPromptText("Amount: ");
        textField.setFocusTraversable(false);
        //textField.setEditable(true);
        textField.setTextFormatter(new TextFormatter<>(change -> change.getText().matches("[0-9]*(\\.[0-9]*)?") ? change : null));
        //button = new Button("°C");
        button = new Button("Submit");
        button.setFocusTraversable(false);
        setSpacing(0);
        setFocusTraversable(true);
        setFillHeight(false);
        setAlignment(Pos.CENTER);

        //textField.textProperty().bindBidirectional(textFieldLabel);
        //button.textProperty().bindBidirectional(buttonLabel);

        getChildren().addAll(textField, button);
    }

    private void registerListeners() {
        //button.setOnMousePressed(e -> handleControlPropertyChanged("BUTTON_PRESSED"));
        //button.setOnMouseClicked(e -> handleSubmit());
    }
    // ******************** Methods *******************************************
    //private void handleSubmit(final String PROPERTY) {
        //if ("BUTTON_PRESSED".equals(PROPERTY)) {
            //String buttonText = button.getText();
            //String text       = textField.getText();
            //if (text.matches("^[-+]?\\d+(\\.\\d+)?$")) {
                //if ("°C".equals(buttonText)) {
                    //// Convert to Fahrenheit
                    //button.setText("°F");
                    //textField.setText(toFahrenheit(textField.getText()));
                //} else {
                    //// Convert to Celsius
                    //button.setText("°C");
                    //textField.setText(toCelsius(textField.getText()));
                //}
            //}
        //}
    //}

    //private void handleControlPropertyChanged(final String PROPERTY) {
        //if ("BUTTON_PRESSED".equals(PROPERTY)) {
            //String buttonText = button.getText();
            //String text       = textField.getText();
            //if (text.matches("^[-+]?\\d+(\\.\\d+)?$")) {
                //if ("°C".equals(buttonText)) {
                    //// Convert to Fahrenheit
                    //button.setText("°F");
                    //textField.setText(toFahrenheit(textField.getText()));
                //} else {
                    //// Convert to Celsius
                    //button.setText("°C");
                    //textField.setText(toCelsius(textField.getText()));
                //}
            //}
        //}
    //}

}
