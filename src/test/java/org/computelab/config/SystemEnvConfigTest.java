package org.computelab.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;

import org.junit.Test;

public class SystemEnvConfigTest {

    @Test
    public void test() {
        Config config = new SystemEnvConfig(Arrays.asList("home"));
        assertEquals(1, config.getKeys().size());
        assertNotNull(config.get("home"));
        assertNull(config.get("some.fake.key"));
    }
}
