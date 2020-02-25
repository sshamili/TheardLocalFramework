package com.seleniummaventestng.tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.seleniummaventestng.base.BaseTest;
import com.seleniummaventestng.lib.Log;
import com.seleniummaventestng.lib.WebDriverFactory;
import com.seleniummaventestng.pageobjects.Google_Page;

public class HelloWorld extends BaseTest {

	@Test(groups = { "selenium.maven.testng.test", "selenium.maven.testng.test.01" })
	public void helloWorldTestCase01() {

		Log.startTestCase("Google Search Test Case");

		WebDriver driver = WebDriverFactory.getDriverForThread();

		Log.info("Launch goole home page");
		Google_Page googlePage = new Google_Page(driver);
		googlePage.get();

		Log.info("Set search text");
		googlePage.enterTextInSearchBox("Hello World - 1");

		Log.info("Click on Search");
		googlePage.clickSearchButton();

		Log.info("Get page title");
		String pageTitle = driver.getTitle();

		Log.info("Compare page title");
		if (pageTitle.contains("Hello World - 1")) {
			Log.info("Verification successful!");
		} else {
			Log.error("Verification failed!");
		}
	}

	@Test(groups = { "selenium.maven.testng.test", "selenium.maven.testng.test.02" })
	public void helloWorldTestCase02() {

		Log.startTestCase("Google Search Test Case - 02");

		WebDriver driver = WebDriverFactory.getDriverForThread();

		Log.info("Launch goole home page");
		Google_Page googlePage = new Google_Page(driver);
		googlePage.get();

		Log.info("Set search text");
		googlePage.enterTextInSearchBox("Hello World - 2");

		Log.info("Click on Search");
		googlePage.clickSearchButton();

		Log.info("Get page title");
		String pageTitle = driver.getTitle();

		Log.info("Compare page title");
		if (pageTitle.contains("Hello World - 2")) {
			Log.info("Verification successful!");
		} else {
			Log.error("Verification failed!");
		}
	}
}
