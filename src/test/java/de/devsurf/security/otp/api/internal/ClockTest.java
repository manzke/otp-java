/*
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.devsurf.security.otp.api.internal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.devsurf.security.otp.api.Clock;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class ClockTest {

    @Mock
    private Calendar calendar;

    @InjectMocks
    private Clock clock = new Clock();

    @Before
    public void setUp()
        throws Exception {
        MockitoAnnotations.initMocks( this );
        Calendar gregorianCalendar = GregorianCalendar.getInstance( TimeZone.getTimeZone( "UTC" ) );
        gregorianCalendar.set( 2012, Calendar.DECEMBER, 21, 0, 0, 0 );
        when( calendar.getTimeInMillis() ).thenReturn( gregorianCalendar.getTimeInMillis() );
    }

    @Test
    public void testGetCurrentInterval()
        throws Exception {
        final long interval = 45201600L;
        assertEquals( interval, clock.getCurrentInterval() );
    }

    public static void main( String[] args )
        throws Exception {
        Clock c = new Clock();
        while ( true ) {
            System.out.println( c.getCurrentInterval() );
            Thread.sleep( 1000 );
        }
    }
}
