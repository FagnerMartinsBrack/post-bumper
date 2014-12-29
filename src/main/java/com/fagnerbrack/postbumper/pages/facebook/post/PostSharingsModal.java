package com.fagnerbrack.postbumper.pages.facebook.post;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fagnerbrack.postbumper.configs.facebook.BumpingStrategy;
import com.fagnerbrack.postbumper.pages.ChainingException;
import com.fagnerbrack.postbumper.pages.DynamicPageObject;
import com.fagnerbrack.postbumper.pages.Navigate;
import com.fagnerbrack.postbumper.pages.PageObjectBehavior;
import com.fagnerbrack.postbumper.pages.WrongPageException;
import com.google.common.base.Function;

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
	
	public void bumpWith( BumpingStrategy strategy ) throws ChainingException {
		if ( strategy == BumpingStrategy.SHARED_BY_ME ) {
			List<WebElement> sharesByMe = new ArrayList<WebElement>();
			
			List<WebElement> postSharings =
				driver.findElements( By.className( "userContentWrapper" ) );
			
			for ( WebElement element : postSharings ) {
				WebElement author = element
					.findElement( By.cssSelector( ".profileLink[data-hovercard]" ) );
				if ( "Fagner Brack".equals( author.getText() ) ) {
					sharesByMe.add( element );
				}
			}
			
			for ( final WebElement element : sharesByMe ) {
				// Enables the content editable element
				WebElement contentEditableTrigger = element
					.findElement( By.className( "UFIAddCommentInput" ) );
				contentEditableTrigger.click();
				
				// Add the comment
				WebElement commentBox = element
					.findElement( By.cssSelector( "[contenteditable]" ) );
				commentBox.sendKeys( "bump" );
				commentBox.sendKeys( Keys.ENTER );
				
				// Open the context menu of the last comment
				WebElement lastComment = new WebDriverWait( driver, 10, 2000 )
				.until(new Function<WebDriver, WebElement>() {
					@Override
					public WebElement apply( WebDriver input ) {
						return element.findElement( By.className( "UFILastCommentComponent" ) );
					}
				});
				WebElement editButton = lastComment
					.findElement( By.className( "UFICommentCloseButton" ) );
				JavascriptExecutor js = ( JavascriptExecutor )driver;
				js.executeScript( "arguments[0].click();", editButton );
				
				// Click to remove the comment using the popup buttons
				WebElement popover = null;
				List<WebElement> popovers = driver
					.findElements( By.className( "uiContextualLayerBelowRight" ) );
				for ( WebElement current : popovers ) {
					if( current.isDisplayed() ) {
						popover = current;
						break;
					}
				}
				if ( popover == null ) {
					throw new ChainingException(
						"There is no visible popover to delete the comment!"
					);
				}
				List<WebElement> menuLinks = popover
					.findElements( By.cssSelector( "a[role='menuitem']" ) );
				WebElement deleteLink = menuLinks.get( menuLinks.size() - 1 );
				deleteLink.click();
				
				// Confirm comment exclusion
				List<WebElement> elements = driver.findElements(
					By.cssSelector( ".layerConfirm.selected" )
				);
				for ( WebElement current : elements ) {
					if( current.isDisplayed() ) {
						current.click();
					}
				}
			}
		}
	}
}
