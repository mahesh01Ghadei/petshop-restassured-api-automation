
package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkRepoter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String fileName;
	
	public void onStart(ITestContext testContext) {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		fileName = "Test-Report-"+ timeStamp + ".html";
		sparkRepoter = new ExtentSparkReporter(".\\reports\\"+ fileName);
		sparkRepoter.config().setDocumentTitle("PetShop API Automation Report");
		sparkRepoter.config().setReportName("API Testing");
		sparkRepoter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkRepoter);
		extent.setSystemInfo("Application", "PetShop");
		extent.setSystemInfo("Module", "User");
		extent.setSystemInfo("SubModule", "User");
		extent.setSystemInfo("UserName", System.getProperty("user.name"));
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
		
    }
	
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+" got successfully executed.");
	}
	
    public void onTestFailure(ITestResult result) {
    	test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName()+" got failed.");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
    
	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+" got skipped.");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	
    public void onTestStart(ITestContext context) {
	    
	}
	  
    public void onFinish(ITestContext context) {
	    extent.flush();
	}
	

}
