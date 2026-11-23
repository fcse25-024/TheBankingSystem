
package view;

import app.BankingData;
import controller.DepositController;
import model.Account;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DepositView {

    public void show(Stage stage) {

        DepositController depositController = new DepositController();

        Label heading = new Label("MAKE A DEPOSIT");
        heading.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");

        Label accountLabel = new Label("Select Account:");

        ComboBox<String> accountBox = new ComboBox<>();
        accountBox.getItems().addAll(
                "Investment Account",
                "Savings Account",
                "Cheque Account"
        );
        accountBox.setPromptText("Choose an account");

        Label amountLabel = new Label("Deposit Amount (BWP):");

        TextField amountField = new TextField();
        amountField.setPromptText("Enter amount");

        Button depositButton = new Button("Deposit");
        Button backButton = new Button("Back to Dashboard");

        Label message = new Label();

        depositButton.setOnAction(event -> {

            String selectedAccount = accountBox.getValue();
            String amountText = amountField.getText();

            if (selectedAccount == null || amountText.isEmpty()) {
                message.setText("Please select an account and enter an amount.");
                return;
            }

            try {

                double amount = Double.parseDouble(amountText);

                if (amount <= 0) {
                    message.setText("Enter an amount greater than zero.");
                    return;
                }

                Account accountToDeposit = null;

                for (Account account : BankingData.customer.getAccounts()) {

                    String accountType = account.getClass().getSimpleName()
                            .replace("Account", " Account");

                    if (accountType.equals(selectedAccount)) {
                        accountToDeposit = account;
                        break;
                    }
                }

                if (accountToDeposit == null) {
                    message.setText("Account not found.");
                    return;
                }

                boolean successful = depositController.deposit(
                        accountToDeposit, amount
                );

                if (successful) {

                    message.setText(
                            "Deposit of P" + String.format("%.2f", amount) +
                                    " successful!\nNew Balance: P" +
                                    String.format("%.2f", accountToDeposit.getBalance())
                    );

                    amountField.clear();

                } else {
                    message.setText("Deposit could not be completed.");
                }

            } catch (NumberFormatException e) {
                message.setText("Please enter a valid numeric amount.");
            }
        });

        backButton.setOnAction(event ->
                new DashboardView().show(stage));

        VBox layout = new VBox(
                15,
                heading,
                accountLabel,
                accountBox,
                amountLabel,
                amountField,
                depositButton,
                message,
                backButton
        );

        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        Scene scene = new Scene(layout, 500, 500);

        stage.setTitle("Banking System - Deposit");
        stage.setScene(scene);
        stage.show();
    }
}
