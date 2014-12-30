package com.fagnerbrack.postbumper.pages.facebook.post;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.fagnerbrack.postbumper.configs.facebook.FBPostConfig;
import com.fagnerbrack.postbumper.pages.ChainingException;
import com.fagnerbrack.postbumper.pages.DynamicPageObject;
import com.fagnerbrack.postbumper.pages.PageObjectBehavior;
import com.fagnerbrack.postbumper.pages.WrongPageException;

public class FBPost extends DynamicPageObject {
	private WebDriver driver;
	
	private FBPost( WebDriver driver, PageObjectBehavior pageBehavior ) throws WrongPageException {
		super( pageBehavior );
		this.driver = driver;
	}
	
	public static FBPost startWith( WebDriver driver, FBPostConfig postData )
	throws WrongPageException {
		driver.navigate().to( "https://www.facebook.com/permalink.php"
			+ "?story_fbid=720471394727826&id=720470948061204" );
		FBPostBehavior pageBehavior = new FBPostBehavior( driver, postData );
		return new FBPost( driver, pageBehavior );
	}
	
	public static FBPost create( WebDriver driver, FBPostConfig postData )
	throws WrongPageException {
		FBPostBehavior pageBehavior = new FBPostBehavior( driver, postData );
		return new FBPost( driver, pageBehavior );
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
	
	public StaticPost inPost() {
		return StaticPost.createPermalinkPost( driver );
	}
}
