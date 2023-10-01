package API_concepts4;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class Authentications {

//	@Test
	public static void testBasicAuthen() {

		given().auth().basic("postman", "password").when().get("https://postman-echo.com/basic-auth").then()
				.statusCode(200).body("authenticated", equalTo(true)).log().all();

	}

//	@Test
	public static void testDigestiveAuthen() {

		given().auth().digest("postman", "password").when().get("https://postman-echo.com/basic-auth").then()
				.statusCode(200).body("authenticated", equalTo(true)).log().all();
	}

//	@Test
	public static void testPreemptiveAuthen() {

		given().auth().preemptive().basic("postman", "password").when().get("https://postman-echo.com/basic-auth")
				.then().statusCode(200).body("authenticated", equalTo(true)).log().all();
	}

//	@Test
	public static void testBearerTokenAuthen() {

		// here we need to create token to succes authentication -- example git hub
		// repository

		// we can use headers while sending request as well

		String bearerToken = "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"; // github personal token
		given().headers("Authorization", "Bearer " + bearerToken).when().get("https://api.github.com/user/repos").then()
				.statusCode(200).log().all();

	}

//	@Test
	public static void testOauthAuthen() {

		// Oauth1 authen : It used many parameters while sending request whereas in
		// Oauth2 , only access token is required

		// Syntax for Oauth1 Authen

		given().auth().oauth("consumerKey", "consumerSecret", "accessToken", "secretToken").when().get("").then()
				.statusCode(200).log().all();

		// for Oauth2 , we just need to pass accessToken paramter

		given().auth().oauth2("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").when()
				.get("https://api.github.com/user/repos").then().statusCode(200).log().all();
	}

	@Test
	public static void testAPIKeyAuthen() {

		// query params will use key and value -- key will be appid and value will be
		// app key for the respective api

		// Mehod 1
		given().queryParam("appid", "fe9c5cddb7e01d747b4611c3fc9eaf2c").when()
				.get("https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7").then()
				.statusCode(200).log().all();

		// Method 2: we can pass path and query parameters in given() section seperately
		// as well

		given().queryParam("appid", "fe9c5cddb7e01d747b4611c3fc9eaf2c").pathParam("myPath", "data/2.5/forecast/daily")
				.queryParam("q", "Delhi").queryParam("units", "metric").queryParam("cnt", "7").when()
				.get("https://api.openweathermap.org/{myPath}").then().statusCode(200).log().all();

	}

}
