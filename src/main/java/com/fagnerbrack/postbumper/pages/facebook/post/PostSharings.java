package com.fagnerbrack.postbumper.pages.facebook.post;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.fagnerbrack.postbumper.configs.facebook.FBMeConfigs;
import com.fagnerbrack.postbumper.pages.StaticPageObject;

public class PostSharings extends StaticPageObject {
	private WebDriver driver;
	private List<WebElement> sharings = new ArrayList<WebElement>();
	
	private PostSharings( WebDriver driver, List<WebElement> sharings ) {
		this.driver = driver;
		this.sharings = sharings;
	}
	
	public static PostSharings createSharingsByMe( WebDriver driver, FBMeConfigs me ) {
		List<WebElement> sharingsByMe = new ArrayList<WebElement>();
		List<WebElement> allSharings = driver.findElements( By.className( "userContentWrapper" ) );
		
		for ( WebElement sharedPost : allSharings ) {
			WebElement author = sharedPost
				.findElement( By.cssSelector( ".profileLink[data-hovercard]" ) );
			if ( author.getText().equals( me.getName() ) ) {
				sharingsByMe.add( sharedPost );
			}
		}
		
		return new PostSharings( driver, sharingsByMe );
	}
	
	public StaticPost get( int index ) {
		WebElement sharedPost = sharings.get( index );
		return StaticPost.createSharingPost( driver, sharedPost );
	}
	
	public int size() {
		return sharings.size();
	}
}
