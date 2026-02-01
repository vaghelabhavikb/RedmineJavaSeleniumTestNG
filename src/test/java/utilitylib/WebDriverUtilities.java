package utilitylib;

import static config.EnvVars.normalww;
import static config.EnvVars.resultFolderPath;
import static config.EnvVars.shortww;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.WheelInput;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import config.DriverWait;

public class WebDriverUtilities {

	public WebDriver driver;
	public WebDriverWait ww;
	public WebDriverWait wwShort;
	public JavascriptExecutor js;
	public int textGetAttempt = 5;
	public int textGetAttemptInterval = 1;

	public WebDriverUtilities(WebDriver wd) {
		driver = wd;
		ww = new WebDriverWait(driver, Duration.ofSeconds(normalww));
		wwShort = new WebDriverWait(driver, Duration.ofSeconds(shortww));
		js = (JavascriptExecutor) driver;
	}

	public void click(By by) {
		ww.until(ExpectedConditions.elementToBeClickable(by)).click();
	}

	public void sliderValueIncrease(By by) {
		ww.until(ExpectedConditions.elementToBeClickable(by)).click();
		Actions ac = new Actions(driver);
		ac.pause(Duration.ofSeconds(2)).sendKeys(Keys.ARROW_RIGHT).sendKeys(Keys.ARROW_RIGHT).build()
		    .perform();
	}

	public WebElement findElement(By by) {
		return ww.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public boolean isElementVisible(By by) {
		WebElement ele = null;
		try {
			ele = wwShort.until(ExpectedConditions.visibilityOfElementLocated(by));
		} catch (Exception e) {
		}
		if (ele == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isElementPresent(By by) {
		WebElement ele = null;
		try {
			ele = wwShort.until(ExpectedConditions.presenceOfElementLocated(by));
		} catch (Exception e) {
		}

		if (ele == null) {
			return false;
		} else {
			return true;
		}
	}

	public List<WebElement> findElements(By by, DriverWait waitType) {
		List<WebElement> eles = null;
		try {
			if (waitType == DriverWait.NORMAL_WAIT) {
				eles = ww.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
			} else if (waitType == DriverWait.SHORT_WAIT) {
				eles = wwShort.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
			}
			return eles;
		} catch (Exception e) {
			return driver.findElements(by);
		}
	}

	public List<String> getTextOfMultipleElements(By by) {
		List<String> texts = new ArrayList<String>();
		for (WebElement ele : ww.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by))) {
			String eleText = ele.getText().trim();
			texts.add(eleText);
			Reporter.log(eleText);
		}
		return texts;
	}

	public void click(WebElement element) {
		ww.until(ExpectedConditions.elementToBeClickable(element)).click();
	}

	public void waitForElementToBeVisible(By by) {
		ww.until(ExpectedConditions.visibilityOfElementLocated(by));
	}

	public void waitForElementToBeInvisible(By by) {
		ww.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	public boolean checkElementIsClickable(By by) {
		WebElement ele = null;
		try {
			ele = ww.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
		}
		if (ele == null) {
			return false;
		} else {
			return true;
		}
	}

	public WebElement getIfElementIsClickable(By by) {
		WebElement ele = null;
		try {
			ele = ww.until(ExpectedConditions.elementToBeClickable(by));
		} catch (Exception e) {
		}
		return ele;
	}

	public void contextClick(By by) {
		Actions ac = new Actions(driver);
		this.waitForElementToBeClickable(by);
		ac.contextClick(driver.findElement(by)).build().perform();
	}

	public void scrollInToElement(By by) {
		Actions ac = new Actions(driver);
		ac.scrollToElement(driver.findElement(by)).build().perform();
	}

	public void scrollInToElement(WebElement ele) {
		Actions ac = new Actions(driver);
		ac.scrollToElement(ele).build().perform();
	}

	public void waitForAlertToDisplay() {
		ww.until(ExpectedConditions.alertIsPresent());
	}

	public void sendText(By by, String str) {
		CharSequence cs = str;
		ww.until(ExpectedConditions.elementToBeClickable(by)).sendKeys(cs);
	}

	public void clearText(By by) {
		ww.until(ExpectedConditions.elementToBeClickable(by)).clear();
	}

	public void sendText(By by, Keys key) {
		ww.until(ExpectedConditions.elementToBeClickable(by)).sendKeys(key);
	}

	public void keyboardSendText(By by, String str) {
		new Actions(driver).sendKeys(findElement(by), str).build().perform();
	}

	public void keyboardSendText(By by, Keys key) {
		new Actions(driver).sendKeys(findElement(by), key).build().perform();
	}

	public String getText(By by) {
		WebElement ele = ww.until(ExpectedConditions.presenceOfElementLocated(by));
		while (textGetAttempt > 0) {
			if (ele.getText().trim().equals("")) {
				wait(textGetAttemptInterval);
				textGetAttempt--;
			} else {
				break;
			}
		}
		return ele.getText().trim();
	}

	public String getText(WebElement ele) {
		ww.until(ExpectedConditions.visibilityOf(ele));
		while (textGetAttempt > 0) {
			if (ele.getText().trim().equals("")) {
				wait(textGetAttemptInterval);
				textGetAttempt--;
			} else {
				break;
			}
		}
		return ele.getText().trim();
	}

	public String getAttribute(By by, String att) {
		WebElement we = ww.until(ExpectedConditions.presenceOfElementLocated(by));
		return ww.until(ExpectedConditions.presenceOfElementLocated(by)).getAttribute(att).trim();
	}

	public boolean checkElementIsNotPresent(By by) {
		return wwShort.until(ExpectedConditions.invisibilityOfElementLocated(by));
	}

	public Select getSelectElement(By by) {
		return new Select(ww.until(ExpectedConditions.presenceOfElementLocated(by)));
	}

	public boolean checkElementIsEnabled(By by) {
		boolean result = false;
		try {
			result = ww.until(ExpectedConditions.presenceOfElementLocated(by)).isEnabled();
		} catch (Exception e) {
		}
		return result;
	}

	public WebElement waitForElementToBeClickable(By by) {
		return ww.until(ExpectedConditions.elementToBeClickable(by));
	}

	public void waitForElementTextToBe(By by, String text) {
		ww.until(ExpectedConditions.textToBe(by, text));
	}

	public List<List<WebElement>> getTableData(By by) {
		WebElement table = driver.findElement(by);
		List<WebElement> rows = table.findElements(By.xpath("//tbody/tr"));
		List<List<WebElement>> tableData = new ArrayList<List<WebElement>>();
		for (WebElement webElement : rows) {
			tableData.add(webElement.findElements(By.tagName("td")));
		}
		return tableData;
	}

	public List<WebElement> findMultiElement(By by) {
//	public List<WebElement> findMultiElement(By by, int minElementsCount, int findAllAttemptCount){
		return ww.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
		// List<WebElement> elements = null;
//		while(findAllAttemptCount > 0) {
//			elements = ww.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
//			if(minElementsCount > elements.size()) {
//				findAllAttemptCount--;
//				try {
//					Thread.sleep(10000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			} else {
//				break;
//			}
//		}
//		return elements;
	}

	public void takeScreenShot(String fileName) {
		File f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(f,
			    new File(resultFolderPath + fileName + Instant.now().toString().replace(":", "-") + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mouseClick(By by) {
		Actions a = new Actions(driver);
		WebElement ele = findElement(by);
		a.click(ele).build().perform();
	}

	public void moveToElement(By by) {
		Actions a = new Actions(driver);
		WebElement ele = findElement(by);
		a.moveToElement(ele).pause(Duration.ofSeconds(1)).build().perform();
	}

	public void moveToElement(WebElement ele) {
		Actions a = new Actions(driver);
		a.moveToElement(ele).pause(Duration.ofSeconds(1)).build().perform();
	}

	public void jsClick(By by) {
		WebElement ele = findElement(by);
		js.executeScript("arguments[0].click();", ele);
	}

	public void wait(int sec) {
		try {
			Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void jsScrollDownViewPointWithinPageTillEleIsDisplayed(By scrollView, By by,
	    int maxScrollCount) {
		WebElement scrollableEle = findElement(scrollView);
		Rectangle rec = scrollableEle.getRect();
		int scrollHeight = rec.getHeight();

		while (maxScrollCount > 0) {
			wait(1);
			try {
				if (driver.findElement(by) != null) {
					break;
				}
			} catch (Exception e) {
				WheelInput.ScrollOrigin scrollOrigin = WheelInput.ScrollOrigin.fromElement(scrollableEle);
				new Actions(driver).scrollFromOrigin(scrollOrigin, 0, scrollHeight).perform();
				maxScrollCount--;
			}
		}
	}

	public void jsScrollDownPageTillEleIsDisplayed(By by, int maxScrollCount) {
		while (maxScrollCount > 0) {
			wait(3);
			try {
				if (driver.findElement(by) != null) {
					break;
				}
			} catch (Exception e) {
				js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
				maxScrollCount--;
			}
		}
	}

	public void jsScrollUpTillEleIsDisplayed() {

	}

	public void zoomOutBrowserWindow() {
		((JavascriptExecutor) driver).executeScript("document.body.style.zoom='80%';");
	}

	public void superZoomOutBrowserWindow() {
		((JavascriptExecutor) driver).executeScript("document.body.style.zoom='50%';");
	}

	public void refresh() {
		driver.navigate().refresh();
	}

	public String getDOMProperty(By by, String property) {
		return findElement(by).getDomProperty(property);
	}

//	public void windowDialogAction(String actionName, String file) {
//		DesiredCapabilities desktopCapabilities = new DesiredCapabilities();
//		desktopCapabilities.setCapability("appium:app", "Root");
//		desktopCapabilities.setCapability("appium:newCommandTimeout", 0);
//
//		try {
//			WindowsDriver session =
//			    new WindowsDriver(new URL("http://127.0.0.1:4723"), desktopCapabilities);
//			WebDriverWait wait = new WebDriverWait(session, Duration.ofSeconds(120));
//
//			switch (actionName.toLowerCase()) {
//				case "attach file":
//					WebElement windowPopup = wait.until(
//					    ExpectedConditions.presenceOfElementLocated(AppiumBy.className("Chrome_WidgetWin_1")));
//					WebElement windowPopupFileNameTB = windowPopup.findElement(AppiumBy.className("Edit"));
////							(WebElement) wait.until(ExpectedConditions
////					    .presenceOfNestedElementLocatedBy(windowPopup, AppiumBy.accessibilityId("1148")));
//					windowPopupFileNameTB.sendKeys(attachFilesFolderPath + file + Keys.ENTER);
//					session.quit();
//					break;
//				default:
//					break;
//			}
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
//
//	}

	public void dragNDrop(By from, By to) {
		scrollInToElement(from);

		WebElement fromEle = findElement(from);
		WebElement toEle = findElement(to);

		Rectangle fromRec = fromEle.getRect();
		Rectangle toRec = toEle.getRect();

//		Rectangle driverSize = findElement(By.tagName("body")).getRect();
//
//		int dW = driverSize.getWidth();
//		int dH = driverSize.getHeight();

//		System.out.println("dW:" + dW + "|dH: " + dH);

		int sX = fromRec.getX() + fromRec.width / 2;
		int sY = fromRec.getY() + fromRec.height / 2;

		int eX = toRec.getX() + toRec.getWidth() / 2;
		int eY = sY;

//		System.out.println("sX:" + sX + "|sY: " + sY);
//		System.out.println("eX:" + eX + "|eY: " + eY);

//		new Actions(driver).dragAndDrop(fromEle, toEle).perform();
//		new Actions(driver).clickAndHold(fromEle).pause(Duration.ofSeconds(5)).moveByOffset(eX - sX - 10, 0)
//		    .pause(Duration.ofSeconds(5)).moveByOffset(eX - sX + 10, 0).pause(Duration.ofSeconds(5))
//		    .release(fromEle).build().perform();

//		moveToElement(from);
//		PointerInput input = new PointerInput(Kind.MOUSE, "default mouse");
//		Interaction i1 = input.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), sX, sY);
//		Interaction i2 = input.createPointerDown(PointerInput.MouseButton.LEFT.asArg());
//		Interaction i3 = input.createPointerMove(Duration.ZERO, PointerInput.Origin.pointer(), eX-sX, 0);
//		Interaction i4 = input.createPointerUp(PointerInput.MouseButton.LEFT.asArg());
//		new Actions(driver).tick(i1).pause(Duration.ofSeconds(2)).tick(i2).pause(Duration.ofSeconds(2))
//		    .tick(i3).pause(Duration.ofSeconds(2)).tick(i4).pause(Duration.ofSeconds(2)).build().perform();
//		wait(20);
		PointerInput input = new PointerInput(Kind.MOUSE, "default mouse");
		Sequence seq = new Sequence(input, 0);
		seq.addAction(
		    input.createPointerMove(Duration.ofSeconds(2), PointerInput.Origin.viewport(), sX, sY));
		seq.addAction(input.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		seq.addAction(
		    input.createPointerMove(Duration.ofSeconds(2), PointerInput.Origin.viewport(), eX, eY));
		seq.addAction(input.createPointerMove(Duration.ofSeconds(2), PointerInput.Origin.viewport(), 0, 0));
		seq.addAction(
		    input.createPointerMove(Duration.ofSeconds(2), PointerInput.Origin.viewport(), eX, eY));
		seq.addAction(input.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

		((RemoteWebDriver) driver).perform(Collections.singletonList(seq));
	}

	public boolean isSelected(By by) {
		return ww.until(ExpectedConditions.visibilityOfElementLocated(by)).isSelected();
	}

}
