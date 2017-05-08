package com.luqili.utils.date;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DateUtilsTest {
	private Date d1 = null;
	private Date d2 = null;

	@BeforeMethod
	public void beforeMethod() {
		try {
			d1 = DateUtils.parseDate("2016-10-24", "yyyy-MM-dd");
			d2 = DateUtils.parseDate("2014-09-12", "yyyy-MM-dd");
		} catch (ParseException e) {
			AssertJUnit.fail("日期转换错误");
		}
	}

	@Test
	public void differ() {
		DateUtils.differ(d1, d2, 1000);
	}

	@Test
	public void differDay() {
		int days = DateUtils.differDay(d1, d2);
		AssertJUnit.assertEquals(773, days);
	}

	@Test
	public void differMonth() {
		int month = DateUtils.differMonth(d1, d2);
		AssertJUnit.assertEquals(25, month);
	}

	@Test
	public void differYear() {
		int year = DateUtils.differYear(d1, d2);
		AssertJUnit.assertEquals(2, year);
	}
	@Test
	public void getToday(){
		Date ttd=DateUtils.getTodayTruncateDay();
		String str=DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(ttd);
		Date d=DateUtils.ceiling(ttd, Calendar.DATE);
		
		String str2=DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.format(d);
		System.out.println(str);
		System.out.println(str2);
	}
	@Test
	public void parseDate() throws ParseException {
		String t1 ="2016-04-22";
		String t2 ="2016年04月22日";
		String t3 ="20160422";
		String t4 ="2016-04-22 12:30";
		String t5 ="2016-04-22 12:30:22";
		
		Date d1 = DateUtils.parseDate(t1);
		Date d2 = DateUtils.parseDate(t2);
		Date d3 = DateUtils.parseDate(t3);
		Date d4 = DateUtils.parseDate(t4);
		Date d5 = DateUtils.parseDate(t5);
		
		Date vd1=DateUtils.parseDate(t1,"yyyy-MM-dd");
		Date vd4=DateUtils.parseDate(t4,"yyyy-MM-dd HH:mm");
		Date vd5=DateUtils.parseDate(t5,"yyyy-MM-dd HH:mm:ss");
		
		AssertJUnit.assertEquals(vd1, d1);
		AssertJUnit.assertEquals(vd1, d2);
		AssertJUnit.assertEquals(vd1, d3);
		AssertJUnit.assertEquals(vd4, d4);
		AssertJUnit.assertEquals(vd5, d5);
	}
	
}
