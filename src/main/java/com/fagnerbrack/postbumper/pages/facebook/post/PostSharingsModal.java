package com.fagnerbrack.postbumper.pages.facebook.post;

import org.openqa.selenium.WebDriver;

import com.fagnerbrack.postbumper.configs.facebook.FBMeConfigs;
import com.fagnerbrack.postbumper.pages.ChainingException;
import com.fagnerbrack.postbumper.pages.DynamicPageObject;
import com.fagnerbrack.postbumper.pages.Navigate;
import com.fagnerbrack.postbumper.pages.PageObjectBehavior;
import com.fagnerbrack.postbumper.pages.WrongPageException;

public class PostSharingsModal extends DynamicPageObject {
	private WebDriver driver;
	
	private PostSharingsModal( WebDriver driver, PageObjectBehavior pageBehavior )
	throws WrongPageException {
		super( pageBehavior, Navigate.YES );
		this.driver = driver;
	}
	
	public static PostSharingsModal createFrom( WebDriver driver )
	throws WrongPageException {
		PostSharingsModalBehavior postSharingsBehavior = new PostSharingsModalBehavior( driver );
		PostSharingsModal sharings = new PostSharingsModal( driver, postSharingsBehavior );
		return sharings;
	}
	
	public PostSharings inSharingsByMe( FBMeConfigs me ) throws ChainingException {
		try {
			return PostSharings.createSharingsByMe( driver, me );
		} catch ( WrongPageException e ) {
			throw new ChainingException( e );
		}
	}
}
