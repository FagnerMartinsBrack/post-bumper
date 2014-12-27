package com.fagnerbrack.postbumper.pages.facebook;

import org.openqa.selenium.support.PageFactory;

import com.fagnerbrack.postbumper.pages.PageObject;
import com.fagnerbrack.postbumper.pages.PageObjectBehavior;
import com.fagnerbrack.postbumper.pages.WrongPageException;

public class FacebookFeed extends PageObject {
	private FacebookFeed( PageObjectBehavior pageBehavior ) throws WrongPageException {
		super( pageBehavior );
	}
	
	public static FacebookFeed createFrom( PageObjectBehavior lastPageBehavior )
	throws WrongPageException {
		FacebookFeed feed = new FacebookFeed( lastPageBehavior );
		PageFactory.initElements( lastPageBehavior.driver(), feed );
		return feed;
	}
	
	// TODO Use this class to enable access to Page Components
}
