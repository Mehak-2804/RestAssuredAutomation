package API_concepts2;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

public class FileUploadAndDownload {

	@Test
	public static void singleFileUpload() {

		// to upload single file

		File myFile = new File("Path of the file , you want to upload");

		// given (multipart and contentType is mandatory)

		given().multiPart("file", myFile).contentType("multipart/form-data")

				.when().post("httplocalhost:8080/uploadFile")

				.then().statusCode(200).body("fileName", equalTo("Test.txt")).log().all();

	}

	@Test
	public static void multipleFileUpload() {

		// to upload multiple file (with multiple multipart)

		File myFile1 = new File("Path of the file , you want to upload");
		File myFile2 = new File("Path of the file , you want to upload");

		// given (multipart and contentType is mandatory)

		given().multiPart("files", myFile1).multiPart("files", myFile2).contentType("multipart/form-data")

				.when().post("httplocalhost:8080/uploadMultipleFiles")

				.then().statusCode(200).body("[0].fileName", equalTo("Test.txt"))
				.body("[0].fileName", equalTo("Test.txt")).log().all();

	}

	@Test
	public static void multipleFileUpload2() { // it may not work for all API's

		// to upload multiple file (using array)

		File myFile1 = new File("Path of the file , you want to upload");
		File myFile2 = new File("Path of the file , you want to upload");

		// given (multipart and contentType is mandatory)

		// we ca create array
		File fileArr[] = { myFile1, myFile2 };

		given().multiPart("files", fileArr).contentType("multipart/form-data")

				.when().post("httplocalhost:8080/uploadMultipleFiles")

				.then().statusCode(200).body("[0].fileName", equalTo("Test.txt"))
				.body("[0].fileName", equalTo("Test.txt")).log().all();

	}

	@Test
	public static void fileDownload() {

		// to download the file
		given()

				.when().get("http://localhost:8080/downloadFile/Test.txt")

				.then().statusCode(200).log().body();

	}

}
