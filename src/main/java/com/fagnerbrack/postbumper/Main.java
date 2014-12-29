package com.fagnerbrack.postbumper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.fagnerbrack.postbumper.configs.Configurations;
import com.fagnerbrack.postbumper.configs.facebook.BumpingStrategy;
import com.fagnerbrack.postbumper.pages.ChainingException;
import com.fagnerbrack.postbumper.pages.WrongPageException;
import com.fagnerbrack.postbumper.pages.facebook.FacebookLogin;

public class Main {
	public static void main( String[] arguments ) {
		System.setProperty( "webdriver.chrome.driver", "/webdrivers/chromedriver_2.13.exe" );
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments( "-incognito" );
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability( ChromeOptions.CAPABILITY, options );
		
		WebDriver driver = new ChromeDriver( capabilities );
		driver.manage().timeouts().implicitlyWait( 10, TimeUnit.SECONDS );
		
		try {
			Configurations configs = Configurations.getInstance();
			FacebookLogin
				.startUsing( driver )
				.loginWith( configs.facebook().auth() )
				.inPagesNavigation()
				.goToPage( configs.facebook().bumper().page() )
				.goToPost( configs.facebook().bumper().page().posts().get( 0 ) )
				.goToPostSharings()
				.bumpWith( BumpingStrategy.SHARED_BY_ME );
		} catch ( WrongPageException | ChainingException | IOException e ) {
			e.printStackTrace();
		}
		
		driver.quit();
	}
}
