package com.fagnerbrack.postbumper.test;

import java.io.InputStream;
import java.io.InputStreamReader;

import junit.framework.Assert;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.fagnerbrack.postbumper.Main;
import com.fagnerbrack.postbumper.configs.Configurations;
import com.fagnerbrack.postbumper.pages.ChainingException;
import com.fagnerbrack.postbumper.pages.WrongPageException;
import com.fagnerbrack.postbumper.pages.facebook.FacebookLogin;
import com.fagnerbrack.postbumper.pages.facebook.page.FBPage;
import com.fagnerbrack.postbumper.pages.facebook.post.FBPost;
import com.fagnerbrack.postbumper.pages.facebook.post.PostSharings;
import com.fagnerbrack.postbumper.pages.facebook.post.StaticComment;
import com.fagnerbrack.postbumper.pages.facebook.post.StaticDeleteConfirmation;
import com.fagnerbrack.postbumper.pages.facebook.post.StaticPost;

public class PostBumpingTest {
	private WebDriver driver;
	
	@Before
	public void beforeTest() {
		System.setProperty( "webdriver.chrome.driver", "/webdrivers/chromedriver_2.13.exe" );
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments( "-incognito" );
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability( ChromeOptions.CAPABILITY, options );
		
		this.driver = new ChromeDriver( capabilities );
	}
	
	@After
	public void afterTest() {
		this.driver.quit();
	}
	
	@Test
	public void should_comment_and_delete() throws ChainingException, WrongPageException {
		Configurations configs = getConfigs( "should_comment_and_delete" );
		FacebookLogin
			.startUsing( driver )
			.loginWith( configs.facebook().auth() );
		FBPost
			.startUsing( driver, configs.facebook().bumper().page().posts().get( 0 ) )
			.inPostContent()
			.comment( "bump" )
			.delete()
			.confirm();
		Assert.assertTrue( "Should execute the process without error (missing elements)", true );
	}
	
	@Test
	public void should_comment_and_delete_in_post_sharings()
	throws ChainingException, WrongPageException {
		Configurations configs = getConfigs( "should_comment_and_delete_in_post_sharings" );
		
		FacebookLogin
			.startUsing( driver )
			.loginWith( configs.facebook().auth() );
		
		PostSharings sharings = FBPage
			.startUsing( driver, configs.facebook().bumper().page() )
			.goToPost( configs.facebook().bumper().page().posts().get( 0 ) )
			.goToPostSharings()
			.inSharingsByMe( configs.facebook().me() );
		
		Assert.assertEquals( "Should load 2 shared posts", 2, sharings.size() );
		
		for ( int i = 0; i < sharings.size(); i++ ) {
			StaticPost sharedPost = sharings.get( i );
			StaticComment comment = sharedPost.comment( "bump" );
			StaticDeleteConfirmation confirmation = comment.delete();
			confirmation.confirm();
		}
		
		Assert.assertTrue( "Should execute the process without error (missing elements)", true );
	}
	
	private Configurations getConfigs( String fileName ) {
		InputStream input = Main.class.getResourceAsStream( "/" + fileName + ".json" );
		InputStreamReader reader = new InputStreamReader( input );
		JSONObject json = ( JSONObject )JSONValue.parse( reader );
		return new Configurations( json );
	}
}
