package ui.jfx.components;

// Imports
import ui.jfx.components.PassField;

// JavaFX
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;

public class PasswordRevealer extends VBox {
    private PassField passwordField;
    private CheckBox checkBox;

    // Getters
    public CheckBox getCheckBox() { return checkBox; }
    public PassField getPasswordField() { return passwordField; }

    // Initialization
    public PasswordRevealer() {
        setAlignment(Pos.CENTER);
        setPrefHeight(400);
        setPrefWidth(600);
        setSpacing(6);

        passwordField = new PassField();
        passwordField.setPrefWidth(400);

        checkBox = new CheckBox();
        passwordField.setPrefWidth(400);
        checkBox.setText("Show password");

        passwordField.setupRevealer(checkBox.selectedProperty());

        getChildren().addAll(passwordField, checkBox);
    }
}
