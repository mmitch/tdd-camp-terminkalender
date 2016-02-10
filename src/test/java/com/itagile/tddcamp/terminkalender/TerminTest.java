package com.itagile.tddcamp.terminkalender;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TerminTest {

	private Termin termin;

	@Before
	public void setUp() throws Exception {
		termin = new Termin();
	}

	@Test
	public void eingeladenerBenutzerIstTeilnehmer() {
		Teilnehmer teilnehmer = new Teilnehmer();
		termin.ladeEin(teilnehmer);
		assertTrue(termin.nimmtTeil(teilnehmer));
	}

	@Test(expected = EndzeitVorStartzeitException.class)
	public void stelltSicherDassEndzeitNachStartzeitLiegt() {
		termin.setStartzeit("10:00");
		termin.setEndzeit("9:00");
	}

	@Test
	public void berechnetDauerDesTermins() {
		termin.setStartzeit("9:00");
		termin.setEndzeit("10:00");
		assertThat(termin.getDauer(), is(1));
	}

}
