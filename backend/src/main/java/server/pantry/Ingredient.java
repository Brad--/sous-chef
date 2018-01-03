package server.pantry;

import server.measuring.Quantity;
import server.measuring.QuantityMismatchException;

public class Ingredient {
    private String name;
    private Quantity quantity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
