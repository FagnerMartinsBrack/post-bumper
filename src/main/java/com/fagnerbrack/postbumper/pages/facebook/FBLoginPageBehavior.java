package com.fagnerbrack.postbumper.pages.facebook;

import org.openqa.selenium.WebDriver;

import com.fagnerbrack.postbumper.pages.PageObjectBehavior;

public class FBLoginPageBehavior extends PageObjectBehavior {
	private WebDriver driver;
	
	public FBLoginPageBehavior( WebDriver driver ) {
		super( driver );
		this.driver = driver;
	}
	
	@Override
	public boolean isCorrectPage() {
		return driver.getTitle().startsWith( "Bem-vindo ao Facebook" );
	}
	
	@Override
	public void navigate() {
		driver.navigate().to( "http://facebook.com" );
	}
}
