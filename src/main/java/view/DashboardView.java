
package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DashboardView {

    public void show(Stage stage) {

        Label heading = new Label("BANKING DASHBOARD");
        heading.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");

        Label welcome = new Label("Welcome, Romeo Oabile!");
        welcome.setStyle("-fx-font-size: 17px;");

        Label investmentBalance = new Label(
                "Investment Account\nP1,155.00");

        Label savingsBalance = new Label(
                "Savings Account\nP2,101.05");

        Label chequeBalance = new Label(
                "Cheque Account\nP1,700.00");

        String cardStyle =
                "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-color: #e8f0fe;" +
                        "-fx-padding: 25px;" +
                        "-fx-background-radius: 10px;";

        investmentBalance.setStyle(cardStyle);
        savingsBalance.setStyle(cardStyle);
        chequeBalance.setStyle(cardStyle);

        HBox accountCards = new HBox(
                15, investmentBalance, savingsBalance, chequeBalance);

        accountCards.setAlignment(Pos.CENTER);

        Button accountsButton = new Button("My Accounts");
        Button depositButton = new Button("Deposit");
        Button withdrawButton = new Button("Withdraw");
        Button historyButton = new Button("Transaction History");
        Button logoutButton = new Button("Logout");

        Label message = new Label("Select an option to continue.");

        accountsButton.setOnAction(event ->
                new AccountsView().show(stage));

        depositButton.setOnAction(event ->
                new DepositView().show(stage));

        withdrawButton.setOnAction(event ->
                new WithdrawView().show(stage));

        historyButton.setOnAction(event ->
                new TransactionHistoryView().show(stage));

        logoutButton.setOnAction(event ->
                new LoginView().show(stage));

        VBox navigation = new VBox(
                12, accountsButton, depositButton,
                withdrawButton, historyButton, logoutButton);

        navigation.setAlignment(Pos.CENTER);

        VBox layout = new VBox(
                25, heading, welcome, accountCards, navigation, message);

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        Scene scene = new Scene(layout, 850, 600);

        stage.setTitle("Banking System - Dashboard");
        stage.setScene(scene);
        stage.show();
    }
}
