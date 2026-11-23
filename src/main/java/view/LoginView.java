
package view;

import controller.LoginController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginView {

    public void show(Stage stage) {

        LoginController loginController = new LoginController();

        // Application heading
        Label title = new Label("THE BANKING SYSTEM");
        title.setStyle(
                "-fx-font-size: 28px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #123B69;"
        );

        Label subtitle = new Label("Welcome Back!");
        subtitle.setStyle(
                "-fx-font-size: 19px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #333333;"
        );

        Label instruction = new Label("Please log in to continue.");
        instruction.setStyle(
                "-fx-font-size: 13px;" +
                        "-fx-text-fill: #777777;"
        );

        // Username field
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter username");
        usernameField.setPrefHeight(42);
        usernameField.setMaxWidth(300);
        usernameField.setStyle(
                "-fx-background-color: #F3F7FC;" +
                        "-fx-border-color: #C9D8EA;" +
                        "-fx-border-radius: 6;" +
                        "-fx-background-radius: 6;" +
                        "-fx-font-size: 14px;"
        );

        // Password field
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");
        passwordField.setPrefHeight(42);
        passwordField.setMaxWidth(300);
        passwordField.setStyle(
                "-fx-background-color: #F3F7FC;" +
                        "-fx-border-color: #C9D8EA;" +
                        "-fx-border-radius: 6;" +
                        "-fx-background-radius: 6;" +
                        "-fx-font-size: 14px;"
        );

        // Login button
        Button loginButton = new Button("LOGIN");
        loginButton.setPrefHeight(42);
        loginButton.setMaxWidth(300);
        loginButton.setStyle(
                "-fx-background-color: #164B85;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 15px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-background-radius: 6;" +
                        "-fx-cursor: hand;"
        );

        // Feedback message
        Label messageLabel = new Label();
        messageLabel.setStyle(
                "-fx-text-fill: #D32F2F;" +
                        "-fx-font-size: 13px;"
        );

        // Login functionality
        loginButton.setOnAction(event -> {

            String username = usernameField.getText();
            String password = passwordField.getText();

            if (loginController.login(username, password)) {

                new DashboardView().show(stage);

            } else {

                messageLabel.setText("Invalid username or password.");
            }
        });

        // Allow Enter key to trigger login
        passwordField.setOnAction(event -> loginButton.fire());

        // Login card
        VBox card = new VBox(15);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(35));
        card.setMaxWidth(400);
        card.setStyle(
                "-fx-background-color: white;" +
                        "-fx-background-radius: 12;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.12), 15, 0, 0, 4);"
        );

        card.getChildren().addAll(
                title,
                subtitle,
                instruction,
                usernameField,
                passwordField,
                loginButton,
                messageLabel
        );

        // Main background
        VBox layout = new VBox(card);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));
        layout.setStyle("-fx-background-color: #EAF1F9;");

        Scene scene = new Scene(layout, 850, 550);

        stage.setTitle("The Banking System - Login");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
