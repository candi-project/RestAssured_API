import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	@Test
	public void SumOfCourses() {
		// Verify if Sum of all Course prices matches with Purchase Amount
				
		JsonPath js = new JsonPath(payload.CoursePrice());
		int courses = js.getInt("courses.size()");
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		       int sum=0;
				for(int i=0;i<courses;i++) {
					int copies=js.get("courses["+i+"].copies");
					int coursePrice = js.getInt("courses["+i+"].price");
					sum=sum+copies*coursePrice;
				}
				System.out.println(sum);
				Assert.assertEquals(sum, totalAmount);
	}

}
