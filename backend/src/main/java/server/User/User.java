package server.User;

import server.pantry.PantryModel;

// Shouts: https://hackernoon.com/spring-boot-persisting-data-with-rest-jpa-33a063e8b147
public class User {
    private String name;
    private String email;
    private String password;

    private PantryModel pantry;

    public User() {
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
//        this.pantry = new PantryModel();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public PantryModel getPantry() {
        return pantry;
    }
}
