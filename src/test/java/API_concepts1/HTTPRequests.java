package API_concepts1;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

public class HTTPRequests {

	int id;

	@Test
	void getUsers() {

		given()

				.when().get("https://reqres.in/api/users?page=2").then().statusCode(200).body("page", equalTo(2)).log()
				.all();

	}

	@Test
	void createUser() {

		HashMap mp = new HashMap();
		mp.put("name", "Mehak");
		mp.put("job", "SE");

//		given().contentType("application/json").body(mp).when().post("https://reqres.in/api/users?page=2").then()
//			.statusCode(201).log().all();

		id = given().contentType("application/json").body(mp).when().post("https://reqres.in/api/users").jsonPath()
				.getInt("id");

	}

	@Test(dependsOnMethods = { "createUser" })
	void updateUser() {
		HashMap mp = new HashMap();
		mp.put("name", "Preet");
		mp.put("job", "Finance");

//		given().contentType("application/json").body(mp).when().post("https://reqres.in/api/users?page=2").then()
//			.statusCode(201).log().all();

		given().contentType("application/json").body(mp).when().put("https://reqres.in/api/users/" + id);
	}

	@Test
	void deleteUser() {

		given().when().delete("https://reqres.in/api/users/" + id).then().statusCode(204).log().all();
	}

}
