
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

        // Heading
        Label heading = new Label("BANKING DASHBOARD");
        heading.setStyle(
                "-fx-font-size: 25px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: white;"
        );

        Label welcome = new Label("Welcome, Romeo Oabile!");
        welcome.setStyle(
                "-fx-font-size: 17px;" +
                        "-fx-text-fill: white;"
        );

        VBox header = new VBox(10, heading, welcome);
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(25));
        header.setStyle(
                "-fx-background-color: #164B85;" +
                        "-fx-background-radius: 10;"
        );

        // Account balance cards
        Label investmentBalance = new Label(
                "INVESTMENT ACCOUNT\n\nP1,155.00");

        Label savingsBalance = new Label(
                "SAVINGS ACCOUNT\n\nP2,101.05");

        Label chequeBalance = new Label(
                "CHEQUE ACCOUNT\n\nP1,700.00");

        String cardStyle =
                "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #164B85;" +
                        "-fx-background-color: white;" +
                        "-fx-padding: 25px;" +
                        "-fx-background-radius: 10;" +
                        "-fx-border-color: #D5E2F0;" +
                        "-fx-border-radius: 10;" +
                        "-fx-alignment: center;" +
                        "-fx-min-width: 190px;";

        investmentBalance.setStyle(cardStyle);
        savingsBalance.setStyle(cardStyle);
        chequeBalance.setStyle(cardStyle);

        HBox accountCards = new HBox(
                15, investmentBalance, savingsBalance, chequeBalance
        );
        accountCards.setAlignment(Pos.CENTER);

        // Navigation buttons
        Button accountsButton = new Button("My Accounts");
        Button depositButton = new Button("Deposit");
        Button withdrawButton = new Button("Withdraw");
        Button historyButton = new Button("Transaction History");
        Button logoutButton = new Button("Logout");

        String buttonStyle =
                "-fx-background-color: #164B85;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 6;" +
                        "-fx-cursor: hand;" +
                        "-fx-pref-width: 200px;" +
                        "-fx-pref-height: 40px;";

        accountsButton.setStyle(buttonStyle);
        depositButton.setStyle(buttonStyle);
        withdrawButton.setStyle(buttonStyle);
        historyButton.setStyle(buttonStyle);

        logoutButton.setStyle(
                "-fx-background-color: #D9534F;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 6;" +
                        "-fx-cursor: hand;" +
                        "-fx-pref-width: 200px;" +
                        "-fx-pref-height: 40px;"
        );

        // Navigation functionality
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
                withdrawButton, historyButton, logoutButton
        );
        navigation.setAlignment(Pos.CENTER);

        Label message = new Label("Select an option to continue.");
        message.setStyle("-fx-text-fill: #777777;");

        VBox layout = new VBox(
                25, header, accountCards, navigation, message
        );

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.setStyle("-fx-background-color: #EAF1F9;");

        Scene scene = new Scene(layout, 850, 650);

        stage.setTitle("The Banking System - Dashboard");
        stage.setScene(scene);
        stage.show();
    }
}
