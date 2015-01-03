package com.fagnerbrack.postbumper.pages.facebook.post;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.fagnerbrack.postbumper.pages.ChainingException;
import com.fagnerbrack.postbumper.pages.StaticPageObject;
import com.fagnerbrack.postbumper.pages.WrongPageException;

public class StaticPost extends StaticPageObject {
	private WebElement postElement;
	private WebDriver driver;
	
	private StaticPost( WebDriver driver, WebElement postElement ) {
		this.postElement = postElement;
		this.driver = driver;
	}
	
	public static StaticPost createPermalinkPost( WebDriver driver ) {
		WebElement postContainer = driver.findElement( By.id( "contentArea" ) );
		return new StaticPost( driver, postContainer );
	}
	
	public static StaticPost createSharingPost( WebDriver driver, WebElement sharedPost ) {
		return new StaticPost( driver, sharedPost );
	}
	
	public StaticComment comment( String message ) throws ChainingException {
		// Enables the content editable element to allow typing the comment
		WebElement contentEditableTrigger = postElement
			.findElement( By.className( "UFIAddCommentInput" ) );
		
		// Selenium click causes an ElementNotVisibleException, probably due to the fact the
		// element is outside the visible area of the scroll 
		JavascriptExecutor js = ( JavascriptExecutor )driver;
		js.executeScript( "arguments[0].click();", contentEditableTrigger );
		
		// Add the comment
		WebElement commentBox = postElement.findElement( By.cssSelector( "[contenteditable]" ) );
		commentBox.sendKeys( message );
		commentBox.sendKeys( Keys.ENTER );
		
		try {
			return StaticComment.createAfterComment( driver, postElement, message );
		} catch ( WrongPageException e ) {
			throw new ChainingException( e );
		}
	}
}
