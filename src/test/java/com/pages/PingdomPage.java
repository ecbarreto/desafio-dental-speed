package com.pages;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PingdomPage {
	private WebDriver driver;
	private By testURL = By.id("urlinput");
	private By selectLocation = By.xpath(".//*[@id='urlform']/div[2]/div/div");
	private By citySanJose = By.xpath(".//*[@id='urlform']/div[2]/div/ul/li[3]");
	private By btnStartTest = By.cssSelector(".button.button-big.button-green.button-starttest");	
	private By btnDownloadHar = By.xpath(".//*[@id='controlwrapper']/div[2]/a[1]");
	private By txtPerfGrade = By.xpath(".//*[@id='content']/div[1]/div[3]/div[1]/div[3]");
	private By txtLoadTime = By.xpath(".//*[@id='content']/div[1]/div[3]/div[2]/div[2]");
	private By txtRequests = By.xpath(".//*[@id='content']/div[1]/div[3]/div[5]/div[2]");
	private By txtRespOk = By.xpath(".//*[@id='content']/div[3]/table/tbody/tr[1]/td[2]");
	

	public PingdomPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public PingdomPage openPage() {
		driver.get("https://tools.pingdom.com/");
		return this;
	}
	
	public PingdomPage typeTestURL(String URL) {
		driver.findElement(testURL).sendKeys(URL);
		return this;
	}
	
	public PingdomPage selectTestLocation() {
		driver.findElement(selectLocation).click();
		driver.findElement(citySanJose).click();
		return this;
	}
	
	public PingdomPage clickStartTest() {
		driver.findElement(btnStartTest).click();	
		return this;
	}
	
	public PingdomPage waitResults() {
		WebDriverWait wait = new WebDriverWait(driver, 300);
		WebElement btnDownload = driver.findElement(btnDownloadHar);
		wait.until(ExpectedConditions.visibilityOfElementLocated(btnDownloadHar));
		assertTrue(btnDownload.isDisplayed());
		return this;
	}
	
	public Boolean validateSummary() {
		String string = driver.findElement(txtPerfGrade).getText();
		String[] parts = string.split(" ");
		Integer perfGrade = Integer.parseInt(parts[1]);
		
		string = null;
		parts = null;
		
		string = driver.findElement(txtLoadTime).getText();
		parts = string.split(" ");
		Float loadTime = Float.valueOf(parts[0]);
		
		Integer requests = Integer.parseInt(driver.findElement(txtRequests).getText());
		if(perfGrade < 80 && loadTime < 8.00 && requests > 100) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Boolean validateResponseCodes() {
		Integer qtdOks = Integer.parseInt(driver.findElement(txtRespOk).getText());
		if(qtdOks > 100)
			return true;
		else
			return false;
	}
}
