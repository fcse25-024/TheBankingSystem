package controller;

public class LoginController {

    public boolean login(String username, String password) {

        return username != null &&
                password != null &&
                username.equals("admin") &&
                password.equals("1234");
    }
}