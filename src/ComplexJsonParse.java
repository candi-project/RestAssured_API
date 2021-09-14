import files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
	
	public static void main(String[] args) {
		
		//json editor https://jsoneditoronline.org/#left=local.qocudi&right=local.qakiye
		
		JsonPath js = new JsonPath(payload.CoursePrice());
		
		//Print No of courses returned by API
		int courses = js.getInt("courses.size()");
		System.out.println(courses);
		
		//Print Purchase Amount
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		
		//Print Title of the first course
		String titleOfFirst = js.get("courses[0].title");
		System.out.println(titleOfFirst);
		
		//Print All course titles and their respective Prices
		for(int i=0;i<courses;i++) {
			String courseTitle = js.get("courses["+i+"].title");
	        System.out.println(courseTitle);
	       // int coursePrice = js.getInt("courses["+i+"].price");
	        System.out.println(js.getInt("courses["+i+"].price"));
	        
		}
		
	}

}
