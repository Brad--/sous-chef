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

    // Clearly this is a bad format. Gotta get better exception handling
    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<Object> handleUserNotFound(Exception e) {
        return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
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

    @PostMapping("/user/{userId}/pantry/{pantryId}")
    public User addIngredients(
            @PathVariable("userId") Long userId,
            @PathVariable("pantryId") Long pantryId,
            @RequestBody List<Ingredient> ingredientList
    ) throws UserNotFoundException {
        return userService.addIngredientsToPantry(userId, pantryId, ingredientList);
    }

    // TODO: Delete User
}
