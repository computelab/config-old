package org.computelab.config;

final class SystemPropertyReader implements ConfigEntryReader<String> {

    @Override
    public ConfigEntry<String> get(final String key) {
        final String value = System.getProperty(key);
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
