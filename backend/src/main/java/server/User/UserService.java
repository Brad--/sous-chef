package server.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.pantry.Ingredient;
import server.pantry.PantryModel;
import server.pantry.PantryService;

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

    public User addIngredientsToPantry(Long userId, Long pantryId, List<Ingredient> ingredients) throws UserNotFoundException{
        User user = getUserById(userId);
        if(user == null) {
            throw new UserNotFoundException();
        }
//        user.addItemsToPantry(ingredients);
        userRepository.save(user);
        // TODO save new ingredients in the pantry or update existing ones. Imma go play starcraft
        return user;
    }
}
