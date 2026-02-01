package utilitylib;

import static config.EnvVars.jsonFileExt;
import static config.EnvVars.tdFolderPath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;

public class JsonTestDataFetch {

	String jsonFile;
	DocumentContext docReader;
	Object document;

	public ArrayList<String> tdList = new ArrayList<String>();
	public HashMap<String, ArrayList<String>> tdMap = new HashMap<String, ArrayList<String>>();
	
//	public JsonTestDataFetch(String fileName) {
//
//		jsonFile = testDataFolderPath + fileName;
//		try {
//			docReader = JsonPath.parse(new File(jsonFile + jsonFileExt));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	public JsonTestDataFetch(Path path) {
		try {
			docReader = JsonPath.parse(path.toFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public JsonTestDataFetch() {
	}

	public String getTD(String jsonPath) {
		return docReader.read(jsonPath);

	}

	public Set<String> getJsonObjKeys(String jsonPath) {
		JSONObject obj = docReader.read(jsonPath);
		return obj.keySet();
	}
	
	public LinkedHashMap<String, String> getJsonObjAsMap(String jsonPath) {
		return docReader.read(jsonPath);
	}
	
	public ArrayList<String> getJsonArrayAsArrayList(String jsonPath) {
		JSONArray arr = docReader.read(jsonPath);
		ArrayList<String> result = new ArrayList<String>();
		int len = arr.size();
		for (int i = 0; i < len; i++) {
			result.add(arr.get(i).toString());
		}
		return result;
	}
	
	public String[] getJsonArrayAsArray(String jsonPath) {
		JSONArray arr = docReader.read(jsonPath);
		Object[] obj = arr.toArray();
		return Arrays.copyOf(obj, obj.length, String[].class);
//		return (String[]) arr.toArray();
	}
	
	public void addToTDList(String td) {
		tdList.add(td);
	}
	
	public String getFromTDList(int index) {
		return tdList.get(index);
	}

	public ArrayList<String> getTDList() {
		return tdList;
	}
	
	public void clearTDList() {
		tdList.clear();
	}
	
	public void addToTDMap(String td, ArrayList<String> list) {
		tdMap.put(td, list);
	}
	
	public ArrayList<String> getFromTDMap(String key) {
		return tdMap.get(key);
	}
	
	public Set<String> getKeysFromTDMap() {
		return tdMap.keySet();
	}
	
	public void clearTDMap() {
		tdMap.clear();
	}
}
