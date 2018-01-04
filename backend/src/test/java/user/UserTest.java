package user;

import io.restassured.http.ContentType;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import server.User.User;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserTest {

    private static final String USER_ENDPOINT = "/api/user";

    @Test
    public void testCreateUser() {
        User user = new User("Owen", "owen@gmail.com", "1234");
        given().contentType(ContentType.JSON).body(user)
                .when().post(USER_ENDPOINT)
                .then().assertThat().statusCode(equalTo(HttpStatus.OK));
    }
}
