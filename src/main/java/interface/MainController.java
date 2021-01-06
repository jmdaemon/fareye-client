package app.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;


//public abstract class ChangeView extends GridPane { 
  //private ChangeView next;

  //ChangeView(ChangeView next) { 
    //this.next = next; 
    //createView();
  //}

  //abstract void createView();

  //protected void callNext() {
      //getScene().setRoot(next);
  //}

//}

public class MainController {
    @FXML
    private StackPane view;

    public void setView(Node node) {
        view.getChildren().setAll(node);
    }

}
