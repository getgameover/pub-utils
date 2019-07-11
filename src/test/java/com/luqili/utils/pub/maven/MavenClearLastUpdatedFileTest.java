package com.luqili.utils.pub.maven;

import org.testng.annotations.Test;

public class MavenClearLastUpdatedFileTest {

    @Test
    public void deleMavenLastUpdated() {
        MavenClearLastUpdatedFile.deleMavenLastUpdated("/home/luqili/.m2");
    }
}
