package com.luqili.utils.pub.string;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class StringUtilsTest {
    @Test
    public void hideMiddleCharacter() {
        String t1 = StringUtils.hideMiddleCharacter(null, 1, 2, "*", 3);
        String t2 = StringUtils.hideMiddleCharacter("a", 1, 2, "*", 3);
        String t3 = StringUtils.hideMiddleCharacter("ab", 1, 2, "*", 3);
        String t4 = StringUtils.hideMiddleCharacter("abc", 1, 2, "*", 3);
        String t5 = StringUtils.hideMiddleCharacter("abcd", 1, 2, "*", 3);
        AssertJUnit.assertEquals("***", t1);
        AssertJUnit.assertEquals("a***a", t2);
        AssertJUnit.assertEquals("a***ab", t3);
        AssertJUnit.assertEquals("a***bc", t4);
        AssertJUnit.assertEquals("a***cd", t5);
    }
}
