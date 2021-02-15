package  tests;
 
import java.io.File;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.commons.exec.CommandLine;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
 
public class ConfigAppium {
	protected static AndroidDriver driver;
	
	public AndroidDriver<MobileElement> driverMethod() throws InterruptedException{

			
		File appDir = new File("C:\\Users\\Komal\\Downloads");
	    File app = new File(appDir, "RCI Version 1.11.0.27 RSCI.apk"); 
	    
		//Set the Desired Capabilities
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("avd", "Pixel_XL_API");
		caps.setCapability("avdLaunchTimeout","20000");
		caps.setCapability("deviceName", "Pixel XL API");
		caps.setCapability("udid", "emulator-5554"); //Give Device ID of your mobile phone
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "8.1.0");
		caps.setCapability("app", app.getAbsolutePath());
		caps.setCapability("appPackage", "com.rccl.royalcaribbean.debug");
		caps.setCapability("appActivity", "com.rcl.excalibur.activity.SplashActivity");
		caps.setCapability("noReset", "false");
		
	//Instantiate Appium Driver
			
		try {
				driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
				System.out.println("App launched successfully");
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				Thread.sleep(5000);
				driver.quit();
				return driver;
		
		} catch (MalformedURLException e) {
				System.out.println(e.getMessage());
		}
		return driver;
}

		}


