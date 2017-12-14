package org.computelab.config;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

final class ConfigFactory {

    private ConfigFactory() {
    }

    /**
     * Creates a config using the specified reader over the collection of keys. Keys that do not read
     * any config entry will not be included in the created config. 
     */
    static Config create(final ConfigEntryReader<String> reader, final Collection<String> keys) {
        final SimpleConfig simpleConfig = new SimpleConfig();
        Collection<String> filteredKeys = Collections2.filter(keys, new Predicate<String>() {
            @Override
            public boolean apply(String key) {
                return reader.get(key) != null;
            }
        });
        for (final String key : filteredKeys) {
            simpleConfig.put(key, reader.get(key).getValue());
        }
        return simpleConfig;
    }

    /**
     * Merges additional config on top of the source config.
     * @return
     */
    static Config create(final Config sourceConfig, final Config additionalConfig) {
        final SimpleConfig simpleConfig = new SimpleConfig();
        for (String key : sourceConfig.keys()) {
            simpleConfig.put(key, sourceConfig.get(key));
        }
        for (String key : additionalConfig.keys()) {
            simpleConfig.put(key, additionalConfig.get(key));
        }
        return simpleConfig;
    }

    /**
     * Merges values read by the reader onto the source config. Values on the same key will be overwritten.
     * New keys will be added to the config if their values exist.
     */
    static Config create(Config sourceConfig, ConfigEntryReader<String> reader, Collection<String> keys) {
        final Set<String> combinedKeys = new HashSet<String>(keys);
        final Collection<String> moreKeys = sourceConfig.keys();
        for (String key : moreKeys) {
            combinedKeys.add(key);
        }
        return create(sourceConfig, create(reader, combinedKeys));
    }
}
