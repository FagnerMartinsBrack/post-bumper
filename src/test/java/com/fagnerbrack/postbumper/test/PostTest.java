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
import com.fagnerbrack.postbumper.pages.facebook.post.FBPost;

public class PostTest {
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
			.startWith( driver, configs.facebook().bumper().page().posts().get( 0 ) )
			.inPost()
			.comment( "bump" )
			.delete()
			.confirm();
		Assert.assertTrue( "Should not throw any errors", true );
	}
	
	private Configurations getConfigs( String fileName ) {
		InputStream input = Main.class.getResourceAsStream( "/" + fileName + ".json" );
		InputStreamReader reader = new InputStreamReader( input );
		JSONObject json = ( JSONObject )JSONValue.parse( reader );
		return new Configurations( json );
	}
}
