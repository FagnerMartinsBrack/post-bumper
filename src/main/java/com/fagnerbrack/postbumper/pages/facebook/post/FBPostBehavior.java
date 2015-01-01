package com.fagnerbrack.postbumper.pages.facebook.post;

import org.openqa.selenium.WebDriver;

import com.fagnerbrack.postbumper.configs.facebook.FBPostConfig;
import com.fagnerbrack.postbumper.pages.PageObjectBehavior;

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
		String url = "https://www.facebook.com/permalink.php";
		String query = "story_fbid=" + postData.getStoryId() + "&id=" + postData.getPostId();
		driver.navigate().to( url + "?" + query );
	}
}
