package com.fagnerbrack.postbumper.pages.facebook.post;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.fagnerbrack.postbumper.configs.facebook.FBMeConfigs;
import com.fagnerbrack.postbumper.pages.StaticPageObject;
import com.fagnerbrack.postbumper.pages.WrongPageException;

public class PostSharings extends StaticPageObject {
	private WebDriver driver;
	private List<WebElement> sharings = new ArrayList<WebElement>();
	
	private PostSharings( WebDriver driver, List<WebElement> sharings ) {
		this.driver = driver;
		this.sharings = sharings;
	}
	
	public static PostSharings createSharingsByMe( WebDriver driver, FBMeConfigs me )
	throws WrongPageException {
		WebElement dialog = null;
		List<WebElement> dialogs = driver.findElements( By.cssSelector( "[role='dialog']" ) );
		for ( WebElement current : dialogs ) {
			if ( current.isDisplayed() ) {
				dialog = current;
				break;
			}
		}
		
		if ( dialog == null ) {
			throw new WrongPageException();
		}
		
		List<WebElement> sharingsByMe = new ArrayList<WebElement>();
		List<WebElement> allSharings = dialog
			.findElements( By.cssSelector( ".userContentWrapper" ) );
		
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
