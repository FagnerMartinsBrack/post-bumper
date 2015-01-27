package com.fagnerbrack.postbumper.repetition;

import com.fagnerbrack.postbumper.configs.facebook.FBPageConfig;


public class DefaultRepetition {
	private BumpingInterval intervalConfig;
	private long iterationsConfig;
	
	public DefaultRepetition( FBPageConfig pageConfig ) {
		this.intervalConfig = pageConfig.getInterval();
		this.iterationsConfig = pageConfig.getIterations();
	}
	
	public void repeat( RepetitionAction action ) throws RepetitionException {
		try {
			for ( int i = 0; i < iterationsConfig; i++ ) {
				try {
					action.run();
				} catch ( Exception e ) {
					throw new RepetitionException( e );
				}
				Thread.sleep( intervalConfig.toTimeInMillis() );
			}
		} catch ( InterruptedException e ) {
			throw new RepetitionException( e );
		}
	}
}
