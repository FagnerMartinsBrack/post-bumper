package com.fagnerbrack.postbumper.pages.facebook.feed;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.fagnerbrack.postbumper.configs.facebook.FBPageConfig;
import com.fagnerbrack.postbumper.pages.ChainingException;
import com.fagnerbrack.postbumper.pages.StaticPageObject;
import com.fagnerbrack.postbumper.pages.WrongPageException;
import com.fagnerbrack.postbumper.pages.facebook.page.FBPage;

public class PagesNav extends StaticPageObject {
	private WebElement pagesNav;
	
	public PagesNav( WebDriver driver ) {
		super( driver );
	}
	
	public static PagesNav createFrom( WebDriver driver ) {
		PagesNav pagesNav = new PagesNav( driver );
		PageFactory.initElements( driver, pagesNav );
		return pagesNav;
	}
	
	public FBPage goToPage( FBPageConfig pageData ) throws ChainingException {
		pagesNav
			.findElement( By.cssSelector( "[data-itemid='" + pageData.getPageId() + "']" ) )
			.findElement( By.linkText( pageData.getPageName() ) )
			.click();
		try {
			return FBPage.createFrom( driver, pageData );
		} catch ( WrongPageException e ) {
			throw new ChainingException( e );
		}
	}
}
