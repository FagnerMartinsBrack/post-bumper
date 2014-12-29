package com.fagnerbrack.postbumper.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;
import com.sun.istack.internal.logging.Logger;

public class InfiniteScrolling extends WebDriverWait {
	private Logger logger = Logger.getLogger( this.getClass() );
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
