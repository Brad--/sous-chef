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

    // Using 'list' instead of 'get' because Jackson auto deserialization puts it in the object if you start with 'get'
    // I could probably fix that but ¯\_(ツ)_/¯
    public int listItemCount() {
        return ingredientList.size();
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
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
}