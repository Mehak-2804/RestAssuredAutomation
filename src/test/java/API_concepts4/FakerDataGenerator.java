package API_concepts4;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class FakerDataGenerator {

	@Test
	public static void generateFakeData() {

		Faker faker = new Faker();
		String fullName = faker.name().fullName();
		String userName = faker.name().username();
		String password = faker.internet().password();
		String cellPhone = faker.phoneNumber().cellPhone();
		String emailAddress = faker.internet().emailAddress();
		String creditCardNum = faker.business().creditCardNumber();
		// so many methods to generate test data

		System.out.println(fullName + ">> " + password + ">> " + emailAddress + ">> " + creditCardNum);
		
	}

}
