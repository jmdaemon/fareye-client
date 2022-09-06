package ui.jfx.components;

//import ui.jfx.components.Callback;
//import ui.jfx.components.EnterInterface;

import javafx.scene.control.TextField;
//import javafx.scene.input.KeyCode;

public class EnterField extends TextField implements EnterInterface {

    @Override
    public TextField getEnterField() {
        return this;
    }

    @Override
    public void handleEnter(CallbackInterface cb) {
        EnterInterface.super.handleEnter(cb);
    }
    //public EnterField() { }

    //public void handleEnter(CallbackInterface cb) {
        //this.setOnKeyPressed(e -> {
            //if (e.getCode() == KeyCode.ENTER) {
                ////this.login();
                //cb.execute();
            //}
        //});
    //}
}
