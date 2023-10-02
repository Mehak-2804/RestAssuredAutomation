package API_concepts5;

import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class apiChaining_CreateUser {

	@Test
	public static void testCreateUser(ITestContext context) {

		// Creating data with Faker class and passing that as JSON object

		Faker faker = new Faker();

		JSONObject obj = new JSONObject();

		obj.put("name", faker.name().fullName());
		obj.put("location", "Amritsar");
		obj.put("emailAddress", faker.internet().emailAddress());

		String bearerToken = "";
		int id = given().headers("Authorization", "Bearer" + bearerToken).contentType("application/json")
				.body(obj.toString())

				.when().post("url").jsonPath().getInt("id");

		System.out.println("generated id >>> " + id);

		// to make this id available to other classes

		// we will pass ITestContext in the method as paramter
		
		context.setAttribute("User_ID", id); // this will make 'id' available to other classes
		
		
		
	}
}
