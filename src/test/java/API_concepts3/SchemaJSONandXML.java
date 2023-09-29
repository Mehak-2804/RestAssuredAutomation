package API_concepts3;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.module.jsv.JsonSchemaValidator;

public class SchemaJSONandXML {

	@Test
	public static void JSONSchemaValidation() {

		given()

				.when().get("http://localhost:8080/store")

				.then().assertThat().body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("JSON schemaFileName present in src/test/resources"));
	}

	// assertThat will be used here to validate schema , JsonSchemaValidator class

	@Test
	public static void XMLSchemaValidation() {

		given()

				.when().get("http://restapi.adequateshop.com/api/Traveler")

				.then().assertThat()
				.body(RestAssuredMatchers.matchesXsdInClasspath("XML schemaFileName present in src/test/resources"));

	}

}