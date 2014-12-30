package com.fagnerbrack.postbumper.pages.facebook.feed;

import org.openqa.selenium.WebDriver;

import com.fagnerbrack.postbumper.pages.DynamicPageObject;
import com.fagnerbrack.postbumper.pages.PageObjectBehavior;
import com.fagnerbrack.postbumper.pages.WrongPageException;

public class FacebookFeed extends DynamicPageObject {
	private WebDriver driver;
	
	private FacebookFeed( WebDriver driver, PageObjectBehavior pageBehavior )
	throws WrongPageException {
		super( pageBehavior );
		this.driver = driver;
	}
	
	public static FacebookFeed create( WebDriver driver )
	throws WrongPageException {
		FacebookFeedBehavior feedBehavior = new FacebookFeedBehavior( driver );
		FacebookFeed feed = new FacebookFeed( driver, feedBehavior );
		return feed;
	}
	
	public PagesNav inPagesNavigation() {
		return PagesNav.createFrom( driver );
	}
}
