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

    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }

    public User getUserById(Long id) {
        return userRepository.findOne(id);
    }

    public User addIngredientsToPantry(List<Ingredient> ingredients) {

        return new User();
    }
}
