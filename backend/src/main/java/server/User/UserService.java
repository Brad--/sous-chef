package server.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.pantry.Ingredient;
import server.pantry.PantryModel;
import server.pantry.PantryService;
import server.persistence.EntityNotFoundException;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;
    private PantryService pantryService;

    private static Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(
            UserRepository userRepository,
            PantryService pantryService
    ) {
        this.userRepository = userRepository;
        this.pantryService = pantryService;
    }

    public User createUser(User user) throws ExistingEmailException{
        if(getUserByEmail(user.getEmail()) != null) {
            throw new ExistingEmailException();
        }
        userRepository.save(user);
        PantryModel defaultPantry = pantryService.createPantry(user.getName() + "'s Pantry", user);
        user.addPantry(defaultPantry);
        userRepository.save(user);
        logger.trace("Created user '{}'", user.getName());
        return user;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserById(Long id) {
        return userRepository.findOne(id);
    }

    public void addIngredientsToPantry(Long userId, Long pantryId, List<Ingredient> ingredients) throws EntityNotFoundException {
        User user = getUserById(userId);
        if(user == null) {
            logger.error("User {} not found.", userId);
            throw new EntityNotFoundException("User");
        }
        if(user.validPantry(pantryId)) {
            pantryService.addIngredientsList(pantryId, ingredients);
        } else {
            // This can happen if the pantry does not exist or the user does not have access to the pantry
            logger.error("Unable to add ingredients to pantry {} for user {}.", pantryId, userId);
        }
    }
}
