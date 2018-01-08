package server.User;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import server.pantry.Ingredient;
import server.pantry.PantryModel;

import javax.persistence.*;
import java.util.List;

// Shouts: https://hackernoon.com/spring-boot-persisting-data-with-rest-jpa-33a063e8b147
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String email;
    private String password;

    @Embedded
    private PantryModel pantry;

    public User() { }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.pantry = new PantryModel();
    }

    public long getId() {
        return id;
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

    @JsonSerialize(as = PantryModel.class)
    public PantryModel getPantry() {
        return pantry;
    }

    public void addItemsToPantry(List<Ingredient> ingredients) {
        this.pantry.addIngredientList(ingredients);
    }
}
