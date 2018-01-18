package genericcomp;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BaseClass {

	// Start_App
	// ExplicitWait
	// TakeScreehshot
	// extentReport
	// Stop_App

	public static WebDriver driver;
	public static ExtentReports extentReport;
	public static ExtentTest logger;

	public static void explicitWait(WebElement ele, Long T) {
		WebDriverWait wait = new WebDriverWait(driver, T);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public static void start_App() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir") + "/src/test/resources/chromedriver_win32/chromedriver.exe");
		driver = new ChromeDriver();
		System.out.println("Apllication started ..");
		driver.manage().deleteAllCookies();
		driver.get("http://slotmachinescript.com/");
		driver.manage().window().maximize();
		Thread.sleep(3000l);
	}

	public static void stop_App() {
		System.out.println("Apllication stopped ..");
		driver.close();
		driver.quit();
	}

	public static String captureScreenShots(String TC_ID, String Order_Id) throws IOException {

		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String dateStr = df.format(date);

		String ScreenShotRepositoryPath = System.getProperty("user.dir") + "\\src\\test\\resources\\Screenshots\\";
		TakesScreenshot ss = (TakesScreenshot) driver;
		File fis = ss.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(fis,
				new File(ScreenShotRepositoryPath + "SlotMachine_" + TC_ID + " " + Order_Id + "_" + dateStr + ".png"));

		return ScreenShotRepositoryPath + "SlotMachine_" + TC_ID + " " + Order_Id + "_" + dateStr + ".png";
	}

	@BeforeSuite
	public static void extentReport() {

		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String dateStr2 = df.format(date);

		extentReport = new ExtentReports(
				System.getProperty("user.dir") + "\\src\\test\\resources\\Reports\\" + dateStr2 + ".html", true);
	}

	public static void Stop_extentReports() {
		extentReport.endTest(logger);
		extentReport.flush();
	}

}
