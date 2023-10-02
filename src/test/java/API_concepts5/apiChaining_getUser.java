package API_concepts5;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class apiChaining_getUser {

	@Test
	public static void testGetUser(ITestContext context) {

		// Creating data with Faker class and passing that as JSON object
//
//		Faker faker = new Faker();
//
//		JSONObject obj = new JSONObject();
//
//		obj.put("name", faker.name().fullName());
//		obj.put("location", "Amritsar");
//		obj.put("emailAddress", faker.internet().emailAddress());

		int id = (Integer) context.getAttribute("User_ID");

		String bearerToken = "";
		given().headers("Authorization", "Bearer" + bearerToken).pathParams("Userid", id)

				.when().get("url{Userid}")

				.then().statusCode(200).log().all();

		System.out.println("generated id >>> " + id);
	}

}
