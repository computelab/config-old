package org.computelab.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SystemPropertyConfigTest {

    private String systemKey;
    private String systemValue;

    @Before
    public void before() {
        systemKey = getClass().getCanonicalName();
        systemValue = systemKey + ".value";
        System.setProperty(systemKey, systemValue);
    }

    @After
    public void after() {
        System.clearProperty(systemKey);
    }

    @Test
    public void testSourceConfigOnly() {
        final SimpleConfig simpleConfig = new SimpleConfig();
        final String nonSystemKey = "some.other.key";
        final String nonSystemValue = "some.other.value";
        simpleConfig.put(nonSystemKey, nonSystemValue);
        final Config config = new SystemPropertyConfig(simpleConfig);
        assertNotNull(config.getKeys());
        assertEquals(1, config.getKeys().size());
        assertEquals(nonSystemValue, config.get(nonSystemKey));
        assertNull("Without explicit keys, system properties should NOT be added to the config.",
                config.get(systemKey));
    }

    @Test
    public void testSourceConfigWithSystemProperties() {
        final SimpleConfig simpleConfig = new SimpleConfig();
        final String nonSystemKey = "some.other.key";
        final String nonSystemValue = "some.other.value";
        simpleConfig.put(nonSystemKey, nonSystemValue);
        simpleConfig.put(systemKey, "some.other.system.value");
        final Config config = new SystemPropertyConfig(simpleConfig);
        assertNotNull(config.getKeys());
        assertEquals(2, config.getKeys().size());
        assertEquals("Source config on a different key should be kept.",
                nonSystemValue, config.get(nonSystemKey));
        assertEquals("Source config on the same key should be overwritten by the system property.",
                systemValue, config.get(systemKey));
    }

    @Test
    public void testSystemPropertiesNonSystemKey() {
        final String nonSystemKey = "some.other.key";
        final Config config = new SystemPropertyConfig(
                Arrays.<String>asList(new String[]{nonSystemKey}));
        assertNotNull(config.getKeys());
        assertEquals(0, config.getKeys().size());
        assertNull("Non-system property should NOT be added.", config.get(nonSystemKey));
        assertNull("Without explicit keys, system properties should NOT be added to the config.",
                config.get(systemKey));
    }

    @Test
    public void testSystemPropertiesOnly() {
        final String nonSystemKey = "some.other.key";
        final Config config = new SystemPropertyConfig(
                Arrays.<String>asList(new String[]{nonSystemKey, systemKey}));
        assertNotNull(config.getKeys());
        assertEquals(1, config.getKeys().size());
        assertNull("Non-system property should NOT be added.", config.get(nonSystemKey));
        assertEquals(systemValue, config.get(systemKey));
    }
}
