package com.fagnerbrack.postbumper.configs;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.fagnerbrack.postbumper.Main;
import com.fagnerbrack.postbumper.configs.facebook.FBConfigs;

public class Configurations {
	private FBConfigs facebook;
	
	public Configurations( JSONObject configs ) {
		this.facebook = new FBConfigs( ( JSONObject )configs.get( "facebook" ) );
	}
	
	public static Configurations getInstance() throws IOException {
		InputStream input = Main.class.getResourceAsStream( "/config.json" );
		InputStreamReader reader = new InputStreamReader( input );
		JSONObject json = ( JSONObject )JSONValue.parse( reader );
		return new Configurations( json );
	}
	
	public FBConfigs facebook() {
		return facebook;
	}
}
