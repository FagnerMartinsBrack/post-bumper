package com.fagnerbrack.postbumper.pages.facebook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.fagnerbrack.postbumper.pages.PageObjectBehavior;

public class FacebookLoginBehavior extends PageObjectBehavior {
	public FacebookLoginBehavior( WebDriver driver ) {
		super( driver );
	}
	
	@Override
	public boolean isCorrectPage() {
		return driver.findElements( By.cssSelector( "form#reg" ) ).size() == 1;
	}
	
	@Override
	public void doNavigate() {
		driver.navigate().to( "http://facebook.com" );
	}
}
