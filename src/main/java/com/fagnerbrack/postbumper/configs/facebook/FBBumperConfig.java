package com.fagnerbrack.postbumper.configs.facebook;

import org.json.simple.JSONObject;

public class FBBumperConfig {
	private FBPageConfig page;
	
	protected FBBumperConfig( JSONObject bumperConfig ) {
		this.page = new FBPageConfig( ( JSONObject )bumperConfig.get( "page" ) );
	}
	
	public FBPageConfig page() {
		return page;
	}
}
