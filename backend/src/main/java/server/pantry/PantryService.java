package server.pantry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.User.User;
import server.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class PantryService {

    private PantryRepository pantryRepository;

    private static Logger logger = LoggerFactory.getLogger(PantryService.class);

    @Autowired
    public PantryService(
            PantryRepository pantryRepository
    ) {
        this.pantryRepository = pantryRepository;
    }

    public PantryModel createPantry(String pantryName, User user) {
        PantryModel pantry = new PantryModel(pantryName);
        pantry.addUser(user);
        pantryRepository.save(pantry);
        logger.trace("Pantry '{}' created.", pantryName);
        return pantry;
    }

    // TODO: Add permissions?
    public PantryModel getPantryById(Long pantryId) {
        PantryModel pantry = pantryRepository.findOne(pantryId);
        if(pantry == null) {
            logger.error("No pantry found with id: {}", pantryId);
        }
        return pantry;
    }

    public PantryModel addIngredientsList(Long pantryId, List<Ingredient> ingredientList) throws EntityNotFoundException {
        PantryModel pantry = pantryRepository.findOne(pantryId);
        if(pantry == null) {
            logger.error("Pantry {} not found.", pantryId);
            throw new EntityNotFoundException("Pantry");
        }
        pantry.addIngredientList(ingredientList);
        pantryRepository.save(pantry);
        logger.trace("Successfully added list to pantry");
        return pantry;
    }
}
