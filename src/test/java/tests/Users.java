package tests;

import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Users extends Common{

	@BeforeMethod(alwaysRun = true)
	public void usersTCSetUp() {
		po.landingPage().navToAdminPage();
	}

	@Test(groups = {
			"regression" }, dataProviderClass = utilitylib.DataProviders.class, dataProvider = "CreateUsers", description = "Post time spent for an issue")
	public void createUsers(LinkedHashMap<String, String> map) {
		po.adminPage().openUsersPage();
		po.usersQueryPage().openUserCreationForm();
		po.createUserForm().createUser(map);
		po.createUserForm().navToUsersQueryPage();

		SoftAssert sa = new SoftAssert();
		Map<String, String> act = po.usersQueryPage().getUserEntry(map.get("Login"));
		for (String key : map.keySet()) {
			if (!key.equals("Password")) {
				sa.assertTrue(act.get(key).contains(map.get(key)),
						"Comparison For: " + key + "|Actual: " + act.get(key) + "|Expected: " + map.get(key) + "|");
			}
		}
		sa.assertAll();
	}

}
