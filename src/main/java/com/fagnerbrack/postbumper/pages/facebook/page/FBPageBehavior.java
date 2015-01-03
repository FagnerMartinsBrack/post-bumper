package com.fagnerbrack.postbumper.pages.facebook.page;

import org.openqa.selenium.WebDriver;

import com.fagnerbrack.postbumper.configs.facebook.FBPageConfig;
import com.fagnerbrack.postbumper.pages.PageObjectBehavior;

public class FBPageBehavior extends PageObjectBehavior {
	private WebDriver driver;
	private FBPageConfig pageData;
	
	public FBPageBehavior( WebDriver driver, FBPageConfig pageData ) {
		super( driver );
		this.driver = driver;
		this.pageData = pageData;
	}
	
	@Override
	public boolean isCorrectPage() {
		return driver.getTitle().contains( pageData.getPageName() );
	}
	
	@Override
	public void doNavigate() {
		driver.navigate().to( "https://www.facebook.com/" + pageData.getPageURL() );
	}
}
