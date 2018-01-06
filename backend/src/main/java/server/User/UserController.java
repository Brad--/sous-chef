package server.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping()
    public User createUser(
            @RequestBody User user
    ) {
        userRepository.save(user);
        return user;
    }

    @GetMapping()
    public User getUser(
            @RequestParam Long id
    ) {
        return userRepository.findOne(id);
    }
}
