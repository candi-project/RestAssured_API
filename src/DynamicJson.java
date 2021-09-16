import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.ReusableMethod;
import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	@Test(dataProvider="BooksData")
	public void AddAndDeleteBook(String isbn, String aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		String response= given().log().all().headers("Content-Type","application/json")
		.body(payload.addBook(isbn, aisle))
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js=ReusableMethod.rawtojson(response);
		String ID=js.get("ID");
		System.out.println(ID);
		
		        
		given().log().all().headers("Content-Type","application/json")
		.body(payload.deleteBook(ID))
		.when().post("/Library/DeleteBook.php")
		.then().log().all().assertThat().statusCode(200);
		
		
	}
	

    @DataProvider(name="BooksData")
	public Object[][] getData(){
    	
    	//array= collection of element
    	//multidimensional array=collection of arrays
    	
    	return new Object[][] {{"QWE","167"},{"ERT","456"},{"VBN","788"}};
    	
	}
    
}