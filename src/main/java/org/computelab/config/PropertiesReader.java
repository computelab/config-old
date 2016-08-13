package org.computelab.config;

import java.util.Properties;

final class PropertiesReader implements ConfigEntryReader<String> {

    private final Properties properties;

    PropertiesReader(Properties properties) {
        this.properties = new Properties();
        for (String key : properties.stringPropertyNames()) {
            this.properties.setProperty(key, properties.getProperty(key));
        }
    }

    @Override
    public ConfigEntry<String> get(final String key) {
        final String value = properties.getProperty(key);
        if (value == null) {
            return null;
        }
        return new ConfigEntry<String>() {
            @Override
            public String getKey() {
                return key;
            }
            @Override
            public String getValue() {
                return value;
            }
        };
    }
}
