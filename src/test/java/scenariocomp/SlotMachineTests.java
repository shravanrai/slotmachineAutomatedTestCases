package scenariocomp;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import genericcomp.BaseClass;
import pageobjcomp.Pob_Home;
import pageobjcomp.Pob_WinChart;
import pageobjcomp.Pob_betContainer;

public class SlotMachineTests extends BaseClass {

	public static Logger log = Logger.getLogger(SlotMachineTests.class);

	@BeforeClass
	public void init() throws InterruptedException {
		start_App();
	}

	@AfterClass
	public void tearDown() {
		stop_App();
	}

	@Test(enabled = true)
	public void testExtentReport() throws IOException {

		try {
			logger = extentReport.startTest("extentReportTest");
			logger.log(LogStatus.PASS, "This is SlotMachine Home Page Test",
					logger.addScreenCapture(captureScreenShots("TC_ID_01", "Order_Set_01")));
			Stop_extentReports();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * TestCaseID: SM_01 :Verify SlotMachineScript loaded fine , verify availability
	 * of the Image with "SlotMachineSCRIPT.COM" text on page.
	 * 
	 * @author shrarai
	 * 
	 */
	@Test(enabled = true, alwaysRun = true,groups="smoke")
	public void testHomeImage() {
		Pob_Home pobH = new Pob_Home(driver);
		Assert.assertEquals("Slot Machine Script Logo", pobH.getSlotMachineAlt());
	}

	/**
	 * TestCaseID: SM_02 :Verify Correct Headline message is displayed.
	 * 
	 * @author shrarai
	 */
	@Test(enabled = true, alwaysRun = true,groups="smoke")
	public void testHeadlineMessage() {
		Pob_Home pobH = new Pob_Home(driver);
		Assert.assertTrue(pobH.getHeadLine().equalsIgnoreCase("Add a Slot Machine like this one to your site Today!"));
	}

	/**
	 * TestCaseID: SM_03 : Verify the "lastWin" should be "Blank" in betContainer.
	 * 
	 * @author shrarai
	 */

	@Test(enabled = true, groups = "FirstTimeVisitor")
	public void test_Init_lastWin() {
		Pob_betContainer pobB = new Pob_betContainer(driver);
		Assert.assertEquals(pobB.getLastWin(), 0);
	}

	/**
	 * TestCaseID: SM_04:Verify the ""credits" or Total Spin" should be "5"  in
	 * betContainer.
	 * 
	 * @author shrarai
	 */

	@Test(enabled = false, groups = "FirstTimeVisitor")
	public void test_Init_credits() {
		Pob_betContainer pobB = new Pob_betContainer(driver);
		Assert.assertEquals(pobB.getCredits(), 50);
	}

	/**
	 * TestCaseID: SM_05 :Verify the "bet" should be "1" in betContainer.
	 * 
	 * @author shrarai
	 */

	@Test(enabled = true, groups = "FirstTimeVisitor")
	public void test_Init_Bet() {
		Pob_betContainer pobB = new Pob_betContainer(driver);
		Assert.assertEquals(pobB.getBet(), 1);
	}

	/**
	 * TestCaseID: SM_06: Verify dayWinnings value is "0" in betContainer.
	 * 
	 * @author shrarai
	 */

	@Test(enabled = true, groups = "FirstTimeVisitor")
	public void test_Init_dayWinnings() {
		Pob_betContainer pobB = new Pob_betContainer(driver);
		Assert.assertEquals(pobB.getDayWinnings(), 0);
	}

	/**
	 * TestCaseID: SM_07 :Verify lifetimeWinnings value is "0" in betContainer.
	 * 
	 * @author shrarai
	 */

	@Test(enabled = true, groups = "FirstTimeVisitor")
	public void test_Init_LifetimeWinnings() {
		Pob_betContainer pobB = new Pob_betContainer(driver);
		Assert.assertEquals(pobB.getLifetimeWinnings(), 0);
	}

	/**
	 * TestCaseID: SM_24: Verify dayWinnings=dayWinnings+LastWin.
	 * 
	 * @author shrarai
	 */

	@Test(enabled = true)
	public void test_dayWinnings() {
		Pob_betContainer pobB = new Pob_betContainer(driver);
		int initDayWinnings = pobB.getDayWinnings();
		pobB.click_spinButton();
		if (pobB.getDayWinnings() == 0) {
			Assert.assertEquals(pobB.getDayWinnings(), 0);
		}
		Assert.assertEquals(pobB.getDayWinnings(), initDayWinnings + pobB.getLastWin());
	}

	/**
	 * TestCaseID: SM_25: Verify lifetimeWinnings =lifetimeWinnings +LastWin.
	 * 
	 * @author shrarai
	 */

	@Test(enabled = true)
	public void test_lifetimeWinnings() {
		Pob_betContainer pobB = new Pob_betContainer(driver);
		int initLifetimeWinnings = pobB.getLifetimeWinnings();
		pobB.click_spinButton();
		if (pobB.getLifetimeWinnings() == 0) {
			Assert.assertEquals(pobB.getLifetimeWinnings(), 0);
		}
		Assert.assertEquals(initLifetimeWinnings + pobB.getLastWin(), pobB.getLifetimeWinnings());
	}

	/**
	 * TestCaseID: SM_23:Verify lifetimeWinnings greater or equal to dayWinnings.
	 * 
	 * @author shrarai
	 */

	@Test(enabled = true)
	public void test_lifetimeWinningsVsDayWinnings() {
		Pob_betContainer pobB = new Pob_betContainer(driver);
		if (pobB.getLifetimeWinnings() >= pobB.getDayWinnings())
			Assert.assertTrue(true);
		else
			Assert.assertTrue(false);
	}


	/**
	 * TestCaseID: SM_09:Verify "Try Me" point to Spin Button.
	 * 
	 * @author shrarai
	 */

	@Test(enabled = true, priority = -1,groups="smoke")
	public void test_Init_betTryMe() {
		Pob_betContainer pobB = new Pob_betContainer(driver);

		try {
			pobB.tryMe.isDisplayed();
			Assert.assertTrue(true, "TryMe button is  availalbe beside spin button");
		} catch (NoSuchElementException e) {
			Assert.assertTrue(false, "TryMe button is Not availalbe beside spin button");
		}

	}

	/**
	 * TestCaseID: SM_10:Verify bet increase by one value
	 * 
	 * @author shrarai
	 */

	@Test(enabled = true)
	public void test_betSpinUp_bet() {
		Pob_betContainer pobB = new Pob_betContainer(driver);
		int initialBetValue = pobB.getBet();
		pobB.click_SpinUp();
		if (initialBetValue == 10) {
			Assert.assertEquals(pobB.getBet(), initialBetValue);
		} else
			Assert.assertEquals(pobB.getBet(), initialBetValue + 1);
	}

	/**
	 * TestCaseID: SM_11:Verify Values in Winchart get multiplied by bet value.
	 * 
	 * @author shrarai
	 */

	@Test(enabled = true,groups="smoke")
	public void test_betSpinUp_winChartValues() {
		Pob_betContainer pobB = new Pob_betContainer(driver);
		Pob_WinChart pobW = new Pob_WinChart(driver);
		pobB.click_SpinUp();
		int size = pobW.trPrizetdLayoutList.size();

		for (int i = 0; i < size; i++) {
			pobW.isMultiplicationCorrect(
					Integer.valueOf(pobW.trPrizetdLayoutList.get(0).getAttribute("data-basepayout")), pobB.getBet(),
					Integer.valueOf(pobW.trPrizetdLayoutList.get(0).getText()));
		}

	}

	/**
	 * TestCaseID: SM_12:Verify customer is allowed MAX 10 bet values.
	 * 
	 * @author shrarai
	 */

	@Test(enabled = true)
	public void test_betSpinUp_maxBetAllowed() {
		Pob_betContainer pobB = new Pob_betContainer(driver);
		for (int i = 0; i < 11; i++) {
			pobB.click_SpinUp();
		}

		Assert.assertEquals(pobB.getBet(), 10);
	}

	/**
	 * TestCaseID: SM_13:Verify bet decrease by one value
	 * 
	 * @author shrarai
	 */

	@Test(enabled = true)
	public void test_betSpinDown_bet() {
		Pob_betContainer pobB = new Pob_betContainer(driver);
		int initBet = pobB.getBet();
		log.info("This is initial bet value: " + initBet);
		System.out.println("This is initial bet value: " + initBet);
		pobB.click_SpinDown();
		if (initBet == 1) {
			Assert.assertEquals(pobB.getBet(), 1);
		} else
			Assert.assertEquals(pobB.getBet(), initBet - 1);
	}

	/**
	 * TestCaseID:SM_14 :Verify customer is NOT allowed less 1 bet values.
	 * 
	 * @author shrarai
	 */

	@Test(enabled = true)
	public void test_betSpinDown_minBet() {
		Pob_betContainer pobB = new Pob_betContainer(driver);
		for (int i = 0; i < 11; i++) {
			pobB.click_SpinDown();
		}

		Assert.assertEquals(pobB.getBet(), 1);
	}

	/**
	 * TestCaseID: SM_15:Spin button should get disabled immediately.
	 * 
	 * @throws InterruptedException
	 */
	@Test(enabled = true)
	public void ClickAndGetSpinButtonClass() throws InterruptedException {
		Pob_betContainer pobB = new Pob_betContainer(driver);
		pobB.click_spinButton();
		Assert.assertEquals(pobB.spinButton.getAttribute("class"), "disabled");
		Thread.sleep(8000);
		// Ensure button gets Enabled back.
		if (pobB.getCredits() >= 1) {
			Assert.assertEquals(pobB.spinButton.getAttribute("class"), "");
		}

	}

	/**
	 * TestCaseID: SM_16:when credit limit exhausts and customer presses the Spin
	 * button. Verify "You don't have enough credits for this bet" message is
	 * displayed
	 * 
	 * @author shrarai Check the alert box message. and then accept.
	 * 
	 *         addition validation : TotalSpin =-1, spin button is disabled, last
	 *         win=blank
	 */

	/**
	 * TestCaseID: SM_17:Verify Credits=InitCredits-bet+0 TestCaseID: SM_19:Verify
	 * Credits=InitCredits-bet+LastWin
	 * 
	 * @author shrarai
	 */

	@Test(enabled = true)
	public void test_PostSpin_Credits() {
		Pob_betContainer pobB = new Pob_betContainer(driver);
		int initCredit = pobB.getCredits();
		pobB.click_spinButton();
		Assert.assertEquals(pobB.getCredits(), initCredit - pobB.getBet() + pobB.getLastWin());

	}

	/**
	 * TestCaseID: SM_18:Verify NO combination is highlighted on WinChart if
	 * unsuccessful TestCaseID: SM_21:Verify Only one combination is highlighted on
	 * WinChart if unsuccessful, Won should be present.
	 * 
	 * @author shrarai
	 */

	@Test(enabled = true)
	public void test_PostSpin_winChartWon() {
		Pob_WinChart pobW = new Pob_WinChart(driver);
		if (pobW.isSuccessfulSpin()) {
			Assert.assertTrue(true, "WinChart Combination is highlighted in Successful Spin");
		}
		Assert.assertTrue(true, "WinChart Combination is Not highlighted in Unsuccessfull spin");

	}

	/**
	 * TestCaseID: SM_20:Verify LastWin value is same as Highlighted Winchart
	 * Combination Value.
	 * 
	 * @author shrarai
	 * @throws InterruptedException
	 */

	@Test(enabled = true)
	public void test_successfulSpin_winchartVsLastWin() throws InterruptedException {
		Pob_betContainer pobB = new Pob_betContainer(driver);
		Pob_WinChart pobW = new Pob_WinChart(driver);
		trytoWin(pobB, pobW);
		explicitWait(pobB.lastWin, 2000l);
		Assert.assertEquals(String.valueOf(pobB.getLastWin()), pobW.winChartWon.getText());
	}

	/**
	 * @param pobB
	 * @param pobW
	 * @throws InterruptedException
	 */
	private void trytoWin(Pob_betContainer pobB, Pob_WinChart pobW) throws InterruptedException {
		pobB.click_spinButton();
		int counter = 0;
		while (!pobW.isSuccessfulSpin()) {
			if (pobB.getCredits() > 1) {
				pobB.click_spinButton();
				counter++;
				if (counter > 10) {
					explicitWait(pobB.lastWin, 5000l);
					Assert.assertTrue(false, "Unable to create Successful condition");
				}
			}

		}
		log.info("Successful condition is available");
	}

	/**
	 * TestCaseID: SM_22:Verify Winchart and machine reel1, reel2,reel3 icons match.
	 * one of the many permutations to win.
	 * 
	 * @author shrarai
	 * @throws InterruptedException
	 */

	@Test(enabled = true)
	public void test_successfulSpin_winChartVsreel() throws InterruptedException {
		Pob_WinChart pobW = new Pob_WinChart(driver);
		Pob_betContainer pobB = new Pob_betContainer(driver);
		trytoWin(pobB, pobW);
		Assert.assertTrue(pobW.isAllStylesMatch(), "This is definitely a successSpin");
		Assert.assertTrue(pobW.isSuccessfulSpin());
	}

}
