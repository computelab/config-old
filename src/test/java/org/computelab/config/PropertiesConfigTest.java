package org.computelab.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

public class PropertiesConfigTest {

    @Test
    public void testPropertiesOnly() throws IOException {
        try (InputStream inputStream = getClass().getResourceAsStream("/test.config")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            Config config = new PropertiesConfig(properties);
            assertNotNull(config.keys());
            assertEquals(9, config.keys().size());
            assertEquals("valueB", config.get("key.b"));
            assertEquals(52, config.getLong("key.e"));
            assertTrue(config.getBoolean("key.f"));
        }
    }

    @Test
    public void testSourceConfigWithProperties() throws IOException {
        SimpleConfig sourceConfig = new SimpleConfig();
        sourceConfig.put("source.key", "sourceValue");
        sourceConfig.put("key.b", "sourceValueB");
        try (InputStream inputStream = getClass().getResourceAsStream("/test.config")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            Config config = new PropertiesConfig(sourceConfig, properties);
            assertNotNull(config.keys());
            assertEquals(10, config.keys().size());
            assertEquals("Should be carried over from the source.",
                    "sourceValue", config.get("source.key"));
            assertEquals("Should be overwritten.", "valueB", config.get("key.b"));
            assertEquals(52, config.getLong("key.e"));
            assertTrue(config.getBoolean("key.f"));
        }
    }
}
