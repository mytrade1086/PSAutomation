package Reports;

import java.io.IOException;

import com.aventstack.extentreports.MediaEntityBuilder;

public interface IExtentReportGenericMethods {
	
	public  void startTest(String vFile) ;
	public  String takeScreenshot(String desc) throws IOException ;
	public  void addFailLog(String desc) ;
	public  void addPassLog(String desc, String takeScreenshot) ;
	public void endTest() ;
	

}
