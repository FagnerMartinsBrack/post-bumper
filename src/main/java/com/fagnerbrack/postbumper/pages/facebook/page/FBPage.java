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
import com.fagnerbrack.postbumper.pages.PageObjectBehavior;
import com.fagnerbrack.postbumper.pages.WrongPageException;
import com.fagnerbrack.postbumper.pages.facebook.post.FBPost;

public class FBPage extends DynamicPageObject {
	private PageObjectBehavior pageBehavior;
	private WebElement posts;
	
	private FBPage( PageObjectBehavior pageBehavior ) throws WrongPageException {
		super( pageBehavior );
		this.pageBehavior = pageBehavior;
	}
	
	public static FBPage createFrom( WebDriver driver, FBPageConfig pageData )
	throws WrongPageException {
		FBPage product = new FBPage( new FBPageBehavior( driver, pageData ) );
		product.posts = driver.findElement( By.id( "PagePostsPagelet_" + pageData.getPageId() ) );
		return product;
	}
	
	public FBPost goToPost( FBPostConfig postData ) throws ChainingException {
		WebElement targetPost = null;
		WebElement linkToPost = null;
		long targetPostId = postData.getPostId();
		
		// Find the post container element
		List<WebElement> potentialPosts = posts
			.findElements( By.cssSelector( ".timelineUnitContainer[data-gt]" ) );
		for ( WebElement element : potentialPosts ) {
			JSONObject json = ( JSONObject )JSONValue.parse( element.getAttribute( "data-gt" ) );
			long postId = Long.valueOf( ( String )json.get( "aggregation_id" ) );
			if ( postId == targetPostId ) {
				targetPost = element;
				break;
			}
		}
		if ( targetPost == null ) {
			throw new ChainingException( "Post with id '" + targetPostId + "' not found" );
		}
		
		// Find the link for the post's detailed page
		List<WebElement> postMainLinks = targetPost
			.findElements( By.cssSelector( ".uiLinkSubtle" ) );
		for (  WebElement element : postMainLinks ) {
			if ( element.getAttribute( "href" ).contains( targetPostId + "" ) ) {
				linkToPost = element;
				break;
			}
		}
		if ( linkToPost == null ) {
			throw new ChainingException(
				"I couldn't find the link to the post with id '" + targetPostId + "'"
			);
		}
		
		linkToPost.click();
		
		try {
			return FBPost.createFrom( pageBehavior.driver(), postData );
		} catch ( WrongPageException e ) {
			throw new ChainingException( e );
		}
	}
}
