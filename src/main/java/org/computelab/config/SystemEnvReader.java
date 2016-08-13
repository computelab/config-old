package org.computelab.config;

final class SystemEnvReader implements ConfigEntryReader<String> {

    @Override
    public ConfigEntry<String> get(final String key) {
        final String value = System.getenv(getEnvKey(key));
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

    String getEnvKey(final String key) {
        return key.toUpperCase().replace(ConfigKey.DELIMITER, "_");
    }
}
