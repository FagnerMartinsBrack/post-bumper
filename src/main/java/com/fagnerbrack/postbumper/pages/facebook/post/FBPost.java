package com.fagnerbrack.postbumper.pages.facebook.post;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.fagnerbrack.postbumper.configs.facebook.FBPostConfig;
import com.fagnerbrack.postbumper.pages.ChainingException;
import com.fagnerbrack.postbumper.pages.DynamicPageObject;
import com.fagnerbrack.postbumper.pages.PageObjectBehavior;
import com.fagnerbrack.postbumper.pages.WrongPageException;

public class FBPost extends DynamicPageObject {
	private PageObjectBehavior pageBehavior;
	
	private FBPost( PageObjectBehavior pageBehavior ) throws WrongPageException {
		super( pageBehavior );
		this.pageBehavior = pageBehavior;
	}
	
	public static FBPost createFrom( WebDriver driver, FBPostConfig postData )
	throws WrongPageException {
		FBPostBehavior pageBehavior = new FBPostBehavior( driver, postData );
		FBPost product = new FBPost( pageBehavior );
		PageFactory.initElements( driver, product );
		return product;
	}
	
	public PostSharingsModal goToPostSharings() throws ChainingException {
		WebDriver driver = pageBehavior.driver();
		
		WebElement sharingsLink = driver.findElement( By.className( "UFIShareLink" ) );
		sharingsLink.click();
		
		try {
			return PostSharingsModal.createFrom( pageBehavior.driver() );
		} catch ( WrongPageException e ) {
			throw new ChainingException( e );
		}
	}
}
