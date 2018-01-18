package pageobjcomp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Pob_Home {

	// WebDriver driver;
	WebDriver driver;

	// @FindBy
	// Main Header //Add a Slot Machine like this one to your site
	@FindBy(xpath = ".//*[@id='header']/div[3]/h1")
	public WebElement headline;

	@FindBy(xpath = ".//*[@id='header']/div[3]/h1/strong")
	public WebElement headlineStrong;

	@FindBy(xpath = ".//*[@id='header']/div[1]/img[@alt='Slot Machine Script Logo']")
	public WebElement slotMachineImage;

	// ##########################################################################################
	// Init factory
	public Pob_Home(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// ##########################################################################################
	// keywords
	public String getHeadLine() {
		return headline.getText();
	}

	public String getHeadLineStrong() {
		return headlineStrong.getText();
	}

	public String getSlotMachineAlt() {
		return slotMachineImage.getAttribute("alt");
	}
}
