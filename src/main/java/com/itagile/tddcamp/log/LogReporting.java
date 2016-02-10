package com.itagile.tddcamp.log;

import com.itagile.tddcamp.terminkalender.Logger;

public interface LogReporting {
	void loggerCouldNotLogEntry(Logger logger, String message);
}
