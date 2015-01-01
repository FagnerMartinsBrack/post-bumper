package com.fagnerbrack.postbumper.pages.facebook.post;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.fagnerbrack.postbumper.configs.facebook.FBPostConfig;
import com.fagnerbrack.postbumper.pages.ChainingException;
import com.fagnerbrack.postbumper.pages.DynamicPageObject;
import com.fagnerbrack.postbumper.pages.Navigate;
import com.fagnerbrack.postbumper.pages.PageObjectBehavior;
import com.fagnerbrack.postbumper.pages.WrongPageException;

public class FBPost extends DynamicPageObject {
	private WebDriver driver;
	
	private FBPost( WebDriver driver, PageObjectBehavior pageBehavior, boolean navigate )
	throws WrongPageException {
		super( pageBehavior, navigate );
		this.driver = driver;
	}
	
	public static FBPost startUsing( WebDriver driver, FBPostConfig postData )
	throws WrongPageException {
		FBPostBehavior pageBehavior = new FBPostBehavior( driver, postData );
		return new FBPost( driver, pageBehavior, Navigate.YES );
	}
	
	public static FBPost create( WebDriver driver, FBPostConfig postData )
	throws WrongPageException {
		FBPostBehavior pageBehavior = new FBPostBehavior( driver, postData );
		return new FBPost( driver, pageBehavior, Navigate.NO );
	}
	
	public PostSharingsModal goToPostSharings() throws ChainingException {
		WebElement sharingsLink = driver.findElement( By.className( "UFIShareLink" ) );
		sharingsLink.click();
		
		try {
			return PostSharingsModal.createFrom( driver );
		} catch ( WrongPageException e ) {
			throw new ChainingException( e );
		}
	}
	
	public StaticPost inPostContent() {
		return StaticPost.createPermalinkPost( driver );
	}
}
