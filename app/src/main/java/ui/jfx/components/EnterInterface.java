package ui.jfx.components;

import javafx.scene.control.TextField;
//import javafx.scene.control.PasswordField;
import javafx.scene.input.KeyCode;

public interface EnterInterface {

    //TextField textField = null;
    //TextField getTextField();
    //PasswordField getPasswordField();
    TextField getEnterField();
    default void handleEnter(CallbackInterface cb) {
        //getPasswordField().setOnKeyPressed(e -> {
        getEnterField().setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.ENTER) { cb.execute(); }
        });
        //textField.setOnKeyPressed(e -> {
            //if (e.getCode() == KeyCode.ENTER) { cb.execute(); }
        //});
    }
    //public void handleEnter(CallbackInterface cb);
    //{
        //this.setOnKeyPressed(e -> {
            //if (e.getCode() == KeyCode.ENTER) { cb.execute(); }
        //});
    //}

}
