package WT_Testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Demo {
	
	
	@BeforeMethod
	public void bm() {
		
		System.out.println("Inside BM" );
		
	}
	
	
	@AfterMethod
	public void am() {
		System.out.println("Inside am" );
		
	}
	
	
	@Test
	
	public void test1() {
		System.out.println("Inside test1" );
		
	}
	
	
@Test
	
	public void test2() {	
		System.out.println("Inside test2" );
		
	}

}
