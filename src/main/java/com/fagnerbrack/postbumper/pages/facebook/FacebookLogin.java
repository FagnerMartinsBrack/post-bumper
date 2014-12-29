package com.fagnerbrack.postbumper.pages.facebook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.fagnerbrack.postbumper.pages.AuthenticationData;
import com.fagnerbrack.postbumper.pages.ChainingException;
import com.fagnerbrack.postbumper.pages.DynamicPageObject;
import com.fagnerbrack.postbumper.pages.LoginPage;
import com.fagnerbrack.postbumper.pages.Navigate;
import com.fagnerbrack.postbumper.pages.WrongPageException;
import com.fagnerbrack.postbumper.pages.facebook.feed.FacebookFeed;

public class FacebookLogin extends DynamicPageObject implements LoginPage<FacebookFeed> {
	private WebElement email;
	private WebElement pass;
	
	private FacebookLogin( FacebookLoginBehavior loginPageBehavior ) throws WrongPageException {
		super( loginPageBehavior, Navigate.YES );
	}
	
	public static FacebookLogin startUsing( WebDriver driver ) throws WrongPageException {
		FacebookLoginBehavior loginPageBehavior = new FacebookLoginBehavior( driver );
		FacebookLogin page = new FacebookLogin( loginPageBehavior );
		PageFactory.initElements( driver, page );
		return page;
	}
	
	public FacebookFeed loginWith( AuthenticationData auth ) throws ChainingException {
		email.sendKeys( auth.username() );
		
		pass.clear(); // Prevent autocomplete
		pass.sendKeys( auth.password() );
		
		pass.submit();
		
		try {
			return FacebookFeed.createFrom( driver );
		} catch ( WrongPageException e ) {
			if ( driver.findElements( By.cssSelector( "body.login_page" ) ).size() > 0 ) {
				throw new ChainingException( "Login failed, check your login information!" );
			}
			throw new ChainingException( e );
		}
	}
}
