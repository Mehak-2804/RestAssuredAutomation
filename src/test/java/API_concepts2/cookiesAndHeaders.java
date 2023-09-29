package API_concepts2;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

// cookies and headers value be generated in run time
//cookie value will not be same in each run, but name will be same
// some headers value will be same and some won't

// Header is single header (name and value) , Headers(Multiple headers and multiple values)

public class cookiesAndHeaders {

	@Test
	public static void testCookies() {

		given().when().get("https://www.google.com/").then().cookie("AEC", "AQWHDBDUIWE8972Y83Y2UHWKDHK").log().all();
	}

	@Test
	public static void getCookies() {

		Response res = given().when().get("https://www.google.com/");

		// get Single cookie info

		String cookie_value = res.getCookie("AEC");
		System.out.println("cooke value is >>> " + cookie_value);

		// get all cookies info
		Map<String, String> cookies = res.getCookies();
		System.out.println(cookies.keySet());
		for (String map : cookies.keySet()) {

			String cookies_value = res.getCookie(map);
			System.out.println(map + " >>> " + cookies_value);
		}
	}

	// mainly we focus on Content-Type, Content-Encoding, server from headers

	@Test
	public static void testHeaders() {

		given()

				.when().get("https://www.google.com/")

				.then().header("Content-type", "valuefromresponse").and().header("Server", "valuefromresponse").log()
				.headers(); // we can only print headers as well instead of passing all, we will pass
							// headers

	}

	@Test
	public static void getHeaders() {

		Response res = given().when().get("https://www.google.com/");

		// get Single header info

		String header_value = res.getHeader("Content-Type");
		System.out.println(" header value is >>> " + header_value);

		// get all headers info
		Headers headers = res.getHeaders();

		for (Header hd : headers) {
			System.out.println(hd.getName() + "" + hd.getValue());
		}
	}
	
	
	@Test
	public static void loggingWays() {

		given()
		  .when().get("https://www.google.com/")
		     .then()
		     .log().all();

		// log().cookies()
		// log().headers()
	}

}
