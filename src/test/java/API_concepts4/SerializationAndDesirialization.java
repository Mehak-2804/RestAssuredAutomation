package API_concepts4;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationAndDesirialization {

	@Test
	public static void convertPOJOtoJSON() throws JsonProcessingException {

		// create JAVA object using POJO

		Students stu = new Students();
		stu.setName("");
		stu.setPhoneNum("01234");
		stu.setLocation("amritsar");

		String coursesArr[] = { "abc", "def" };
		stu.setCourses(coursesArr);

		// convert POJO to JSON --- for this we would require Jackson library

		ObjectMapper objMapper = new ObjectMapper();
		String jsonData = objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stu);
		System.out.println(jsonData);
	}

	@Test
	public static void converJSONtoPOJO() throws JsonProcessingException {

		// json object

		String JSONdata = "{\r\n" + "	\"name\": \"MehakPreet\",\r\n\"" + "	\"phoneNum\": \"004\",\r\n\""
				+ "	\"location\": \"Canada\",\r\n\"" + "	\"courses\": [\r\\n\"" + "		\"Ruby\",\r\\n\""
				+ "		\"JS\"]" + "}";

		// convert POJO to JSON --- for this we would require Jackson library

		ObjectMapper objMapper = new ObjectMapper();
		Students stu = objMapper.readValue(JSONdata, Students.class); // pojo should be in student format so thats y we
																		// have given Students.class

		// now fetch the response

		System.out.println(stu.getName());
		System.out.println(stu.getPhoneNum());
		System.out.println(stu.getLocation());
		System.out.println(stu.getCourses()[0]);
		System.out.println(stu.getCourses()[1]);

	}
}
