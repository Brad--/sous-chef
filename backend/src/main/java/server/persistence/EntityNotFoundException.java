package server.persistence;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(String entityName){
        super("Entity " + entityName + " not found.");
    }
}
