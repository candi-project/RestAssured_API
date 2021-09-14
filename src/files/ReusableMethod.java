package files;

import io.restassured.path.json.JsonPath;

public class ReusableMethod {

	public static JsonPath rawtojson(String response) {
		JsonPath js = new JsonPath(response);
		return js;
	
	}
}
