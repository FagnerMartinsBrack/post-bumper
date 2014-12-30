package com.fagnerbrack.postbumper.pages;


/**
 * Handles custom user behavior within a dynamic page or component state. This interface should
 * be used when the desired content is not visible by default in the page and should be created
 * from a user generated action (via AJAX or a regular request).
 */
public abstract class DynamicPageObject {
	protected DynamicPageObject( PageObjectBehavior pageBehavior ) throws WrongPageException {
		this( pageBehavior, false );
	}
	
	public DynamicPageObject( PageObjectBehavior pageBehavior, boolean navigate )
	throws WrongPageException {
		if ( navigate == true ) {
			pageBehavior.doNavigate();
		}
		
		if ( !pageBehavior.isCorrectPage() ) {
			throw new WrongPageException();
		}
	}
}
