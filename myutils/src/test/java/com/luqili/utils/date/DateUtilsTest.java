package com.luqili.utils.date;

import java.text.ParseException;
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
}
