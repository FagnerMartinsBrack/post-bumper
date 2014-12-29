package com.fagnerbrack.postbumper.configs.facebook;

import org.json.simple.JSONObject;

import com.fagnerbrack.postbumper.pages.AuthenticationData;

public class FBAuthConfig implements AuthenticationData {
	private String email;
	private String password;
	
	protected FBAuthConfig( JSONObject facebookAuth ) {
		this.email = ( String )facebookAuth.get( "username" );
		this.password = ( String )facebookAuth.get( "password" );
	}
	
	@Override
	public String username() {
		return email;
	}
	
	@Override
	public String password() {
		return password;
	}
}
