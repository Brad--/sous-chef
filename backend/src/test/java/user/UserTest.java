package user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import server.User.User;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class UserTest {

    private static final String USER_ENDPOINT = "/api/user";

    private static ObjectMapper objectMapper;

    @Before
    public void setup() {
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateUser() {
        User user = new User("Owen", "owen@gmail.com", "1234");
        given().contentType(ContentType.JSON).body(user)
                .when().post(USER_ENDPOINT)
                .then().assertThat().statusCode(equalTo(HttpStatus.OK.value()));
    }

    @Test
    public void testListUsers() {
        User user = new User("Owen", "owen@gmail.com", "1234");
        when().get(USER_ENDPOINT)
                .then()
                .body("[0]", equalTo(user));
    }
}
