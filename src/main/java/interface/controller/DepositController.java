package app.ui;

import java.util.ResourceBundle;
//import java.net.URL;
import java.io.IOException;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class DepositController {

    @FXML
    private Button backButton;

    @FXML
    private TextField depositAmount;

    @FXML
    private Label depositAmtLabel;

    @FXML
    private Button depositButton;

    @FXML
    private Label currency;

    @FXML
    private void initialize() {
    }
  
}
