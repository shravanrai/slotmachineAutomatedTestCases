package pageobjcomp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Pob_betContainer {

	// WebDriver driver;
	// WebDriver driver;

	// @FindBy

	// betContainer
	@FindBy(xpath = ".//*[@id='lastWin']")
	public WebElement lastWin;

	@FindBy(xpath = ".//*[@id='credits']")
	public WebElement credits;

	@FindBy(xpath = ".//*[@id='bet']")
	public WebElement bet;

	@FindBy(xpath = ".//*[@id='dayWinnings']")
	public WebElement dayWinnings;

	@FindBy(xpath = ".//*[@id='lifetimeWinnings']")
	public WebElement lifetimeWinnings;

	@FindBy(xpath = ".//*[@id='betSpinUp']")
	public WebElement betSpinUp;

	@FindBy(xpath = ".//*[@id='betSpinDown']")
	public WebElement betSpinDown;

	@FindBy(xpath = ".//*[@id='spinButton']")
	public WebElement spinButton;

	@FindBy(xpath = ".//*[@id='SlotsInnerContainer']/div[@id='tryMe']")
	public WebElement tryMe;

	// Init factory
	public Pob_betContainer(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// keywords

	public int getLastWin() {
		if (lastWin.getText().equals(""))
			return 0;
		return Integer.valueOf(lastWin.getText());
	}

	public int getCredits() {
		return Integer.valueOf(credits.getText());
	}

	public int getBet() {
		return Integer.valueOf(bet.getText());
	}

	public int getDayWinnings() {
		if (dayWinnings.getText().equals(""))
			return 0;
		return Integer.valueOf(dayWinnings.getText());
	}

	public int getLifetimeWinnings() {
		if (lifetimeWinnings.getText().equals(""))
			return 0;
		return Integer.valueOf(lifetimeWinnings.getText());
	}

	public void click_spinButton() {
		spinButton.click();
	}

	public String getSpinButtonClass() {
		return spinButton.getAttribute("class");
	}

	public void click_SpinUp() {
		betSpinUp.click();
	}

	public void click_SpinDown() {
		betSpinDown.click();
	}

}
