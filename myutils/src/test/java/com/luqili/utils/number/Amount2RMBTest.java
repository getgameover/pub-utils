package com.luqili.utils.number;

import java.math.BigDecimal;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class Amount2RMBTest {

	@Test
	public void convertBigDecimal() {
		BigDecimal price = new BigDecimal("111.156");
		System.out.println();
		AssertJUnit.assertEquals("壹佰壹拾壹元壹角伍分", Amount2RMB.convert(price));
	}

	@Test
	public void convertString() {
		AssertJUnit.assertEquals("壹万陆仟肆佰零玖元零贰分", Amount2RMB.convert("16,409.02"));
		AssertJUnit.assertEquals("壹仟肆佰零玖元伍角", Amount2RMB.convert("1,409.50"));
		AssertJUnit.assertEquals("壹万陆仟肆佰零玖元零贰分", Amount2RMB.convert("16,409.02"));
		AssertJUnit.assertEquals("壹分", Amount2RMB.convert("0.01"));
	}
	
}
