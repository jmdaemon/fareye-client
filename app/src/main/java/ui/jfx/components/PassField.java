package ui.jfx.components;

// Imports
import ui.jfx.components.mixins.IEnter;

// JavaFX
import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.PasswordField;

//public class PassField extends PasswordField implements IEnter {
//public class PassField extends HBox implements IEnter {
public class PassField extends VBox implements IEnter {
    private PasswordField maskedPasswordField;
    private TextField unmaskedPasswordField;

    // Mixins
    @Override
    //public TextField getEnterField() { return this; }
    public TextField getEnterField() { return maskedPasswordField; }

    @Override
    public void handleEnter(CallbackInterface cb) { IEnter.super.handleEnter(cb); }

    // Getters
    public TextField getTextField() { return unmaskedPasswordField; }

    private void showUnmaskedPassword() {
      //this.setVisible(false);      // Hide the nmasked password field
      maskedPasswordField.setVisible(false);      // Hide the masked password field
      unmaskedPasswordField.setVisible(true); // Show the unmasked password field
      //this.visibleProperty().set(false);      // Show the masked password field
    }

    private void initPasswordField() {
      // Show the 'Password' prompt initially
      unmaskedPasswordField.setPromptText("Password ");
      showUnmaskedPassword();
    }

    //public PassField() {
      //super();
    //}

    //@FXML
    //public void initialize() {
    public PassField() {
      //new VBox();

      unmaskedPasswordField = new TextField();
      maskedPasswordField = new PasswordField();

      unmaskedPasswordField.textProperty().bindBidirectional(maskedPasswordField.textProperty());
      unmaskedPasswordField.setManaged(false);

      //unmaskedPasswordField.textProperty().bindBidirectional(this.textProperty());
      //this.setVisible(false);
      //unmaskedPasswordField.setVisible(true);
      //unmaskedPasswordField.setText("");
      //initPasswordField();

      maskedPasswordField.setPromptText("Password ");
      maskedPasswordField.setVisible(true);
      //unmaskedPasswordField.setPromptText("Password ");

      //unmaskedPasswordField.setPromptText("Password ");
      //unmaskedPasswordField.setVisible(true); // Show the unmasked password field
      //maskedPasswordField.setVisible(false);  // Hide the masked password field

      getChildren().addAll(unmaskedPasswordField, maskedPasswordField);
      //unmaskedPasswordField.setPromptText("Password ");

      //initPasswordField();

      // When the password is empty, show the "Password" prompt
      //unmaskedPasswordField.textProperty().addListener((observable, oldValue, newValue) -> {
        //if (newValue.isEmpty()) {
          //initPasswordField();
        //} else {
          //showUnmaskedPassword();
          //// Hide the unmasked password field
          ////unmaskedPasswordField.setVisible(false);
          //// Show the masked password field
          ////this.setVisible(false);
        //}
      //});

      //unmaskedPasswordField.onInputMethodTextChangedProperty

     //, and when we start typing, show the masked version
    }

    public void setupRevealer(BooleanProperty prop) {
      //boolean unmaskedVisible = unmaskedPasswordField.visibleProperty().get();
      //boolean maskedVisible = this.visibleProperty().get();
      //boolean unmaskedVisible = unmaskedPasswordField.visibleProperty().get();
      //boolean maskedVisible = maskedPasswordField.visibleProperty().get();

      unmaskedPasswordField.managedProperty().bind(prop);
      unmaskedPasswordField.visibleProperty().bind(prop);

      maskedPasswordField.managedProperty().bind(prop.not());
      maskedPasswordField.visibleProperty().bind(prop.not());

      //this.managedProperty().bind(prop.not());
      //this.visibleProperty().bind(prop.not());

      //this.setVisible(maskedVisible);
      //unmaskedPasswordField.setVisible(unmaskedVisible);

      //maskedPasswordField.setVisible(maskedVisible);
      //unmaskedPasswordField.setVisible(unmaskedVisible);

    }
}
