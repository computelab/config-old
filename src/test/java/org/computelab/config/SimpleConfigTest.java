package org.computelab.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SimpleConfigTest {

    @Test
    public void testEmpty() {
        Config config = new SimpleConfig();
        assertNotNull(config.keys());
        assertEquals(0, config.keys().size());
        assertNull(config.get("test.key"));
        assertEquals(AppStack.LOCAL, config.stack());
    }

    @Test
    public void test() {
        SimpleConfig config = new SimpleConfig();
        String key = "test.key";
        config.put(key, "true");
        assertEquals("true", config.get(key));
        assertTrue(config.getBoolean(key));
        config.put(ConfigKey.STACK, "develop");
        assertEquals(AppStack.DEVELOP, config.stack());
        assertEquals(2, config.keys().size());
    }
}
