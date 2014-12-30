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
	private WebDriver driver;
	
	public PagesNav( WebDriver driver ) {
		this.driver = driver;
	}
	
	public static PagesNav createFrom( WebDriver driver ) {
		PagesNav pagesNav = new PagesNav( driver );
		PageFactory.initElements( driver, pagesNav );
		return pagesNav;
	}
	
	public FBPage goToPage( FBPageConfig pageData ) throws ChainingException {
		String cssSelector = "[data-testid='left_nav_item_" + pageData.getPageName() + "']";
		WebElement link = pagesNav.findElement( By.cssSelector( cssSelector ) );
		link.click();
		try {
			return FBPage.create( driver, pageData );
		} catch ( WrongPageException e ) {
			throw new ChainingException( e );
		}
	}
}
