package com.fagnerbrack.postbumper.pages;

import java.util.logging.Logger;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

public class InfiniteScrolling extends WebDriverWait {
	private Logger logger = Logger.getGlobal();
	private WebDriver driver;
	
	public InfiniteScrolling( WebDriver driver ) {
		super( driver, 120 );
		this.driver = driver;
	}
	
	@Override
	public void until( final Predicate<WebDriver> isTrue ) {
		final JavascriptExecutor js = ( JavascriptExecutor )driver;
		super.until(new Predicate<WebDriver>() {
			@Override
			public boolean apply( WebDriver input ) {
				if( isTrue.apply( input ) ) {
					logger.info( "All content was loaded." );
					return true;
				}
				logger.info( "Loading more content..." );
				js.executeScript( "window.scrollTo( 0, document.body.scrollHeight );" );
				return false;
			}
		});
	}
}
