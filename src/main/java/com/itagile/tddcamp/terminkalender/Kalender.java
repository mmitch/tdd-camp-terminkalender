package com.itagile.tddcamp.terminkalender;

import java.util.ArrayList;
import java.util.List;

import com.itagile.tddcamp.log.FileLogger;

public class Kalender {

	List<Termin> termine = new ArrayList<Termin>();
	private Teilnehmer teilnehmer;
	private Logger logger = new FileLogger();

	public void trageEin(Termin termin) {
		if (hat(termin)) {
			throw new TerminDoppeltEingetragenException();
		}
		termin.ladeEin(teilnehmer);
		termine.add(termin);
		logger.neuerTerminEingetragen(this, termin);
	}

	public boolean hat(Termin termin) {
		return termine.contains(termin);
	}

	public void setBesitzer(Teilnehmer teilnehmer) {
		this.teilnehmer = teilnehmer;
	}

	void setLogger(Logger logger) {
		this.logger = logger;
	}
}
