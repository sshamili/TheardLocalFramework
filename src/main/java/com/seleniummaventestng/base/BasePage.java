package com.seleniummaventestng.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.LoadableComponent;

public abstract class BasePage extends LoadableComponent<BasePage> {

	public WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}
}
