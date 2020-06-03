package Reports;
import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import Base.Base;
import Utilities.ElementUtil;

public class myListener extends Base implements ITestListener {
	
	
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.INFO, result.getName() + " execution has started");	
	}

	public void onTestSuccess(ITestResult result) {
//		try {
//			//test.log(Status.PASS, result.getThrowable(),MediaEntityBuilder.createScreenCaptureFromPath(ElementUtil.screenshot()).build());	
//			//test.log(Status.PASS, result.getName()+" passed",MediaEntityBuilder.createScreenCaptureFromPath(ElementUtil.screenshot()).build());
//			
//			test.log(Status.PASS, result.getName()+" passed",MediaEntityBuilder.createScreenCaptureFromPath(el.takeScreenshot("testpassed")).build());
//			//takeScreenshot
//			
//			
//			
//		} catch (IOException e) {
//			System.out.println("Exception in onTestSuccess" + e.getMessage());
//		}
	}

	public void onTestFailure(ITestResult result) {
		// test.log(Status.FAIL, result.getThrowable());
		
//		try {
//			test.log(Status.FAIL, result.getThrowable());		
//			//test.log(Status.FAIL,result.getName()+" failed", MediaEntityBuilder.createScreenCaptureFromPath(ElementUtil.screenshot()).build());
//			test.log(Status.FAIL,result.getName()+" failed", MediaEntityBuilder.createScreenCaptureFromPath(el.takeScreenshot("Test Failed")).build());
//		} catch (IOException e) {
//		System.out.println("Exception in onTestFailure==>" + e.getMessage());
//		}
//		
//		catch (Exception  e) {
//            System.out.println("test value is "+ test);
//			System.out.println("Exception in onTestFailure::>" + e.getMessage());
//		}
//		
//		finally{
//		afterMethod();
//		}	
	
		
		afterMethod();
		
		
		
	}

	public void onTestSkipped(ITestResult result) {	
		if(test!=null) 	{		
			test.log(Status.SKIP, result.getName() + " skipped");
		}
		
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}
	public void onTestFailedWithTimeout(ITestResult result) {
		el.addFailLog(result.getName() +" has timeout out","NA");
	}
	
	public void onStart(ITestContext context) {
	
	}

	public void onFinish(ITestContext context) {
		afterMethod();
	}
}
