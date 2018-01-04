package server.pantry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController("/pantry")
public class PantryController {

    @Autowired
    public PantryController() {}
}
