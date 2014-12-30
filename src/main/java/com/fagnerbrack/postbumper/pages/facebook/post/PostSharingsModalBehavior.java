package com.fagnerbrack.postbumper.pages.facebook.post;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.fagnerbrack.postbumper.pages.InfiniteScrolling;
import com.fagnerbrack.postbumper.pages.PageObjectBehavior;
import com.google.common.base.Predicate;

public class PostSharingsModalBehavior extends PageObjectBehavior {
	private WebDriver driver;
	private By scrollPlaceholderContents = By.cssSelector( "#pagelet_scrolling_pager > *" );
	
	public PostSharingsModalBehavior( WebDriver driver ) {
		super( driver );
		this.driver = driver;
	}
	
	@Override
	public boolean isCorrectPage() {
		return driver.findElements( scrollPlaceholderContents ).size() == 0;
	}
	
	@Override
	public void doNavigate() {
		new InfiniteScrolling( driver ).until(new Predicate<WebDriver>() {
			@Override
			public boolean apply( WebDriver input ) {
				return driver.findElements( scrollPlaceholderContents ).size() == 0;
			}
		});
	}
}
