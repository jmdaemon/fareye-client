package ui.jfx.components;

//import ui.jfx.components.Callback;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class EnterField extends TextField {

    //public EnterField() { }

    public void handleEnter(CallbackInterface cb) {
        this.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) {
                //this.login();
                cb.execute();
            }
        });
    }
}
