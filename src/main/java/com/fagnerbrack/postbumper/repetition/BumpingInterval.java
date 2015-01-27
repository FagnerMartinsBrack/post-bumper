package com.fagnerbrack.postbumper.repetition;

public class BumpingInterval {
	private String intervalConfig;
	
	public BumpingInterval( String intervalConfig ) {
		this.intervalConfig = intervalConfig;
	}
	
	public long toTimeInMillis() throws RepetitionException {
		String timeFormat = intervalConfig
			.substring( intervalConfig.length() - 1, intervalConfig.length() );
		int timeUnit = Integer.valueOf( intervalConfig.replace( timeFormat, "" ) );
		
		if ( "m".equals( timeFormat ) ) {
			return timeUnit * 60000;
		}
		
		throw new RepetitionException( "Time format not implemented: '" + intervalConfig + "'" );
	}
}
