package API_concepts5;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class apiChaining_updateUser {

	@Test
	public static void testUpdateUser(ITestContext context) {

		// Creating data with Faker class and passing that as JSON object

		Faker faker = new Faker();

		JSONObject obj = new JSONObject();

		obj.put("name", faker.name().fullName());
		obj.put("location", "Noida");
		obj.put("emailAddress", faker.internet().emailAddress());

		int id = (Integer) context.getAttribute("User_ID");
		String bearerToken = "";
		given().headers("Authorization", "Bearer" + bearerToken).contentType("application/json").pathParam("userId", id)
				.body(obj.toString())

				.when().put("url{userId}")
						
					.then().statusCode(200).log().all();
					
		System.out.println("generated id >>> " + id);
	}
}
