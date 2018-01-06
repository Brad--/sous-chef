package server.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.pantry.Ingredient;

import java.util.List;

@RestController("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @PostMapping()
    public User createUser(
            @RequestBody User user
    ) {
        return userService.createUser(user);
    }

    @GetMapping()
    public User getUser(
            @RequestParam Long id
    ) {
        return userService.getUserById(id);
    }

    @PostMapping(value = "/user/pantry")
    public void addIngredients(
            @RequestParam Long id,
            @RequestBody List<Ingredient> ingredients
    ) {

    }
}
