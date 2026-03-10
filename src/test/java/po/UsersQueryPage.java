package po;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilitylib.WebDriverUtilities;

public class UsersQueryPage {

	WebDriverUtilities cmd;
	
	public UsersQueryPage(WebDriver driver) {
		cmd = new WebDriverUtilities(driver);
	}
	
	public By newUserLK = By.xpath("//a/*[text()='New user']");
	public By userTable = By.tagName("table");
	
	public void openUserCreationForm() {
		cmd.click(newUserLK);
	}
	
	public Map<String, String> getUserEntry(String userEntry) {
		
		List<Map<String, String>> tableData = cmd.getTableDataMap(userTable); // List<List<WebElement>> rows = cmd.getTableDataMap(spentTimeTable); // List<List<String>>
//		for (int i = 0; i < rows.size(); i++) {
//			for (WebElement ele : rows.get(i)) {
//				actualData.add(new ArrayList<String>());
//				actualData.get(i).add(ele.getText());
//			}
//		}
		
		for (Map<String, String> map : tableData) {
			if(map.get("Login").equals(userEntry))
				return map;
		}
		
		return new HashMap<String, String>();
		
	}
}
