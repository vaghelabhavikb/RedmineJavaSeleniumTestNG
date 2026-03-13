package testnglib;

import org.openqa.selenium.WebDriver;
import org.testng.IConfigurationListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

import utilitylib.WebDriverUtilities;

public class OnConfigFailure implements IConfigurationListener {
	@Override
	public void onConfigurationFailure(ITestResult result) {
		ITestContext context = result.getTestContext();
		WebDriver driver = (WebDriver) context.getAttribute("driver");
		new WebDriverUtilities(driver)
				.takeScreenShot("ConfigFailure." + result.getTestClass().getName() + "." + result.getName().trim());
	}
}
