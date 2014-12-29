package com.fagnerbrack.postbumper.configs.facebook;

import org.json.simple.JSONObject;

public class FBPostConfig {
	private long postId;
	
	public FBPostConfig( JSONObject postConfig ) {
		this.postId = ( Long )postConfig.get( "id" );
	}
	
	public Long getPostId() {
		return postId;
	}
}
