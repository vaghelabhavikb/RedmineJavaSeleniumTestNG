package testnglib;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import utilitylib.WebDriverUtilities;

public class OnFailureListeners implements ITestListener {

	@Override
	public void onTestFailure(ITestResult result) {
		ITestContext context = result.getTestContext();
		WebDriver driver = (WebDriver) context.getAttribute("driver");
		new WebDriverUtilities(driver).takeScreenShot(result.getTestClass().getName() + "." + result.getName().trim());
	}
}
