package app.ui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class SidebarController {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML 
  void loadDashboardView(MouseEvent event) { 
    Navigator.loadScene(event, Navigator.DASHBOARD); 
  }

  @FXML 
  void loadDepositView(MouseEvent event) { 
    Navigator.loadScene(event, Navigator.DEPOSIT); 
  }

  @FXML
  void initialize() { 
  }
}

