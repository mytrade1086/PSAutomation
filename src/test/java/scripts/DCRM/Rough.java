package scripts.DCRM;

import java.lang.reflect.Method;
import java.util.HashMap;

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

		// HashMap<Object,Object> hm=new HashMap<Object,Object>();

		Object[][] data = new Object[3][2];
		Object[][] data2 = new Object[3][1];

		// Object[][] data2=new Object[data.length-1][1];

		data[0][0] = "username";
		data[0][1] = "password";
		data[1][0] = "sumit";
		data[1][1] = "pwd";
		data[2][0] = "neha";
		data[2][1] = "npwd";

		System.out.println(data.length);
		System.out.println(data[0].length);

		// HashMap<Object,Object> hm=new HashMap<Object,Object>();
		for (int row = 0; row < data.length; row++) {
			HashMap<Object, Object> hm = new HashMap<Object, Object>();

			// hm.put(key, value)
			for (int col = 0; col < data[0].length; col++) {
				hm.put(data[0][col], data[row][col]);               //username username
				                                                    //.//userbane password    
 
				
			}
			data2[row][0] = hm;
		}

		System.out.println("hi");
	}

	@AfterMethod
	public void AM(Method result) {
		System.out.println(result.getAnnotation(Test.class).description());
	}

}
