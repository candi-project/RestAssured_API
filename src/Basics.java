import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import files.ReusableMethod;
import files.payload;



public class Basics {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
	//Validate if Add Place API is working as expected
	//Add place -> Update Place with new address -> Get Place to validate if New address is present in response
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		//given - all input details
		//when - submit the API, resource,http method
		//then - validate the response
		
		/*
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.AddPlace()).when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString(); */
		
		//content of the file to String -> content of the file can convert into Bype -> Byte data to String
		// /Users/candichiu/Desktop/RestAssuredApi/payload.json
		
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(new String(Files.readAllBytes(Paths.get("/Users/candichiu/Desktop/RestAssuredApi/AddPlace.json")))).when().post("/maps/api/place/add/json")
				.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
				.header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		System.out.println(response);
		
		JsonPath js = new JsonPath(response); //for parsing json
		String placeId = js.getString("place_id");
		
		System.out.println(placeId);
		String newAddr = "1 Apple Park Way Cupertino, California, 95014-0642 United States";
		
		//Update Place
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body("{\n"
				+ "\"place_id\":\""+placeId+"\",\n"
				+ "\"address\":\""+newAddr+"\",\n"
				+ "\"key\":\"qaclick123\"\n"
				+ "}")
		.when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//Get Place
		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
		.when().get("maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js1=ReusableMethod.rawtojson(getPlaceResponse);
		String actualAddr = js1.getString("address");
		System.out.println(actualAddr);
		
		Assert.assertEquals(actualAddr, newAddr);
		
		

	}

	

	

}
