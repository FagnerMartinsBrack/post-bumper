package com.fagnerbrack.postbumper.pages.facebook.feed;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.fagnerbrack.postbumper.pages.DynamicPageObject;
import com.fagnerbrack.postbumper.pages.PageObjectBehavior;
import com.fagnerbrack.postbumper.pages.WrongPageException;

public class FacebookFeed extends DynamicPageObject {
	private FacebookFeed( PageObjectBehavior pageBehavior ) throws WrongPageException {
		super( pageBehavior );
	}
	
	public static FacebookFeed createFrom( WebDriver driver )
	throws WrongPageException {
		FacebookFeedBehavior feedBehavior = new FacebookFeedBehavior( driver );
		FacebookFeed feed = new FacebookFeed( feedBehavior );
		PageFactory.initElements( driver, feed );
		return feed;
	}
	
	public PagesNav inPagesNavigation() {
		return PagesNav.createFrom( driver );
	}
}
