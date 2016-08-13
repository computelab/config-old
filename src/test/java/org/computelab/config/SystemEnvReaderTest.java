package org.computelab.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;

public class SystemEnvReaderTest {

    @Test
    public void test() {
        ConfigEntryReader<String> reader = new SystemEnvReader();
        ConfigEntry<String> entry1 = reader.get("home");
        assertNotNull(entry1);
        assertEquals("home", entry1.getKey());
        assertNotNull(entry1.getValue());
        ConfigEntry<String> entry2 = reader.get("HOME");
        assertNotNull(entry2);
        assertEquals("HOME", entry2.getKey());
        assertNotNull(entry2.getValue());
        assertEquals("Lower-case key should be converted to upper case before reading the value",
                entry1.getValue(), entry2.getValue());
    }

    @Test
    public void testNull() {
        ConfigEntryReader<String> reader = new SystemEnvReader();
        ConfigEntry<String> entry = reader.get("some.fake.key.that.does.not.exist");
        assertNull(entry);
    }

    @Test
    public void testGetEnvKey() {
        SystemEnvReader reader = new SystemEnvReader();
        assertEquals("SOME_KEY", reader.getEnvKey("some.key"));
    }
}
