package server.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.pantry.Ingredient;

import java.util.List;

@RestController("/user")
public class UserController {

    private UserService userService;

    @ExceptionHandler({ExistingEmailException.class})
    public ResponseEntity<Object> handleException(Exception e) {
        return new ResponseEntity<>("Email address already in use.", HttpStatus.CONFLICT);
    }

    @Autowired
    public UserController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @PostMapping()
    public User createUser(
            @RequestBody User user
    ) throws ExistingEmailException {
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
            @RequestBody Long id,
            @RequestBody List<Ingredient> ingredients
    ) {
        userService.addIngredientsToPantry(id, ingredients);
    }
}
