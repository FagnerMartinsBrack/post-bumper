package com.fagnerbrack.postbumper.configs.facebook;

import org.json.simple.JSONObject;

public class FBMeConfigs {
	private String name;
	
	protected FBMeConfigs( JSONObject me ) {
		this.name = ( String )me.get( "name" );
	}
	
	public String getName() {
		return name;
	}
}
