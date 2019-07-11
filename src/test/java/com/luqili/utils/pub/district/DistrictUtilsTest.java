package com.luqili.utils.pub.district;

import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class DistrictUtilsTest {

    @Test
    public void getDistrictName() {
        String name1 = DistrictUtils.getDistrictName(37);
        String name2 = DistrictUtils.getDistrictName(3715);
        String name3 = DistrictUtils.getDistrictName(371521);
        AssertJUnit.assertEquals("山东省", name1);
        AssertJUnit.assertEquals("聊城市", name2);
        AssertJUnit.assertEquals("阳谷县", name3);
        String name4 = DistrictUtils.getDistrictFullName(371521);
        System.out.println(name4);
    }

    @Test
    public void getDistrictNames() {
        DistrictUtils.getDistrictNames();
    }
}
