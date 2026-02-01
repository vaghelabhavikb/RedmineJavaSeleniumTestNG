package utilitylib;

import java.io.FileInputStream;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static config.EnvVars.*;

public class PropertiesTestDataFetch {

	Properties p;
	FileInputStream fis;
	
	public PropertiesTestDataFetch(String fileName) {
		
		try {
			fis = new FileInputStream(tdFolderPath + fileName + propertiesFileExt);
			p = new Properties();
			p.load(fis);
				
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getTD(String key) {
		return this.p.getProperty(key);
	}
	
}











