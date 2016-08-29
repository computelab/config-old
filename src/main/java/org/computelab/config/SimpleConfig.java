package org.computelab.config;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

final class SimpleConfig extends AbstractLocalStackConfig {

    private final Map<String, String> configMap;

    SimpleConfig() {
        configMap = new HashMap<String, String>();
    }

    @Override
    public Collection<String> keys() {
        return Collections.unmodifiableSet(configMap.keySet());
    }

    @Override
    public String getValue(String key) {
        return configMap.get(key);
    }

    void put(String key, String value) {
        checkNotNull(key);
        checkNotNull(value);
        configMap.put(key, value);
    }
}
