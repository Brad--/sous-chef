package user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import server.User.User;
import server.measuring.Quantity;
import server.pantry.Ingredient;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class UserTest {

    private static final String USER_ENDPOINT = "/api/user";
    private static final String PANTRY_ADD_ENDPOINT = USER_ENDPOINT + "/pantry";
    private static final String ID_KEY = "id";

    @Autowired
    private static ObjectMapper objectMapper;

    @Before
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    public User getOwen() {
        return new User("Owen", "owen@gmail.com", "1234");
    }

    public List<Ingredient> getIngredient() {
        Quantity quantity = new Quantity("Lb", 1234);
        Ingredient ingredient = new Ingredient("Tofu", quantity);
        return Collections.singletonList(ingredient);
    }

    @Test
    public void testCreateUser() {
        User user = getOwen();
//        System.out.println(objectMapper.writeValueAsString(user));
        given().contentType(ContentType.JSON)
                .body(user)
                .when().post(USER_ENDPOINT)
                .then().assertThat().statusCode(equalTo(HttpStatus.OK.value()));
    }

    @Test
    public void testGetUser() {
        User user = getOwen();
        given().contentType(ContentType.JSON)
                .param(ID_KEY, 1) // This should run after 'testCreateUser', so we know Owen is id: 1
                .when().get(USER_ENDPOINT)
                .then().assertThat().statusCode(equalTo(HttpStatus.OK.value()))
                .body("name", equalTo(user.getName()));
    }

    @Test
    public void testAddItemToPantry() {
        // This should run after 'testCreateUser' which will make Owen with an id of 1, so we're gonna use his id
        User user = getOwen();
        given().contentType(ContentType.JSON)
                .param(ID_KEY, 1)
                .body(getIngredient())
                .when().post(PANTRY_ADD_ENDPOINT)
                .then().assertThat().statusCode(equalTo(HttpStatus.OK.value()));

        given().contentType(ContentType.JSON)
                .param(ID_KEY, 1)
                .when().get(USER_ENDPOINT)
                .then().assertThat()
                .body("pantry.ingredientList[0].name", equalTo("Tofu"));
    }

    @Test
    public void gimmeJson() throws JsonProcessingException{
        User user = new User("Owen", "owen@gmail.com", "1234");
//        user.addItemsToPantry(getIngredient());
        System.out.println(objectMapper.writeValueAsString(user));
    }
}
