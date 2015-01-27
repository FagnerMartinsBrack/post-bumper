package com.fagnerbrack.postbumper.repetition;

import junit.framework.Assert;

import org.junit.Test;

public class BumpingIntervalTest {
	@Test
	public void should_be_able_to_convert_15m_to_millis() throws RepetitionException {
		BumpingInterval interval = new BumpingInterval( "15m" );
		
		long actual = interval.toTimeInMillis();
		long expected = 900000L;
		
		Assert.assertEquals( expected, actual );
	}
}
