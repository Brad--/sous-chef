package server.recipes;

import java.util.List;

// TODO this whole thing - switching over to pantry first
public class RecipeModel {
    private String name;
    private List<String> tags;

    // TODO author object
    private String author;

    private String description; // For storing notes outside of the actual directions
    private String shortDescription; // Quick excerpt for card view
    private List<String> instructions;
}
