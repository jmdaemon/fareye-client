package app.ui;

import app.bankAccount.*;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class Account {

    //private ObservableList<BankAccount> accounts = FXCollections.observableArrayList();
    private ObservableList<BankAccount> accounts;

    public ObservableList<BankAccount> getAccounts() {
        return accounts;
    }

    public void addAccount(BankAccount acct) {
      //accounts.addAll(acct);
      //accounts.setAll(acct);
      accounts = FXCollections.observableArrayList(acct);
    }
}
