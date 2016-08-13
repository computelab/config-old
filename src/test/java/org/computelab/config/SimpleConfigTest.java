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
        assertNotNull(config.getKeys());
        assertEquals(0, config.getKeys().size());
        assertNull(config.get("test.key"));
        assertEquals(AppStack.LOCAL, config.getStack());
    }

    @Test
    public void test() {
        SimpleConfig config = new SimpleConfig();
        String key = "test.key";
        config.put(key, "true");
        assertEquals("true", config.get(key));
        assertTrue(config.getBoolean(key));
        config.put(ConfigKey.STACK, "develop");
        assertEquals(AppStack.DEVELOP, config.getStack());
        assertEquals(2, config.getKeys().size());
    }
}
