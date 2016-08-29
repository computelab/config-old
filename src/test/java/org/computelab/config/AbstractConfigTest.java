package org.computelab.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

public class AbstractConfigTest {

    private Config testConfig;

    @Before
    public void before() {

        final Map<String, String> configMap = new HashMap<String, String>();
        configMap.put("key", "value");
        configMap.put("true1", "true");
        configMap.put("true2", "True");
        configMap.put("false1", "false");
        configMap.put("false2", "yes");
        configMap.put("int", "-3");
        configMap.put("long", "32819383819583922");
        configMap.put("long", "56789231008769320");
        configMap.put("list1", "a, b,c");
        configMap.put("list2", "a, b,c,");
        configMap.put("list3", "a");
        configMap.put("list4", "a, ");
        configMap.put("list5", "");

        testConfig = new AbstractConfig() {
            @Override
            public AppStack stack() {
                return AppStack.DEVELOP;
            }
            @Override
            public Collection<String> keys() {
                return configMap.keySet();
            }
            @Override
            public String getValue(String key) {
                return configMap.get(key);
            }
        };
    }

    @Test
    public void test() {

        assertEquals(AppStack.DEVELOP, testConfig.stack());

        Collection<String> keys = testConfig.keys();
        assertNotNull(keys);
        assertEquals("Duplicate keys 'long' should be deduped.", 12, keys.size());
        assertTrue(keys.contains("key"));
        assertTrue(keys.contains("true1"));
        assertTrue(keys.contains("true2"));
        assertTrue(keys.contains("false1"));
        assertTrue(keys.contains("false2"));
        assertTrue(keys.contains("int"));
        assertTrue(keys.contains("long"));

        assertEquals("value", testConfig.get("key"));
        assertNotEquals("Value", testConfig.get("key"));

        assertTrue(testConfig.getBoolean("true1"));
        assertTrue(testConfig.getBoolean("true2"));
        assertFalse(testConfig.getBoolean("false1"));
        assertFalse(testConfig.getBoolean("false2"));

        assertEquals("-3", testConfig.get("int"));
        assertEquals(-3, testConfig.getInt("int"));
        assertEquals("This one should overwrite the previous one of the same key.",
                56789231008769320L, testConfig.getLong("long"));
        assertNotEquals("This one should be overwritten by the one of the same key.",
                32819383819583922L, testConfig.getLong("long"));

        List<String> list = testConfig.getList("list1", ",\\s*");
        assertNotNull(list);
        assertEquals(3, list.size());
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
        assertEquals("c", list.get(2));
        list = testConfig.getList("list2", ",\\s*");
        assertNotNull(list);
        assertEquals(3, list.size());
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
        assertEquals("c", list.get(2));
        list = testConfig.getList("list3", ",\\s*");
        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals("a", list.get(0));
        list = testConfig.getList("list4", ",\\s*");
        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals("a", list.get(0));
        list = testConfig.getList("list5", ",\\s*");
        assertNotNull(list);
        assertEquals(0, list.size());
    }

    @Test(expected=NullPointerException.class)
    public void testNonNull() {
        testConfig.get(null);
    }
}
