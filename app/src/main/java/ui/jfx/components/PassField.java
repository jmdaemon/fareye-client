package ui.jfx.components;

// JavaFX
import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class PassField extends PasswordField implements EnterInterface {
    private TextField unmaskedPasswordField;

    // Used purely for
    //public TextField getTextField() {
    @Override
    public TextField getEnterField() {
      //return unmaskedPasswordField;
      return this;
    }

    @Override
    public void handleEnter(CallbackInterface cb) {
        EnterInterface.super.handleEnter(cb);
    }

    //@Override
    //public PasswordField getPasswordField() {
      //return this;
    //}
    public TextField getTextField() {
      return unmaskedPasswordField;
    }

    @FXML
    public void initialize() {
      unmaskedPasswordField.textProperty().bindBidirectional(this.textProperty());
    }

    public void setupRevealer(BooleanProperty prop) {
      unmaskedPasswordField.managedProperty().bind(prop);
      unmaskedPasswordField.visibleProperty().bind(prop);

      this.managedProperty().bind(prop.not());
      this.visibleProperty().bind(prop.not());

      //unmaskedPasswordField.textProperty().bindBidirectional(this.textProperty());
    }
}
