package app.ui;

import app.bankAccount.*;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class MainController {
  @FXML
  private StackPane view;

  private BankAccount user;

  public void setView(Node node) {
      view.getChildren().setAll(node);
  }

  public void setUser(BankAccount user) {
    this.user = user;
  }

  public BankAccount getUser() {
    return user;
  }

}
