package scripts.DCRM;

import java.lang.reflect.Method;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Rough {
	String vFileName;
	@BeforeMethod
	public void BM(Method result) {
		System.out.println(result.getAnnotation(Test.class).description());
	}

	@Test(description = "Create Opportunity")
	public void demo() {	
		System.out.println("Inside test");
	}
	
	@AfterMethod
	public void AM(Method result) {
		System.out.println(result.getAnnotation(Test.class).description());
	}

}
