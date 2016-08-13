package org.computelab.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SystemPropertyReaderTest {

    private String key = getClass().getCanonicalName();
    private String value = key + ConfigKey.DELIMITER + "value";

    @Before
    public void before() {
        System.setProperty(key, value);
    }

    @After
    public void after() {
        System.clearProperty(getClass().getCanonicalName());
    }

    @Test
    public void test() {
        ConfigEntryReader<String> reader = new SystemPropertyReader();
        ConfigEntry<String> entry = reader.get(key);
        assertNotNull(entry);
        assertEquals(key, entry.getKey());
        assertEquals(value, entry.getValue());
    }

    @Test
    public void testNull() {
        ConfigEntryReader<String> reader = new SystemPropertyReader();
        ConfigEntry<String> entry = reader.get("some.fake.key.that.does.not.exist");
        assertNull(entry);
    }
}
