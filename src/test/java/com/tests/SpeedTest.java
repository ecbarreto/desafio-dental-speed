package com.tests;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.pages.PingdomPage;


public class SpeedTest {
	public WebDriver driver;
	public PingdomPage pingdomPage;
	
	public static void main(String args[]) {
		  JUnitCore junit = new JUnitCore();
		  junit.addListener(new TextListener(System.out));
		  Result result = junit.run(SpeedTest.class); // Replace "SampleTest" with the name of your class
		  if (result.getFailureCount() > 0) {
		    System.out.println("Test failed.");
		    System.exit(1);
		  } else {
		    System.out.println("Test finished successfully.");
		    System.exit(0);
		  }
		}

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		//driver.quit();
	}

	@Test
	public void validateDentalSpeedPerf() {
		pingdomPage = new PingdomPage(driver);
		pingdomPage.openPage()
			.typeTestURL("www.dentalspeed.com")
			.selectTestLocation()
			.clickStartTest()
			.waitResults();
		assertTrue(pingdomPage.validateSummary());
		assertTrue(pingdomPage.validateResponseCodes());
	}

}
