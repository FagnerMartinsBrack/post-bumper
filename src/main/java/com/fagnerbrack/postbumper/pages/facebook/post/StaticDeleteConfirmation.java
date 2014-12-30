package com.fagnerbrack.postbumper.pages.facebook.post;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fagnerbrack.postbumper.pages.StaticPageObject;
import com.google.common.base.Function;

public class StaticDeleteConfirmation extends StaticPageObject {
	private WebElement layer;
	
	private StaticDeleteConfirmation( WebElement layer ) {
		this.layer = layer;
	}
	
	public static StaticDeleteConfirmation create( WebDriver driver ) {
		WebElement visibleLayer = new WebDriverWait( driver, 10, 1000 )
			.until(new Function<WebDriver, WebElement>() {
				@Override
				public WebElement apply( WebDriver input ) {
					List<WebElement> layers = input.findElements(
						By.cssSelector( ".uiLayer" )
					);
					for ( WebElement layer : layers ) {
						if( layer.isDisplayed() ) {
							return layer;
						}
					}
					return null;
				}
			});
		return new StaticDeleteConfirmation( visibleLayer );
	}
	
	public void confirm() {
		WebElement confirmButton = layer.findElement( By.cssSelector( ".layerConfirm" ) );
		confirmButton.click();
	}
}
