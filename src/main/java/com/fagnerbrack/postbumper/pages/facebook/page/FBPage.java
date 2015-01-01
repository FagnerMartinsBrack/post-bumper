package com.fagnerbrack.postbumper.pages.facebook.page;

import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.fagnerbrack.postbumper.configs.facebook.FBPageConfig;
import com.fagnerbrack.postbumper.configs.facebook.FBPostConfig;
import com.fagnerbrack.postbumper.pages.ChainingException;
import com.fagnerbrack.postbumper.pages.DynamicPageObject;
import com.fagnerbrack.postbumper.pages.Navigate;
import com.fagnerbrack.postbumper.pages.PageObjectBehavior;
import com.fagnerbrack.postbumper.pages.WrongPageException;
import com.fagnerbrack.postbumper.pages.facebook.post.FBPost;

public class FBPage extends DynamicPageObject {
	private WebElement postContainer;
	private WebDriver driver;
	
	private FBPage(
		WebDriver driver,
		FBPageConfig pageData,
		PageObjectBehavior pageBehavior,
		boolean navigate
	) throws WrongPageException {
		super( pageBehavior, navigate );
		this.driver = driver;
		this.postContainer = driver
			.findElement( By.id( "PagePostsPagelet_" + pageData.getPageId() ) );
	}
	
	public static FBPage startUsing( WebDriver driver, FBPageConfig pageData )
	throws WrongPageException {
		FBPageBehavior pageBehavior = new FBPageBehavior( driver, pageData );
		return new FBPage( driver, pageData, pageBehavior, Navigate.YES );
	}
	
	public static FBPage create( WebDriver driver, FBPageConfig pageData )
	throws WrongPageException {
		FBPageBehavior pageBehavior = new FBPageBehavior( driver, pageData );
		return new FBPage( driver, pageData, pageBehavior, Navigate.NO );
	}
	
	public FBPost goToPost( FBPostConfig postData ) throws ChainingException {
		WebElement targetPost = null;
		WebElement linkToPost = null;
		long storyId = postData.getStoryId();
		
		// Find the post container element
		List<WebElement> potentialPosts = postContainer
			.findElements( By.cssSelector( ".timelineUnitContainer[data-gt]" ) );
		for ( WebElement element : potentialPosts ) {
			JSONObject json = ( JSONObject )JSONValue.parse( element.getAttribute( "data-gt" ) );
			long aggregationId = Long.valueOf( ( String )json.get( "aggregation_id" ) );
			if ( aggregationId == storyId ) {
				targetPost = element;
				break;
			}
		}
		if ( targetPost == null ) {
			throw new ChainingException( "Post with id '" + storyId + "' not found" );
		}
		
		// Find the link for the post's detailed page
		List<WebElement> postMainLinks = targetPost
			.findElements( By.cssSelector( ".uiLinkSubtle" ) );
		for (  WebElement element : postMainLinks ) {
			if ( element.getAttribute( "href" ).contains( storyId + "" ) ) {
				linkToPost = element;
				break;
			}
		}
		if ( linkToPost == null ) {
			throw new ChainingException(
				"I couldn't find the link to the post with id '" + storyId + "'"
			);
		}
		
		linkToPost.click();
		
		try {
			return FBPost.create( driver, postData );
		} catch ( WrongPageException e ) {
			throw new ChainingException( e );
		}
	}
}
