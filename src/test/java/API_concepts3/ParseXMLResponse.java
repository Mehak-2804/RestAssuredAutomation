package API_concepts3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ParseXMLResponse {

	@Test
	public static void parseXMLResponse() {

		// Approach 1

		given().when().get("http://restapi.adequateshop.com/api/Traveler?page=1").then()
				.body("TravelerinformationResponse.travelers.Travelerinformation[0].email",
						equalTo("Developer12@gmail.com"))
				.log().all();

		// Approach 2

		Response res = given().when().get("http://restapi.adequateshop.com/api/Traveler?page=1");

		String travelerEmail = res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].email")
				.toString();

		Assert.assertEquals(travelerEmail, "Developer12@gmail.com");
	}

	@Test
	public static void parseXMLResponseApproach3() {

		// Approach 3

		Response res = given().when().get("http://restapi.adequateshop.com/api/Traveler?page=1");

		XmlPath xmlObj = new XmlPath(res.asString()); // if we want to convert entire response into String - then
														// asString()

		// here we will use getList to get the list of all the specific nodes

		List<String> travelerList = xmlObj.getList("TravelerinformationResponse.travelers.Travelerinformation");

		Assert.assertEquals(travelerList.size(), 10);
		// verify traveler name in response

		List<String> travelerNameList = xmlObj
				.getList("TravelerinformationResponse.travelers.Travelerinformation.name");

		boolean status = false;
		for (String list : travelerNameList) {
			if (list.contains("Vijay")) {
				status = true;
				break;
			}
		}

		Assert.assertEquals(status, true);
	}

}
