package com.fagnerbrack.postbumper.configs.facebook;

import org.json.simple.JSONObject;

public class FBConfigs {
	private FBAuthConfig auth;
	private FBBumperConfig bumper;
	private FBMeConfigs me;
	
	public FBConfigs( JSONObject facebookConfigs ) {
		this.auth = new FBAuthConfig( ( JSONObject )facebookConfigs.get( "auth" ) );
		this.bumper = new FBBumperConfig( ( JSONObject )facebookConfigs.get( "bumper" ) );
		this.me = new FBMeConfigs( ( JSONObject )facebookConfigs.get( "me" ) );
	}
	
	public FBAuthConfig auth() {
		return auth;
	}
	
	public FBBumperConfig bumper() {
		return bumper;
	}
	
	public FBMeConfigs me() {
		return me;
	}
}
