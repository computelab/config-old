package org.computelab.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

public class AppStackConfigTest {

    @Test
    public void test() throws IOException {
        try (InputStream inputStream = getClass().getResourceAsStream("/test.config")) {
            Properties properties = new Properties();
            properties.load(inputStream);
            Config config = new AppStackConfig(AppStack.DEVELOP, new PropertiesConfig(properties));
            assertEquals("valueA", config.get("key.a"));
            assertEquals("developValueB", config.get("key.b"));
            assertEquals("developValueC", config.get("key.c"));
            assertNull(config.get("key.d"));
        }
    }
}
