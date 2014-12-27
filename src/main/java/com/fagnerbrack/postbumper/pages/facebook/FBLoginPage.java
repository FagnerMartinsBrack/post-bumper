package com.fagnerbrack.postbumper.pages.facebook;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.fagnerbrack.postbumper.pages.ChainingException;
import com.fagnerbrack.postbumper.pages.LoginPage;
import com.fagnerbrack.postbumper.pages.PageObject;
import com.fagnerbrack.postbumper.pages.WrongPageException;

public class FBLoginPage extends PageObject implements LoginPage<FacebookFeed> {
	FBLoginPageBehavior loginPageBehavior;
	private WebElement email;
	private WebElement pass;
	
	private FBLoginPage( FBLoginPageBehavior loginPageBehavior ) throws WrongPageException {
		super( loginPageBehavior );
		this.loginPageBehavior = loginPageBehavior;
	}
	
	public static FBLoginPage startUsing( WebDriver driver ) throws WrongPageException {
		FBLoginPageBehavior loginPageBehavior = new FBLoginPageBehavior( driver );
		FBLoginPage page = new FBLoginPage( loginPageBehavior );
		PageFactory.initElements( driver, page );
		return page;
	}
	
	public FacebookFeed loginAs( String username, String password ) throws ChainingException {
		email.sendKeys( username );
		pass.sendKeys( password );
		pass.submit();
		try {
			return FacebookFeed.createFrom( loginPageBehavior );
		} catch ( WrongPageException e ) {
			throw new ChainingException( e );
		}
	}
}
