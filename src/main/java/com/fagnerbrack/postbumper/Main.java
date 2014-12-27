package com.fagnerbrack.postbumper;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.fagnerbrack.postbumper.pages.ChainingException;
import com.fagnerbrack.postbumper.pages.WrongPageException;
import com.fagnerbrack.postbumper.pages.facebook.FBLoginPage;

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
			FBLoginPage
				.startUsing( driver )
				.loginAs( "user", "pass" );
			// TODO Read credentials from config
		} catch ( WrongPageException | ChainingException e ) {
			e.printStackTrace();
		}
		
		driver.quit();
	}
}
