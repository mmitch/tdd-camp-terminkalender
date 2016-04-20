package com.itagile.tddcamp.terminkalender;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.Mockito;

public class KalenderTest {

	private Kalender kalender = new Kalender();
	private Termin termin = new Termin();
	private final Teilnehmer teilnehmer;

	public KalenderTest() {
		teilnehmer = new Teilnehmer();
		kalender.setBesitzer(teilnehmer);
	}

	@Test
	public void neuerTerminIstImKalender() {
		kalender.trageEin(termin);
		assertThat(kalender.termine, hasItem(termin));
	}

	@Test
	public void besitzerDesKalendersIstTeilnehmerImTermin() {
		kalender.trageEin(termin);
		assertTrue(termin.nimmtTeil(teilnehmer));
	}

	@Test(expected = TerminDoppeltEingetragenException.class)
	public void verhindertDoppelteTermine() {
		kalender.trageEin(termin);
		kalender.trageEin(termin);
	}

	@Test
	public void schreibeLogBeiTermineintrag() {
		// given
		Logger mockedLogger = mock(Logger.class);
		kalender.setLogger(mockedLogger);

		// when
		kalender.trageEin(termin);

		// then
		verify(mockedLogger, times(1)).neuerTerminEingetragen(kalender, termin);
	}
}
