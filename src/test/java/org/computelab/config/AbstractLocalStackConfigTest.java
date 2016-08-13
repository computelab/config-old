package org.computelab.config;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Test;

public class AbstractLocalStackConfigTest {

    @Test
    public void test() {
        Config testConfig = new AbstractLocalStackConfig() {
            @Override
            public Collection<String> getKeys() {
                return null;
            }
            @Override
            String getValue(String key) {
                return null;
            }
        };
        assertEquals("Default stack is LOCAL.", AppStack.LOCAL, testConfig.getStack());
    }
}
