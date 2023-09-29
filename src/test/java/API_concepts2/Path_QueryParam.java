package API_concepts2;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class Path_QueryParam {

	@Test
	public static void pathAndQueryParam() {
		
		
		// https://www.reqres.in/api/users?page=2&id=5
		
		// path params will be declared as variable by giving any name for key, but for query params, we need
		// to be specific about the queries name we are passing as key. if it's page, key will be page only.
		
		given()
		    .pathParam("myPath", "users")
		    .queryParam("page", 2)
		    .queryParam("id", 5)
		
	   .when()
		     .get("https://www.reqres.in/api{myPath}") //here we need to mention path parameter in {} , whereas query param will automatically be sent with request, we can define multiple param paths as well
				
	   .then()
		 		.statusCode(200)
		 		.log().all();
	}

}
