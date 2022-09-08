package ui.jfx.components;

import javafx.scene.control.Button;
import javafx.application.Platform;

public class QuitButton extends Button {
    public QuitButton() {
        this.setText("Quit");
        this.setOnMouseClicked(e -> { Platform.exit(); });
    }
}
