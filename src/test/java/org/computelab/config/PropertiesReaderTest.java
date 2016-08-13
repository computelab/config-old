package org.computelab.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Properties;

import org.junit.Test;

public class PropertiesReaderTest {

    @Test
    public void test() {
        Properties properties = new Properties();
        ConfigEntryReader<String> reader = new PropertiesReader(properties);
        assertNull(reader.get("a"));
        properties.setProperty("a", "b");
        assertNull(reader.get("a"));
        reader = new PropertiesReader(properties);
        assertEquals("a", reader.get("a").getKey());
        assertEquals("b", reader.get("a").getValue());
    }
}
