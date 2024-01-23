package API_concepts1;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

public class PostRequestWays {

	// post request using HashMap

	@Test
	public static void postDataUsingHashMap() {
		
		HashMap data = new HashMap();

		data.put("name", "Mehak");
		data.put("phoneNum", "000");
		data.put("location", "Noida");

		String courseArr[] = { "C", "C++" };
		
		data.put("course", courseArr);

		// to create nested hashmap
		// HashMap data1 = new HashMap();
		//	data1.put("log" , "123");
		//      data1.put("lat" , "234");
		// data.put("location" , data1);
		
		given().contentType("application/json").body(data).when().post("htttp:/localhost:3000/students").then()
				.statusCode(201).body("name", equalTo("Mehak")).body("course[0]", equalTo("C"))
				.body("course[1]", equalTo("C++")).header("Content-Type", "application/json; charset=uft-8").log()
				.all();

	}

	// post request body using org.json library

	// for this , firstly we need to add org.json dependency in pom.xml

	public static void postRequestUsingOrgJson() {

		JSONObject data = new JSONObject();
		data.put("name", "Preet");
		data.put("phoneNum", "001");
		data.put("location", "Gurgaon");

		String courseArr[] = { "Java", "Python" };

		data.put("course", courseArr);

		// for JSON , we need to convert data to the string (toString())

		given().contentType("application/json").body(data.toString()).when().post("htttp:/localhost:3000/students")
				.then().statusCode(201).body("name", equalTo("Mehak")).body("course[0]", equalTo("C"))
				.body("course[1]", equalTo("C++")).header("Content-Type", "application/json; charset=uft-8").log()
				.all();
	}

	// post request body using POJO (plain old java object)

	public static void postRequestUsingPOJO() {

		POJO_PostRequest data = new POJO_PostRequest();
		data.setName("Jagpreet");
		data.setPhoneNum("003");
		data.setLocation("Punjab");

		String courseArr[] = { "Java", "Python" };

		data.setCourses(courseArr);

		given().contentType("application/json").body(data).when().post("htttp:/localhost:3000/students").then()
				.statusCode(201).body("name", equalTo("Mehak")).body("course[0]", equalTo("C"))
				.body("course[1]", equalTo("C++")).header("Content-Type", "application/json; charset=uft-8").log()
				.all();
	}

	// post request body using extrnal JSON file

	public static void postRequestUsingExternalJSON() throws FileNotFoundException {

		File file = new File(".\\Rest-Assured\\body.json");

		// we will read the file (opening the file)

		FileReader fr = new FileReader(file);

		// we will split file into tokens

		JSONTokener jt = new JSONTokener(fr);

		// now extract data into JSON format

		JSONObject data = new JSONObject(jt);

		// use toString to convert JSON data into String
		
		given().contentType("application/json").body(data.toString())
		 .when().post("htttp:/localhost:3000/students")
		   .then()
				.statusCode(201).body("name", equalTo("Mehak")).body("course[0]", equalTo("C"))
				.body("course[1]", equalTo("C++")).header("Content-Type", "application/json; charset=uft-8").log()
				.all();
	}

}
