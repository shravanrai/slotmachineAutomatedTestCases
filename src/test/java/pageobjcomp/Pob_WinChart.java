package pageobjcomp;

import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class Pob_WinChart {

	// WebDriver driver;
	WebDriver driver;

	// @FindBy

	@FindBy(xpath = ".//*[@id='ReelContainer']/div[@class='reel']")
	public List<WebElement> ReelContainerList; // getStyle

	// Main Header //Add a Slot Machine like this one to your site
	@FindBy(xpath = ".//*[@id='SlotsOuterContainer'][@class='won']//div[@class='trPrize won']")
	public WebElement winChartWon;

	@FindBy(xpath = ".//*[@class='prizes_list_slot_machine'][@style='']//div/span")
	public List<WebElement> trPrizetdLayoutList;

	// Init factory
	public Pob_WinChart(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// keywords
	// if Won Not fount. //definitely unsuccessful condition.
	public boolean isSuccessfulSpin() {
		try {
			return winChartWon.isDisplayed();
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return false;
		}

	}

	// matchTheReels // if this is true then definitely won condition.
	public boolean isAllStylesMatch() {
		if (ReelContainerList.get(0).getAttribute("style").equals(ReelContainerList.get(1).getAttribute("style"))) {
			ReelContainerList.get(0).getAttribute("style").equals(ReelContainerList.get(2).getAttribute("style"));
			return true;
		}
		return false;
	}

	// check multiplication
	public void isMultiplicationCorrect(int baseValue, int bet, int ActualValue) {
		Assert.assertEquals(ActualValue, baseValue * bet);
	}
}
