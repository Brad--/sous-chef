package server.pantry;

import server.User.User;
import server.measuring.QuantityMismatchException;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Pantry")
public class PantryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    
    private Long id;
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="User",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "pantry_id")
    )
    private Set<User> users = new HashSet<>();

    @ElementCollection
    private List<Ingredient> ingredientList;

    public PantryModel(){
        this.ingredientList = new ArrayList<>();
    }

    public PantryModel(String name) {
        this();
        this.name = name;
    }

    // Using 'list' instead of 'get' because Jackson auto deserialization puts it in the object if you start with 'get'
    // I could probably fix that but ¯\_(ツ)_/¯
    public int listItemCount() {
        return ingredientList.size();
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredientList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    public void addIngredientList(List<Ingredient> ingredients) {
        ingredients.forEach(ingredient -> {
            try {
                addIngredient(ingredient);
            }
            catch(Exception e) {
                //TODO after logger config: log this error
            }
        });
    }

    public void addIngredient(Ingredient ingredient) throws IngredientMismatchException, QuantityMismatchException {
        if(ingredientList.contains(ingredient)) {
            ingredientList.get(ingredientList.indexOf(ingredient)).add(ingredient);
        } else {
            ingredientList.add(ingredient);
        }
    }

    public void addUser(User user) {
        if(!users.contains(user)){
            users.add(user);
        }
    }
}