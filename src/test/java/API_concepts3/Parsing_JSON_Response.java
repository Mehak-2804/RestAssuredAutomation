package API_concepts3;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;

public class Parsing_JSON_Response {

	@Test
	public static void ParsingJSONResponse() {

		// Approach 1

		given().contentType("application/json; charset=uft-8")

				.when().get("https://localhost:3000/store")

				.then().statusCode(200).header("Content-Type", "").body("book[3].title", equalTo(""));

		//*****************************************
		
		// Approach 2 (there we can have more validations compare to first one)

		Response res = given().contentType(ContentType.JSON)

				.when().get("https://localhost:3000/store");

		// now if we want to validate any response then we can put
		// assertions on res variable which is the response of the request

		Assert.assertEquals(res.getStatusCode(), "200"); // validation 1

		// now if we want to validate any field from the body, then we will be using the
		// method jsonPath

		String bookName = res.jsonPath().get("book[30]title").toString(); // this will convert object to String
		Assert.assertEquals(bookName, "English");

		//*****************************************
		// Approach 3

		// now we want to get the title of any specific field from the JSON (that field
		// is having multiple entries)

		// every array represented in JSON file is an object

		// JSON Object -> JSON Arrays -> JSON Objects

		JSONObject jo = new JSONObject(res.toString()); // response needs to be converted from object to String

		boolean status = false;
		for (int i = 0; i < jo.getJSONArray("book").length(); i++) {

			String titleName = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			System.out.println(titleName);
			// to validate

			if (titleName.equals("")) {
				status = true;
				break;
			}
		}

		Assert.assertEquals(status, true);

		// to get total price

		double totPrice = 0;
		for (int i = 0; i < jo.getJSONArray("book").length(); i++) {

			String price = jo.getJSONArray("book").getJSONObject(i).get("title").toString();
			totPrice = totPrice + Double.parseDouble(price);
		}
		Assert.assertEquals(totPrice, 34.5);
	}
}