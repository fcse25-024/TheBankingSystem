
package view;

import app.BankingData;
import controller.AccountsController;
import model.Account;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class AccountsView {

    public void show(Stage stage) {

        AccountsController accountsController =
                new AccountsController();

        List<Account> accounts =
                accountsController.getCustomerAccounts(BankingData.customer);

        double totalBalance =
                accountsController.getTotalBalance(BankingData.customer);

        Label heading = new Label("MY ACCOUNTS");
        heading.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");

        String style =
                "-fx-font-size: 16px;" +
                        "-fx-padding: 20px;" +
                        "-fx-background-color: #e8f0fe;" +
                        "-fx-background-radius: 10px;";

        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        layout.getChildren().add(heading);

        for (Account account : accounts) {

            String accountType =
                    account.getClass().getSimpleName()
                            .replace("Account", " Account");

            Label accountLabel = new Label(
                    accountType +
                            "\nAccount Number: " + account.getAccountNumber() +
                            "\nBalance: P" + String.format("%.2f", account.getBalance())
            );

            accountLabel.setStyle(style);

            layout.getChildren().add(accountLabel);
        }

        Label totalLabel = new Label(
                "Total Balance: P" + String.format("%.2f", totalBalance)
        );

        totalLabel.setStyle(
                "-fx-font-size: 18px; -fx-font-weight: bold;"
        );

        Button backButton = new Button("Back to Dashboard");

        backButton.setOnAction(event ->
                new DashboardView().show(stage));

        layout.getChildren().addAll(totalLabel, backButton);

        Scene scene = new Scene(layout, 600, 600);

        stage.setTitle("Banking System - My Accounts");
        stage.setScene(scene);
        stage.show();
    }
}
