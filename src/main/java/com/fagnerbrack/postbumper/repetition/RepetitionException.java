package com.fagnerbrack.postbumper.repetition;

public class RepetitionException extends Exception {
	private static final long serialVersionUID = 1;
	public RepetitionException( String msg ) {
		super( msg );
	}
	public RepetitionException( Throwable e ) {
		super( e );
	}
}
