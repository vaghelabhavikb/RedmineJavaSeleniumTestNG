package config;

import java.nio.file.Path;
import java.nio.file.Paths;

public class EnvVars {

//	public static String testDataFolderPath = System.getProperty("user.dir") + "\\src\\test\\resources\\TestData\\";
	public static final Path tdFolderPath = Paths.get("src", "test", "resources", "TestData");
	public static final Path configDataFolderPath = Paths.get("src", "test", "resources", "ConfigData");
	public static final Path jsonTDPath = tdFolderPath.resolve("JsonData");
	public static final Path tdProjectsPath = jsonTDPath.resolve("Projects");
	public static final Path tdUsersPath = jsonTDPath.resolve("Users");

	// Alternative styles — all correct and portable
//	Path base = Paths.get("src/test/resources");
//	Path file1 = base.resolve("TestData/test.xlsx");           // resolves child path
//	Path file2 = base.resolve(Paths.get("TestData", "test.xlsx"));
	public static String attachFilesFolderPath = tdFolderPath + "AttachFiles\\";

	public static String resultFolderPath = System.getProperty("user.dir") + "\\Result\\";
	public static String logFilePath = resultFolderPath + "Log\\log.txt";

	public static final String baseURL1 = "http://127.0.0.1:3000/"; 
	public static final String baseURL2 = "http://127.0.0.1:3001/";// don't use localhost as it is interfering with FireFox Cookies addition
	
	public static final String jsonFileExt = ".json";
	public static final String propertiesFileExt = ".properties";
	public static final String excelFileExt = ".xlsx";
	public static final String textFileExt = ".txt";

//Enable kill chrome logic before starting of execution
	public static final boolean killChrome = true;
	public static final int maxInstanceToKeepOpen = 2;

//Script hard waits
	public static final int quickwait1 = 1;
	public static final int quickwait2 = 2;
	public static final int quickwait3 = 3;
	public static final int shortwait = 4;
	public static final int mediumwait = 8;
	public static final int longwait = 12;

//WebDriver Waits
	public static final int shortww = 5;
	public static final int normalww = 30;
}
