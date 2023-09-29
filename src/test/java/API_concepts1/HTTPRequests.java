package API_concepts1;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class HTTPRequests {

	@Test
	void getUser() {

		given()

				.when()

				.then();

	}

	@Test
	void createUser() {

		given()

				.when()

				.then();
	}

	@Test
	void updateUser() {

		given()

				.when().delete().

				.then();
	}

	@Test
	void deleteUser() {

		given()

				.when()

				.then();
	}

}
