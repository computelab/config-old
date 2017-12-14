package org.computelab.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;

import org.junit.Test;

public class ConfigFactoryTest {

    @Test
    public void testCreateFromReader() {
        ConfigEntryReader<String> reader = new ConfigEntryReader<String>() {
            @Override
            public ConfigEntry<String> get(String key) {
                if ("a".equals(key)) {
                    return new ConfigEntry<String>() {
                        @Override
                        public String getKey() {
                            return "a";
                        }
                        @Override
                        public String getValue() {
                            return "1";
                        }
                    };
                }
                return null;
            }
        };
        Config config = ConfigFactory.create(reader, Arrays.asList("a", "b"));
        assertNotNull(config);
        assertNotNull(config.keys());
        assertEquals(1, config.keys().size());
        assertEquals("1", config.get("a"));
        assertNull(config.get("b"));
    }

    @Test
    public void testCreateByMergingTwoConfigs() {
        SimpleConfig sourceConfig = new SimpleConfig();
        sourceConfig.put("a", "1");
        sourceConfig.put("b", "2");
        SimpleConfig additionalConfig = new SimpleConfig();
        additionalConfig.put("b", "8");
        additionalConfig.put("c", "9");
        Config config = ConfigFactory.create(sourceConfig, additionalConfig);
        assertNotNull(config);
        assertNotNull(config.keys());
        assertEquals(3, config.keys().size());
        assertEquals("1", config.get("a"));
        assertEquals("8", config.get("b"));
        assertEquals("9", config.get("c"));
        assertNull(config.get("d"));
    }

    @Test
    public void testCreateByMergingReaderOverConfig() {
        SimpleConfig sourceConfig = new SimpleConfig();
        sourceConfig.put("a", "1");
        sourceConfig.put("b", "2");
        ConfigEntryReader<String> reader = new ConfigEntryReader<String>() {
            @Override
            public ConfigEntry<String> get(String key) {
                if ("b".equals(key)) {
                    return new ConfigEntry<String>() {
                        @Override
                        public String getKey() {
                            return "b";
                        }
                        @Override
                        public String getValue() {
                            return "8";
                        }
                    };
                }
                if ("c".equals(key)) {
                    return new ConfigEntry<String>() {
                        @Override
                        public String getKey() {
                            return "c";
                        }
                        @Override
                        public String getValue() {
                            return "9";
                        }
                    };
                }
                return null;
            }
        };
        Config config = ConfigFactory.create(sourceConfig, reader, Arrays.asList("a", "b", "c", "d"));
        assertNotNull(config);
        assertNotNull(config.keys());
        assertEquals(3, config.keys().size());
        assertEquals("1", config.get("a"));
        assertEquals("8", config.get("b"));
        assertEquals("9", config.get("c"));
        assertNull(config.get("d"));
    }
}
