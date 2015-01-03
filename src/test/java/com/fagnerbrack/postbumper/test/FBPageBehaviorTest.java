package com.fagnerbrack.postbumper.test;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.openqa.selenium.WebDriver;

import com.fagnerbrack.postbumper.configs.facebook.FBPageConfig;
import com.fagnerbrack.postbumper.pages.facebook.page.FBPageBehavior;

@RunWith( MockitoJUnitRunner.class )
public class FBPageBehaviorTest {
	@Mock
	private WebDriver driver;
	
	@Mock
	private FBPageConfig pageData;
	
	@Test
	public void should_account_for_activities_in_title() {
		Mockito.when( driver.getTitle() ).thenReturn( "(1) My Page" );
		Mockito.when( pageData.getPageName() ).thenReturn( "My Page" );
		
		FBPageBehavior behavior = new FBPageBehavior( driver, pageData );
		boolean expected = true;
		boolean actual = behavior.isCorrectPage();
		
		Assert.assertEquals( expected, actual );
	}
}
