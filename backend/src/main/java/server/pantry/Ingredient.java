package server.pantry;

import server.measuring.Quantity;
import server.measuring.QuantityMismatchException;

import javax.persistence.*;

@Entity
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ingredientName;

    @Embedded
    private Quantity quantity;

    public Ingredient(){}

    public Ingredient(String ingredientName, Quantity quantity) {
        this.ingredientName = ingredientName;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return ingredientName;
    }

    public void setName(String name) {
        this.ingredientName = name;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }

    public void add(Ingredient ingredient) throws IngredientMismatchException, QuantityMismatchException{
        if(!this.getName().equals(ingredient.getName())){
            throw new IngredientMismatchException();
        }
        this.quantity.add(ingredient.getQuantity());
    }
}
