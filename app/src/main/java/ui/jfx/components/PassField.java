package ui.jfx.components;

// Imports
import ui.jfx.components.mixins.IEnter;

// JavaFX
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.PasswordField;

public class PassField extends VBox implements IEnter {
    private PasswordField password;
    private TextField plaintext;

    // Mixins
    @Override
    //public TextField getEnterField() { return this; }
    public TextField getEnterField() { return password; }

    @Override
    public void handleEnter(CallbackInterface cb) { IEnter.super.handleEnter(cb); }

    // Properties
    private StringProperty promptLabel = new SimpleStringProperty();

    // Set field prompt property
    public String getPromptLabel()              { return promptLabel.get(); }
    public StringProperty promptLabelProperty() { return promptLabel; }
    public void setPromptLabel(String label)    { promptLabel.set(label);}

    //public void promptProperty(String label)  { promptLabel.set(label); }

    //public void setPromptText(String prompt) { promptLabel.set("Password"); }
    //public void setPromptText(String prompt) { promptLabel.set("Password"); }

    // Getters
    public TextField getTextField() { return plaintext; }

    public PassField() {
      // Initialize members
      plaintext = new TextField();
      password = new PasswordField();

      plaintext.textProperty().bindBidirectional(password.textProperty());
      plaintext.setManaged(false);

      setPromptLabel("Password");
      password.setVisible(true);

      getChildren().addAll(plaintext, password);

      // Bind properties
      password.promptTextProperty().bindBidirectional(promptLabel);
      plaintext.promptTextProperty().bindBidirectional(promptLabel);
    }

    public void setupRevealer(BooleanProperty prop) {
      plaintext.managedProperty().bind(prop);
      plaintext.visibleProperty().bind(prop);

      password.managedProperty().bind(prop.not());
      password.visibleProperty().bind(prop.not());
    }
}
