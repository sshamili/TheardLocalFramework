package com.seleniummaventestng.lib;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;

public class WebDriverFactory {

	private static ThreadLocal<WebDriver> driverForThread = new ThreadLocal<WebDriver>();
	private static ChromeDriverService chromeDriverService;

	private static String chromeDriverPath = "C:\\Users\\Shamili\\Downloads\\SeleniumMavenTestNG_Parallel\\SeleniumMavenTestNG\\drivers\\chromedriver.exe";

	public static WebDriver getDriverForThread() {
		return driverForThread.get();
	}

	public static void setDriverForThread() throws Exception {
		driverForThread.set(getWebdriver());
	}

	private static synchronized WebDriver getWebdriver() throws MalformedURLException {

		WebDriver webdriver = null;

		Log.info("Initializing Chrome driver");

		System.setProperty("webdriver.chrome.driver", chromeDriverPath);

		ChromeOptions cOptions = new ChromeOptions();
		cOptions.setCapability(CapabilityType.PLATFORM_NAME, Platform.WINDOWS);
		cOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		cOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cOptions.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
		cOptions.addArguments("--disable-infobars");
		cOptions.addArguments("--disable-extensions");
		cOptions.addArguments("--disable-notifications");
		cOptions.addArguments("--disable-session-crashed-bubble");
		cOptions.addArguments("--enable-automation");
		cOptions.addArguments("--disable-save-password-bubble");

		Map<String, Object> prefs = new HashMap<String, Object>();
		prefs.put("credentials_enable_service", false);
		prefs.put("profile.password_manager_enabled", false);

		cOptions.setExperimentalOption("prefs", prefs);

		webdriver = new ChromeDriver(chromeDriverService, cOptions);

		return webdriver;
	}

	public static void startChromeService() throws Exception {

		if (null == chromeDriverService) {
			Log.info("Initializing chrome driver service");
			chromeDriverService = new ChromeDriverService.Builder().usingDriverExecutable(new File(chromeDriverPath))
					.usingAnyFreePort().build();
		}

		if (!chromeDriverService.isRunning()) {
			Log.info("Starting chrome driver service");
			chromeDriverService.start();
			Log.info("Chrome driver service started");
		}
	}

	public static void stopChromeService() throws IOException {

		if (null != chromeDriverService && chromeDriverService.isRunning()) {
			Log.info("Stopping chrome driver service");
			chromeDriverService.stop();
		}
	}
}
