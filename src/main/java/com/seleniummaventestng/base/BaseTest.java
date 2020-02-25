package com.seleniummaventestng.base;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.seleniummaventestng.lib.Log;
import com.seleniummaventestng.lib.WebDriverFactory;

public abstract class BaseTest {

	@BeforeSuite(alwaysRun = true)
	public void beforeSuite(ITestContext context) throws Exception {

	
		WebDriverFactory.startChromeService();
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() throws IOException {

		WebDriverFactory.stopChromeService();
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() throws Exception {

			startWebDriverClient();
	}

	@AfterMethod(alwaysRun = true)
	public void afterMethod() throws Exception {

			WebDriver driver = WebDriverFactory.getDriverForThread();
		driver.close();
	}

	protected void startWebDriverClient() throws Exception {

		WebDriverFactory.setDriverForThread();
		WebDriver driver = WebDriverFactory.getDriverForThread();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.MILLISECONDS);
	}
}