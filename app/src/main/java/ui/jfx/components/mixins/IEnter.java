package ui.jfx.components.mixins;

import ui.jfx.components.CallbackInterface;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

/**
  * Enter Interface Mixin
  * To create your own custom widgets, implement IEnter, and override getEnterField(), and handleEnter()
  * Example;
  * <pre>
  * @Override
  * public TextField getEnterField() { return this; }
  *
  * @Override
  * public void handleEnter(CallbackInterface cb) {
  *     IEnter.super.handleEnter(cb);
  * }
  * </pre>
  **/
public interface IEnter {
    TextField getEnterField();
    default void handleEnter(CallbackInterface cb) {
        getEnterField().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) { cb.execute(); }
        });
    }
}
