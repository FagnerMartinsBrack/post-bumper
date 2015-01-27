package com.fagnerbrack.postbumper.configs.facebook;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.fagnerbrack.postbumper.repetition.BumpingInterval;

/**
 * Object representing a Facebook's page data
 */
public class FBPageConfig {
	private long id;
	private String name;
	private String url;
	private BumpingInterval interval;
	private long iterations;
	private ArrayList<FBPostConfig> posts = new ArrayList<FBPostConfig>();
	
	protected FBPageConfig( JSONObject pageConfig ) {
		this.id = ( Long )pageConfig.get( "id" );
		this.name = ( String )pageConfig.get( "name" );
		this.url = ( String )pageConfig.get( "url" );
		this.interval = new BumpingInterval( ( String )pageConfig.get( "interval" ) );
		this.iterations = ( Long )pageConfig.get( "iterations" );
		
		JSONArray postsArray = ( JSONArray )pageConfig.get( "posts" );
		for ( Object item : postsArray ) {
			JSONObject post = ( JSONObject )item;
			posts.add( new FBPostConfig( post ) );
		}
	}
	
	public long getPageId() {
		return id;
	}
	
	public String getPageName() {
		return name;
	}
	
	public String getPageURL() {
		return url;
	}
	
	public List<FBPostConfig> posts() {
		return posts;
	}
	
	public BumpingInterval getInterval() {
		return interval;
	}
	
	public long getIterations() {
		return iterations;
	}
}
