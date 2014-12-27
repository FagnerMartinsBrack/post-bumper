package com.fagnerbrack.postbumper.pages;

import org.openqa.selenium.WebDriver;

/**
 * Associate consistent behavior between each Page Object.
 */
public abstract class PageObjectBehavior implements DriverDecorable {
	private WebDriver driver;
	
	public PageObjectBehavior( WebDriver driver ) {
		this.driver = driver;
	}
	
	/**
	 * Determine if the current state of the driver represents the correct Page Object in which this
	 * behavior is associated.
	 */
	public abstract boolean isCorrectPage();
	
	/**
	 * Navigate to the resource representing the Page Object in which this behavior is associated. 
	 */
	public abstract void navigate();
	
	public WebDriver driver() {
		return driver;
	}
}
