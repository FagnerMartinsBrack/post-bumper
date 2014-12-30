package com.fagnerbrack.postbumper.pages.facebook.post;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fagnerbrack.postbumper.pages.ChainingException;
import com.fagnerbrack.postbumper.pages.StaticPageObject;
import com.fagnerbrack.postbumper.pages.WrongPageException;
import com.google.common.base.Function;

public class StaticComment extends StaticPageObject {
	private WebElement postElement;
	private WebDriver driver;
	private String commentMessage;
	
	private StaticComment( WebDriver driver, WebElement postElement, String commentMessage )
	throws WrongPageException {
		this.postElement = postElement;
		this.driver = driver;
		this.commentMessage = commentMessage;
	}
	
	public static StaticComment createAfterComment(
		WebDriver driver,
		final WebElement postElement,
		final String commentMessage
	) throws WrongPageException {
		return new StaticComment( driver, postElement, commentMessage );
	}
	
	public StaticDeleteConfirmation delete() throws ChainingException {
		try {
			Thread.sleep( 2000 );
		} catch ( InterruptedException e ) {
			throw new ChainingException( e );
		}
		
		WebElement editButton = new WebDriverWait( driver, 10 )
			.until(new Function<WebDriver, WebElement>() {
				@Override
				public WebElement apply( WebDriver input ) {
					List<WebElement> comments = postElement
						.findElements( By.className( "UFICommentContentBlock" ) );
					for ( WebElement comment : comments ) {
						if ( comment.getText().contains( commentMessage ) ) {
							List<WebElement> editButton = comment
								.findElements( By.className( "UFICommentCloseButton" ) );
							if ( editButton.size() == 1 ) {
								return editButton.get( 0 );
							}
						}
					}
					return null;
				}
			});
		
		// The edit button stays hidden unless a "mouse over" in the comment occurs, so we use
		// Javascript to do the clicking instead.
		JavascriptExecutor js = ( JavascriptExecutor )driver;
		js.executeScript( "arguments[0].click();", editButton );
		
		// Get the first visible popover (Facebook does not position the popover as a child of the
		// comment element, so we get the first visible one after click)
		WebElement visiblePopover = new WebDriverWait( driver, 10, 1000 )
			.until(new Function<WebDriver, WebElement>() {
				@Override
				public WebElement apply( WebDriver input ) {
					List<WebElement> existentPopovers = input
						.findElements( By.className( "uiContextualLayerBelowRight" ) );
					for ( WebElement current : existentPopovers ) {
						if( current.isDisplayed() ) {
							return current;
						}
					}
					return null;
				}
			});
		List<WebElement> menuLinks = visiblePopover
			.findElements( By.cssSelector( "a[role='menuitem']" ) );
		WebElement deleteLink = menuLinks.get( menuLinks.size() - 1 );
		deleteLink.click();
		return StaticDeleteConfirmation.create( driver );
	}
}
