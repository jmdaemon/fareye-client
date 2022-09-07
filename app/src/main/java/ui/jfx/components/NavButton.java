package ui.jfx.components;

// Standard Library
import java.io.IOException;

// JavaFX
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

/**
  * Navigator Button
  * Allows you to load and switch to a new view on mouse click
  **/
public class NavButton extends Button {
    private StringProperty destination = new SimpleStringProperty();

    public NavButton() {
        destination.set("");
        this.setOnMouseClicked(e -> { this.navigate(this.getScene()); });
    }

    public NavButton(String fxml) {
        //super();
        destination.set(fxml);
        this.setOnMouseClicked(e -> { this.navigate(this.getScene()); });
    }

    public void navigate(Scene scene) {
        var loader = new FXMLLoader(getClass().getResource(destination.get()));
        Parent root = null;
        try {
            root = (Parent) loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (root != null)
            scene.setRoot(root);
        else
            System.err.println("Could not load " + destination);
    }

    public String getDestination() {
        return destination.get();
    }

    public StringProperty getDestinationProperty() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination.set(destination);
    }
}
