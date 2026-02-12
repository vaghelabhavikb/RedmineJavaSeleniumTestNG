package utilitylib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

public class DataProviders {

	JsonTestDataFetch createIssuesJson = new JsonTestDataFetch("CreateIssues");
	
	@DataProvider(name = "ProjectNames")
	public Object[] projectNames() {
		return new Object[] { "Assets", "Fuel Records" };
	}

	@DataProvider(name = "ProjectDetailsWithOptionalFields")
	public Object[] projectCreationWithOptionalFields() {

		ArrayList<Map<String, String>> projects = new ArrayList<Map<String, String>>();
		Map<String, String> firstProject = new HashMap<String, String>();
		Map<String, String> secondProject = new HashMap<String, String>();

		firstProject.put("Project Name", "Vendors");
		firstProject.put("Description", "Vendors Records");
		firstProject.put("Mark Public", "true");
		firstProject.put("Sub Project Of", "DocID");

		secondProject.put("Project Name", "Accounts");
		secondProject.put("Description", "Accounts Records");
		secondProject.put("Mark Public", "false");
		secondProject.put("Identifier", "acc");
		secondProject.put("Sub Project Of", "DocID1");
		
		projects.add(firstProject);
		projects.add(secondProject);

		return new Object[] {
			projects.get(0), projects.get(1)	
		};
	}
	
	@DataProvider(name="CreateIssues")
	public Object[] createIssues() {
		List<LinkedHashMap<String, String>> listMaps = new ArrayList<LinkedHashMap<String, String>>();
		listMaps = createIssuesJson.getJsonObjInJsonArray("issues");
		return new Object[] { listMaps.get(0), listMaps.get(1) };
	}

}
