package server.pantry;

import server.measuring.QuantityMismatchException;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class PantryModel {

    @OneToMany
    @Embedded
    private List<Ingredient> ingredientList;

    public PantryModel(){
        ingredientList = new ArrayList<>();
    }

    public void addIngredient(Ingredient ingredient) throws IngredientMismatchException, QuantityMismatchException {
        if(ingredientList.contains(ingredient)) {
            ingredientList.get(ingredientList.indexOf(ingredient)).add(ingredient);
        } else {
            ingredientList.add(ingredient);
        }
    }
}