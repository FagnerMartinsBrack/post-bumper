package com.fagnerbrack.postbumper.configs.facebook;

public class BumpingStrategyConfig {
	private BumpingStrategy strategy;
	
	protected BumpingStrategyConfig( String strategy ) {
		this.strategy = BumpingStrategy.valueOf( strategy );
	}
	
	public BumpingStrategy strategy() {
		return strategy;
	}
}
