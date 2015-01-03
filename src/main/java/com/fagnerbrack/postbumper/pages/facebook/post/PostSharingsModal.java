package com.fagnerbrack.postbumper.pages.facebook.post;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fagnerbrack.postbumper.configs.facebook.FBMeConfigs;
import com.fagnerbrack.postbumper.pages.ChainingException;
import com.fagnerbrack.postbumper.pages.InfiniteScrolling;
import com.fagnerbrack.postbumper.pages.StaticPageObject;
import com.fagnerbrack.postbumper.pages.WrongPageException;
import com.google.common.base.Function;
import com.google.common.base.Predicate;

public class PostSharingsModal extends StaticPageObject {
	private WebDriver driver;
	private List<WebElement> allSharings;
	
	private PostSharingsModal( WebDriver driver, List<WebElement> allSharings ) throws WrongPageException {
		this.driver = driver;
		this.allSharings = allSharings;
	}
	
	public static PostSharingsModal createFrom( WebDriver driver )
	throws WrongPageException {
		final WebElement dialog = new WebDriverWait( driver, 30 )
		.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply( WebDriver driver ) {
				WebElement dialog = null;
				List<WebElement> dialogs = driver
					.findElements( By.cssSelector( "[role='dialog']" ) );
				for ( WebElement current : dialogs ) {
					if ( current.isDisplayed() ) {
						dialog = current;
						break;
					}
				}
				
				// Dialog is not loaded yet
				if ( dialog == null ) {
					return null;
				}
				
				List<WebElement> allSharings = dialog
					.findElements( By.cssSelector( ".userContentWrapper" ) );
				
				// Wait for at least one shared post to show up.
				// Everything is ajax based, so a situation may happen where the dialog is
				// loaded but the posts are not loaded yet. We know that at least one shared post
				// should exist anyway (otherwise why using the tool?)
				if ( allSharings.size() == 0 ) {
					return null;
				}
				
				return dialog;
			}
		});
		
		// Scroll to the bottom if there is more posts to show up
		if ( hasMorePostsIn( dialog ) ) {
			new InfiniteScrolling( driver ).until(new Predicate<WebDriver>() {
				@Override
				public boolean apply( WebDriver driver ) {
					return !hasMorePostsIn( dialog );
				}
			});
		}
		
		List<WebElement> allSharings = dialog
			.findElements( By.cssSelector( ".userContentWrapper" ) );
		
		return new PostSharingsModal( driver, allSharings );
	}
	
	public PostSharings inSharingsByMe( FBMeConfigs me ) throws ChainingException {
		try {
			return PostSharings.createSharingsByMe( driver, me, allSharings );
		} catch ( WrongPageException e ) {
			throw new ChainingException( e );
		}
	}
	
	/**
	 * Check whether there is more shared posts inside the given dialog
	 */
	private static boolean hasMorePostsIn( WebElement dialog ) {
		return dialog.findElements( By.cssSelector( "#pagelet_scrolling_pager > *" ) ).size() > 0;
	}
}
