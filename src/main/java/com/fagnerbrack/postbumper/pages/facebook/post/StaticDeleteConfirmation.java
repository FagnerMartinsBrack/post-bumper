package com.fagnerbrack.postbumper.pages.facebook.post;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.fagnerbrack.postbumper.pages.StaticPageObject;

public class StaticDeleteConfirmation extends StaticPageObject {
	private WebDriver driver;
	
	private StaticDeleteConfirmation( WebDriver driver ) {
		this.driver = driver;
	}
	
	public static StaticDeleteConfirmation create( WebDriver driver ) {
		return new StaticDeleteConfirmation( driver );
	}
	
	public void confirm() {
		List<WebElement> confirmButtons = driver
			.findElements( By.cssSelector( "button.layerConfirm" ) );
		for ( WebElement confirmButton : confirmButtons ) {
			if ( !confirmButton.isDisplayed() ) {
				continue;
			}
			confirmButton.click();
			break;
		}
	}
}
