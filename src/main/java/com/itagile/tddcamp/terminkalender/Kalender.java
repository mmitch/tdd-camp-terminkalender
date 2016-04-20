package com.itagile.tddcamp.terminkalender;

import java.util.ArrayList;
import java.util.List;

import com.itagile.tddcamp.log.FileLogger;
import com.itagile.tddcamp.log.LogReporting;
import com.itagile.tddcamp.log.WebserviceLogReporter;

public class Kalender {

	List<Termin> termine = new ArrayList<Termin>();
	private Teilnehmer teilnehmer;
	private Logger logger = new FileLogger();
	private LogReporting reporting = new WebserviceLogReporter();

	public void trageEin(Termin termin) {
		if (hat(termin)) {
			throw new TerminDoppeltEingetragenException();
		}
		termin.ladeEin(teilnehmer);
		termine.add(termin);
		try {
			logger.neuerTerminEingetragen(this, termin);
		} catch (RuntimeException e) {
			reporting.loggerCouldNotLogEntry(logger, e.getMessage());
		}
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

	void setReporting(LogReporting reporting) {
		this.reporting = reporting;
	}
}
