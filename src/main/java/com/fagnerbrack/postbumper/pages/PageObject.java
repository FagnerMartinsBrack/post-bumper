package com.fagnerbrack.postbumper.pages;

/**
 * Handles custom user behavior within a specific page state.
 */
public abstract class PageObject {
	public PageObject( PageObjectBehavior pageBehavior ) throws WrongPageException {
		pageBehavior.navigate();
		if ( !pageBehavior.isCorrectPage() ) {
			throw new WrongPageException();
		}
	}
}
