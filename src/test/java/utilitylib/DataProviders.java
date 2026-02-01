package utilitylib;

import org.testng.annotations.DataProvider;

public class DataProviders {

	
	@DataProvider(name = "ProjectNames")
	public Object[] projectNames() {
		return new Object[] { "Assets", "Fuel Records" };
	}

}
