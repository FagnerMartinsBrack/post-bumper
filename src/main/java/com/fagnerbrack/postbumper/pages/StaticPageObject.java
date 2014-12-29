package com.fagnerbrack.postbumper.pages;

import org.openqa.selenium.WebDriver;

/**
 * Handles custom user behavior within a static component inside a dynamic page component.
 */
public abstract class StaticPageObject {
	protected WebDriver driver;
	public StaticPageObject( WebDriver driver ) {
		this.driver = driver;
	}
}
