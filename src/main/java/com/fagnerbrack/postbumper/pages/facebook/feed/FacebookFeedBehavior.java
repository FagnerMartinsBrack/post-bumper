package com.fagnerbrack.postbumper.pages.facebook.feed;

import org.openqa.selenium.WebDriver;

import com.fagnerbrack.postbumper.pages.PageObjectBehavior;

public class FacebookFeedBehavior extends PageObjectBehavior {
	public FacebookFeedBehavior( WebDriver driver ) {
		super( driver );
	}
	
	@Override
	public boolean isCorrectPage() {
		return driver.getTitle().equals( "Facebook" );
	}
	
	@Override
	public void doNavigate() {
		driver.navigate().to( "https://www.facebook.com/feed" );
	}
}
