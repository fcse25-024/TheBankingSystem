
package view;

import app.BankingData;
import controller.TransactionHistoryController;
import model.Transaction;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class TransactionHistoryView {

    public void show(Stage stage) {

        TransactionHistoryController historyController =
                new TransactionHistoryController();

        List<Transaction> transactions =
                historyController.getTransactionHistory(BankingData.customer);

        Label heading = new Label("TRANSACTION HISTORY");
        heading.setStyle("-fx-font-size: 25px; -fx-font-weight: bold;");

        VBox layout = new VBox(18);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        layout.getChildren().add(heading);

        if (transactions.isEmpty()) {

            Label emptyMessage = new Label("No transactions found.");
            layout.getChildren().add(emptyMessage);

        } else {

            for (Transaction transaction : transactions) {

                Label transactionLabel = new Label(
                        transaction.getTransactionType() +
                                " | P" +
                                String.format("%.2f", transaction.getAmount()) +
                                " | " +
                                transaction.getTransactionDate()
                );

                transactionLabel.setStyle(
                        "-fx-font-size: 14px; -fx-padding: 10px;"
                );

                layout.getChildren().add(transactionLabel);
            }
        }

        Button backButton = new Button("Back to Dashboard");

        backButton.setOnAction(event ->
                new DashboardView().show(stage));

        layout.getChildren().add(backButton);

        Scene scene = new Scene(layout, 700, 600);

        stage.setTitle("Banking System - Transaction History");
        stage.setScene(scene);
        stage.show();
    }
}
