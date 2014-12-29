package com.fagnerbrack.postbumper.pages.facebook.post;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.fagnerbrack.postbumper.pages.DynamicPageObject;
import com.fagnerbrack.postbumper.pages.Navigate;
import com.fagnerbrack.postbumper.pages.PageObjectBehavior;
import com.fagnerbrack.postbumper.pages.WrongPageException;

public class PostSharingsModal extends DynamicPageObject {
	private WebDriver driver;
	public PostSharingsModal( PageObjectBehavior pageBehavior ) throws WrongPageException {
		super( pageBehavior, Navigate.YES );
		this.driver = pageBehavior.driver();
	}
	
	public static PostSharingsModal createFrom( WebDriver driver )
	throws WrongPageException {
		PostSharingsModalBehavior postSharingsBehavior = new PostSharingsModalBehavior( driver );
		PostSharingsModal sharings = new PostSharingsModal( postSharingsBehavior );
		PageFactory.initElements( driver, sharings );
		return sharings;
	}
	
	public void commentAllWith( String comment ) {
		// TODO comment
		driver.getClass();
	}
}
