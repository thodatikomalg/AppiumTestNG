package tests;
import org.testng.annotations.AfterSuite;
//import io.appium.java_client.android;
//import io.appium.java_client.screenrecording;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import tests.ConfigAppium;

public class AppTests extends ConfigAppium {

	WebDriverWait wait;
	File file;
	FileWriter writer;
	AppiumServer AppiumServerObj= new AppiumServer();
	ConfigAppium ConfigAppiumObj =  new ConfigAppium();
	Process process;
	
	
	@BeforeSuite
	public void launchApp() throws InterruptedException
	{
		process = AppiumServerObj.startServer();
		Thread.sleep(5000);
		ConfigAppiumObj.driverMethod();	
		//driver.resetApp();
		wait = new WebDriverWait(driver,20);
		Thread.sleep(2000);
	}

//	@Test(priority=1)
	public void login() throws Exception {
		String Path_TestData = "C:\\Users\\Komal\\eclipse-workspace\\AppiumTest\\src\\test\\testData\\";
	    String File_TestData = "Logins.xlsx";
	    
		ExcelUtils.setExcelFile(Path_TestData + File_TestData,"Sheet1");
		System.out.println(Path_TestData+File_TestData);
		int i;
		for (i=1;i<=10;i++)
		{
		String username = ExcelUtils.getCellData(i,3); //cell starts with 0 count
		String password = "password1";
		
		driver.findElement(By.id("com.rccl.royalcaribbean.debug:id/launch_pad_sign_in")).click();
		driver.findElement(By.id("com.rccl.royalcaribbean.debug:id/guest_sign_in_input_layout_user_email")).sendKeys(username);
		driver.findElement(By.id("com.rccl.royalcaribbean.debug:id/guest_sign_in_input_layout_password")).sendKeys(password);
		driver.hideKeyboard();
		driver.findElement(By.id("com.rccl.royalcaribbean.debug:id/guest_sign_in_button")).click();
		Thread.sleep(5000);
		if(driver.findElement(By.id("com.rccl.royalcaribbean.debug:id/guest_find_reservation_info_text1")).getText() == "Link a cruise reservation")
		{
			System.out.println("Login succesfull with: "+ username);
			break;
			}
			else fail("Login Unsuccessfull");
		}
		}

//	@Test(priority=2)
	public void navigateToHomePlanner() {
		driver.findElement(By.id("com.rccl.royalcaribbean.debug:id/guest_find_reservation_info_text_have_not_booked")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Allure Of The Seas']")).click();
		driver.findElement(By.id("com.rccl.royalcaribbean.debug:id/guest_choose_a_sailing_date_item_text")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.rccl.royalcaribbean.debug:id/tab_discover")));
	}
	
//	@Test(priority=3)
	public void homePlanner() {
		driver.findElement(By.id("com.rccl.royalcaribbean.debug:id/tab_discover")).click();
	}
	
//	@Test(priority=4)
	public void selectActivity() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.rccl.royalcaribbean.debug:id/button_activities")));
		driver.findElement(By.id("com.rccl.royalcaribbean.debug:id/button_activities")).click();
		driver.findElement(By.id("com.rccl.royalcaribbean.debug:id/card_title")).click();
		driver.findElement(By.id("com.rccl.royalcaribbean.debug:id/back_arrow")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Games & Competitions']")).click();
		driver.findElement(By.id("com.rccl.royalcaribbean.debug:id/card_title")).click();
		driver.findElement(By.id("com.rccl.royalcaribbean.debug:id/back_arrow")).click();
		driver.findElement(By.id("com.rccl.royalcaribbean.debug:id/plans_header_back")).click();
	}
	
//	@Test(priority=5)
	public void selectDining() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.rccl.royalcaribbean.debug:id/button_dinning")));
		driver.findElement(By.id("com.rccl.royalcaribbean.debug:id/button_dinning"));
		driver.findElement(By.id("com.rccl.royalcaribbean.debug:id/card_title")).click();
		driver.findElement(By.id("com.rccl.royalcaribbean.debug:id/back_arrow")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@text='Bars & Lounges']")).click();
		driver.findElement(By.id("com.rccl.royalcaribbean.debug:id/card_title")).click();
		driver.findElement(By.id("com.rccl.royalcaribbean.debug:id/back_arrow")).click();
		driver.findElement(By.id("com.rccl.royalcaribbean.debug:id/plans_header_back"));
	}
	
//	@Test(priority=6)
	public void selectShorex() {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.rccl.royalcaribbean.debug:id/button_excursions")));
			driver.findElement(By.id("com.rccl.royalcaribbean.debug:id/button_excursions"));
			driver.findElement(By.id("com.rccl.royalcaribbean.debug:id/card_title")).click();
			driver.findElement(By.id("com.rccl.royalcaribbean.debug:id/back_arrow")).click();
			driver.findElement(By.xpath("//android.widget.TextView[text()='Fort Lauderdale']")).click();
			driver.findElement(By.id("com.rccl.royalcaribbean.debug:id/card_title")).click();
			driver.findElement(By.id("com.rccl.royalcaribbean.debug:id/back_arrow")).click();
			driver.findElement(By.id("com.rccl.royalcaribbean.debug:id/plans_header_back"));
	}
	
	@AfterSuite
	public void stopProcess() throws Throwable {
		driver.closeApp();
		System.out.println("Drivers has been stopped");
		
		List<LogEntry> logEntries = driver.manage().logs().get("logcat").getAll();
		file = new File("C:\\Users\\Komal\\eclipse-workspace\\AppiumTest\\src\\test\\LogcatLogs\\logcat.txt");
		PrintWriter log_file_writer = new PrintWriter(file);
	    log_file_writer.println(logEntries);
	    log_file_writer.flush();
	    
	    AppiumServerObj.stopServer();
	    driver.quit();
	    Runtime.getRuntime().exec("adb -s emulator-5554 emu kill");
	}

}
