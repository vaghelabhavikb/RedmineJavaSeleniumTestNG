package pom;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilitylib.WebDriverUtilities;

public class SpentTimeTab {

	WebDriverUtilities cmd;

	public SpentTimeTab(WebDriver driver) {
		cmd = new WebDriverUtilities(driver);
	}

	public By logTimeLnk = By.xpath("//span[text()='Log time']");
	public By spentTimeTable = By.tagName("table");

	public void launchSpentTimeCreationForm() {
		cmd.click(logTimeLnk);
	}

	public Map<String, String> getTimeEntry(String timeEntry) {
		
		List<Map<String, String>> tableData = cmd.getTableDataMap(spentTimeTable); // List<List<WebElement>> rows = cmd.getTableDataMap(spentTimeTable); // List<List<String>>
//		for (int i = 0; i < rows.size(); i++) {
//			for (WebElement ele : rows.get(i)) {
//				actualData.add(new ArrayList<String>());
//				actualData.get(i).add(ele.getText());
//			}
//		}
		
		for (Map<String, String> map : tableData) {
			if(map.get("Hours").equals(timeEntry))
				return map;
		}
		
		return new HashMap<String, String>();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
