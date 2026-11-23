package view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import controller.LoginController;

public class LoginView {

    public void show(Stage stage) {
        LoginController loginController = new LoginController();
        Label title = new Label("BANKING SYSTEM");
        title.setStyle("-fx-font-size: 26px; -fx-font-weight: bold;");

        Label subtitle = new Label("Welcome! Please log in.");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter username");

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter password");

        Button loginButton = new Button("Login");

        Label messageLabel = new Label();

        loginButton.setOnAction(event -> {

            String username = usernameField.getText();
            String password = passwordField.getText();

            if (loginController.login(username, password)) {

                new DashboardView().show(stage);

            } else {

                messageLabel.setText("Invalid username or password.");
            }
        });

        VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(30));

        layout.getChildren().addAll(
                title,
                subtitle,
                usernameField,
                passwordField,
                loginButton,
                messageLabel
        );

        Scene scene = new Scene(layout, 450, 400);

        stage.setTitle("Banking System - Login");
        stage.setScene(scene);
        stage.show();
    }
}
