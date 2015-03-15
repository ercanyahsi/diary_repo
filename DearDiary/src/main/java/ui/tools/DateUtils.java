package ui.tools;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DateUtils {
	

	public static java.sql.Date getCurrentDate() {
		GregorianCalendar today = new GregorianCalendar();
		StringBuffer sb = new StringBuffer();
		int temp = 0;
		sb.append(today.get(Calendar.YEAR));
		sb.append("-");
		temp = today.get(Calendar.MONTH) + 1;
		sb.append((temp < 10) ? "0" + temp : String.valueOf(temp));
		sb.append("-");
		temp = today.get(Calendar.DAY_OF_MONTH);
		sb.append((temp < 10) ? "0" + temp : String.valueOf(temp));
		return Date.valueOf(sb.toString());
	}


	public static Date before(Date tarih, int days) {
		Calendar cal = Calendar.getInstance();
        cal.setTime(tarih);
        cal.add(Calendar.DATE, days*(-1)); //minus number would decrement the days
        return new Date(cal.getTimeInMillis());
	}

	public static Date after(Date tarih, int days) {
		Calendar cal = Calendar.getInstance();
        cal.setTime(tarih);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return new Date(cal.getTimeInMillis());
	}

	public static int diff(Date start, Date end) {
		long oneDay = 1000 * 60 * 60 * 24; //miliseconds for 1 day
		long oneHour = 1000*60*60;//miliseconds for 1 hour
		long diff = end.getTime() + oneHour - start.getTime();
		return (int) (diff / oneDay);
	}
	

	public static int getCurrentYear() {
		GregorianCalendar today = new GregorianCalendar();
		return today.get(Calendar.YEAR);
	}

	public static int getCurrentMonth() {
		GregorianCalendar today = new GregorianCalendar();
		return today.get(Calendar.MONTH) + 1;
	}


}
