package server.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import server.pantry.Ingredient;

import java.util.List;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(
            UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) throws ExistingEmailException{
        if(getUserByEmail(user.getEmail()) != null) {
            throw new ExistingEmailException();
        }
        userRepository.save(user);
        return user;
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUserById(Long id) {
        return userRepository.findOne(id);
    }

    public User addIngredientsToPantry(Long id, List<Ingredient> ingredients) {
        User user = getUserById(id);
        if(user == null) {
            // TODO throw user not found
        }
        user.addItemsToPantry(ingredients);
        // TODO update the user in the db. Tomorrow!
        return user;
    }
}
