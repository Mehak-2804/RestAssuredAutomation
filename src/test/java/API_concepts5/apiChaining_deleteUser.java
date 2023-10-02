package API_concepts5;

import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;

public class apiChaining_deleteUser {

	@Test
	public static void testDeleteUser(ITestContext context) {

		int id = (Integer) context.getAttribute("User_ID");

		String bearerToken = "";
		given().headers("Authorization", "Bearer" + bearerToken).pathParams("Userid", id)

				.when().delete("url{Userid}")

				.then().statusCode(204).log().all();

		System.out.println("generated id >>> " + id);
	}
}
