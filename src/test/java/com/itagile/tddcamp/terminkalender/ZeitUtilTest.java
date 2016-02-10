package com.itagile.tddcamp.terminkalender;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;

import static java.util.Calendar.HOUR_OF_DAY;
import static java.util.Calendar.MINUTE;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ZeitUtilTest {

	@Test
	public void parstDatumAusString() {
		Date date = ZeitUtil.parseZeit("18:30");
		assertThat(getHour(date), is(18));
		assertThat(getMinute(date), is(30));
	}

	@Test
	public void formatiertDatumInString() {
		Date date = getDateForHourAndMinute(18, 20);
		assertThat(ZeitUtil.formatiereZeit(date), is("18:20"));
	}

	@Test(expected = RuntimeException.class)
	public void wandeltParseExceptionInRuntimeExceptionUm() {
		ZeitUtil.parseZeit("abcd");
	}




	private int getHour(Date date) {
		Calendar calendar = getCalendar(date);
		return calendar.get(HOUR_OF_DAY);
	}

	private int getMinute(Date date) {
		Calendar calendar = getCalendar(date);
		return calendar.get(MINUTE);
	}

	private Calendar getCalendar(Date date) {
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	private Date getDateForHourAndMinute(int hour, int minute) {
		Calendar calendar = getCalendar(new Date());
		calendar.set(HOUR_OF_DAY, hour);
		calendar.set(MINUTE, minute);
		return calendar.getTime();
	}

}
