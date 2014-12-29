package com.fagnerbrack.postbumper.configs.facebook;

import org.json.simple.JSONObject;

public class FBConfigs {
	private FBAuthConfig auth;
	private FBBumperConfig bumper;
	
	public FBConfigs( JSONObject facebookConfigs ) {
		this.auth = new FBAuthConfig( ( JSONObject )facebookConfigs.get( "auth" ) );
		this.bumper = new FBBumperConfig( ( JSONObject )facebookConfigs.get( "bumper" ) );
	}
	
	public FBAuthConfig auth() {
		return auth;
	}
	
	public FBBumperConfig bumper() {
		return bumper;
	}
}
