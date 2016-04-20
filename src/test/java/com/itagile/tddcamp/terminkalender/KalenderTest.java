package com.itagile.tddcamp.terminkalender;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.mockito.Mockito;

import com.itagile.tddcamp.log.LogReporting;

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

	@Test
	public void reporteFehlerBeiLogSchreibung() {
		// given
		final String givenFehlermeldung = "Platte voll!";

		Logger mockedLogger = mock(Logger.class);
		doThrow(new RuntimeException(givenFehlermeldung)) //
				.when(mockedLogger).neuerTerminEingetragen(kalender, termin);
		kalender.setLogger(mockedLogger);

		LogReporting mockedReporting = mock(LogReporting.class);
		kalender.setReporting(mockedReporting);

		// when
		kalender.trageEin(termin);

		// then
		verify(mockedReporting, times(1)) //
				.loggerCouldNotLogEntry(mockedLogger, givenFehlermeldung);
	}
}
