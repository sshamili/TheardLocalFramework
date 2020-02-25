package com.seleniummaventestng.pageobjects;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.seleniummaventestng.base.BasePage;
import com.seleniummaventestng.lib.Log;

public class Google_Page extends BasePage {

	WebDriver driver;

	@FindBy(how = How.ID, id = "//*[@id='fakebox-input']")
	private WebElement searchBoxElement;

	@FindBy(how = How.CSS, css = "center:nth-child(1) input[name='btnK']")
	private WebElement searchButton;

	public Google_Page(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@Override
	protected void isLoaded() throws Error {
		try {
			Log.info("Wait for page to load");
			Thread.sleep(10000);

			Log.info("Check if page is loaded.");
			if (!searchBoxElement.isDisplayed()) {
				throw new Error("Page load error : Google home page not loaded");
			}
		} catch (Exception e) {
			throw new Error("Page load Exception : " + e.getMessage(), e);
		}
	}

	@Override
	protected void load() {
		Log.info("Open browser and hit google URL");
		driver.get("https://www.google.com/");
	}

	public void clickSearchButton() {

		Log.info("Click on search button");
		//searchButton.click();
		searchBoxElement.sendKeys(Keys.ENTER);
	}

	public void enterTextInSearchBox(String text) {

		Log.info("Enter search text as 'Hello World!'");
		searchBoxElement.sendKeys(text);
	}
}
