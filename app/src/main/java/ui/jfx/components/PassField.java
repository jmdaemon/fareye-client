package ui.jfx.components;

// Imports
import ui.jfx.components.mixins.IEnter;

// JavaFX
import javafx.beans.property.BooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;

public class PassField extends PasswordField implements IEnter {
    private TextField unmaskedPasswordField;

    // Mixins
    @Override
    public TextField getEnterField() { return this; }

    @Override
    public void handleEnter(CallbackInterface cb) { IEnter.super.handleEnter(cb); }

    // Getters
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
    }
}
