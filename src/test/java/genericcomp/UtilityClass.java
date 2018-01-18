package genericcomp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;



public class UtilityClass {

	
	private String loadProperties(String key) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
        Properties prop = new Properties();
        File fis= new File("C:\\Users\\srai020\\ws_selenium\\Prowarenes.selenium.framework.practice\\src\\main\\resources\\env.properties");
        prop.load(new FileReader(fis));
        
        return prop.getProperty(key);
	}
}
