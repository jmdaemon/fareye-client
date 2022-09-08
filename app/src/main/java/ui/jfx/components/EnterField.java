package ui.jfx.components;

import ui.jfx.components.mixins.IEnter;
import javafx.scene.control.TextField;

public class EnterField extends TextField implements IEnter {

    // Mixins
    @Override
    public TextField getEnterField() { return this; }

    @Override
    public void handleEnter(CallbackInterface cb) { IEnter.super.handleEnter(cb); }
}
