package com.fagnerbrack.postbumper.pages;

import org.openqa.selenium.WebDriver;

/**
 * Associate consistent behavior between each Page Object.
 */
public abstract class PageObjectBehavior implements DriverDecorable {
	protected WebDriver driver;
	
	public PageObjectBehavior( WebDriver driver ) {
		this.driver = driver;
	}
	
	/**
	 * Determine if the current state of the driver represents the correct Page Object in which this
	 * behavior is associated.
	 */
	public abstract boolean isCorrectPage();
	
	/**
	 * Optional navigation behavior before checking if this page is correct or not.
	 */
	public abstract void doNavigate();
	
	public WebDriver driver() {
		return driver;
	}
}
