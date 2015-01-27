package com.fagnerbrack.postbumper;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.fagnerbrack.postbumper.configs.Configurations;
import com.fagnerbrack.postbumper.pages.ChainingException;
import com.fagnerbrack.postbumper.pages.WrongPageException;
import com.fagnerbrack.postbumper.pages.facebook.FacebookLogin;
import com.fagnerbrack.postbumper.pages.facebook.post.PostSharings;
import com.fagnerbrack.postbumper.pages.facebook.post.StaticComment;
import com.fagnerbrack.postbumper.pages.facebook.post.StaticDeleteConfirmation;
import com.fagnerbrack.postbumper.pages.facebook.post.StaticPost;
import com.fagnerbrack.postbumper.pages.facebook.post.UnableToCommentException;
import com.fagnerbrack.postbumper.repetition.DefaultRepetition;
import com.fagnerbrack.postbumper.repetition.RepetitionAction;
import com.fagnerbrack.postbumper.repetition.RepetitionException;

public class Main {
	public static void main( String[] arguments ) {
		System.setProperty( "webdriver.chrome.driver", "/webdrivers/chromedriver_2.13.exe" );
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments( "-incognito" );
		DesiredCapabilities capabilities = DesiredCapabilities.chrome();
		capabilities.setCapability( ChromeOptions.CAPABILITY, options );
		
		WebDriver driver = new ChromeDriver( capabilities );
		driver.manage().timeouts().implicitlyWait( 20, TimeUnit.SECONDS );
		
		try {
			Configurations configs = Configurations.getInstance();
			
			final PostSharings sharingsByMe = FacebookLogin
				.startUsing( driver )
				.loginWith( configs.facebook().auth() )
				.inPagesNavigation()
				.goToPage( configs.facebook().bumper().page() )
				.goToPost( configs.facebook().bumper().page().posts().get( 0 ) )
				.goToPostSharings()
				.inSharingsByMe( configs.facebook().me() );
			
			new DefaultRepetition(
				configs.facebook().bumper().page()
			).repeat(new RepetitionAction() {
				@Override
				public void run() throws ChainingException {
					bumpMySharings( sharingsByMe );
				}
			});
			bumpMySharings( sharingsByMe );
		} catch ( WrongPageException | ChainingException | IOException | RepetitionException e ) {
			e.printStackTrace();
		}
		
		driver.quit();
	}
	
	private static void bumpMySharings( PostSharings sharingsByMe ) throws ChainingException {
		for ( int i = 0; i < sharingsByMe.size(); i++ ) {
			StaticPost sharing = sharingsByMe.get( i );
			try {
				StaticComment comment = sharing.comment( "." );
				StaticDeleteConfirmation confirmation = comment.delete();
				confirmation.confirm();
			} catch ( UnableToCommentException e ) {
				System.out.println( "Unable to comment in a post" );
			}
		}
	}
}
