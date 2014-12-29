package com.fagnerbrack.postbumper.pages.facebook.post;

import org.openqa.selenium.WebDriver;

import com.fagnerbrack.postbumper.configs.facebook.FBPostConfig;
import com.fagnerbrack.postbumper.pages.PageObjectBehavior;
import com.fagnerbrack.postbumper.pages.NotImplementedException;

public class FBPostBehavior extends PageObjectBehavior {
	private WebDriver driver;
	private FBPostConfig postData;
	
	public FBPostBehavior( WebDriver driver, FBPostConfig postData ) {
		super( driver );
		this.driver = driver;
		this.postData = postData;
	}
	
	@Override
	public boolean isCorrectPage() {
		return driver.getCurrentUrl().contains( postData.getPostId().toString() );
	}
	
	@Override
	public void doNavigate() {
		throw new NotImplementedException( "The post navigation behavior is not yet implemented" );
	}
}
