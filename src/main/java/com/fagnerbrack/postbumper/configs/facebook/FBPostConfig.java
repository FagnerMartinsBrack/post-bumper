package com.fagnerbrack.postbumper.configs.facebook;

import org.json.simple.JSONObject;

public class FBPostConfig {
	private long postId;
	private long storyId;
	
	public FBPostConfig( JSONObject postConfig ) {
		this.postId = ( Long )postConfig.get( "id" );
		this.storyId = ( Long )postConfig.get( "story_id" );
	}
	
	public Long getPostId() {
		return postId;
	}
	
	public Long getStoryId() {
		return storyId;
	}
}
