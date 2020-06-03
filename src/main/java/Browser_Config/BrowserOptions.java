package Browser_Config;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserOptions {
	
	public static ChromeOptions setChromeOptions() {
	ChromeOptions options = new ChromeOptions();
	Map<String, Object> prefs=null;
	prefs = new HashMap<String, Object>();
	prefs.put("credentials_enable_service", false);
	prefs.put("profile.password_manager_enabled", false);
	prefs.put("profile.default_content_settings.geolocation", 2);
	options.setExperimentalOption("prefs", prefs);
	options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
	options.addArguments("test-type");
	options.setExperimentalOption("useAutomationExtension", false);
	options.addArguments("start-maximized");
	options.addArguments("--disable-extensions");
	options.addArguments("--js-flags=--expose-gc");
	options.addArguments("--enable-precise-memory-info");
	options.addArguments("--disable-popup-blocking");
	 options.addArguments("--disable-default-apps");
	 //options.addArguments("--incognito");
     return options;
}
	
	public void setFirefoxOptions() {
	
		
}
	
	public void setAndroid() {
	
		
}
}
