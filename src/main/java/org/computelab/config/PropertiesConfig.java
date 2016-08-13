package org.computelab.config;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Properties;
import java.util.Set;

public final class PropertiesConfig extends AbstractLocalStackConfig {

    private final Config config;

    public PropertiesConfig(Properties properties) {
        this(new SimpleConfig(), properties);
    }

    public PropertiesConfig(Config source) {
        this(source, new Properties());
    }

    public PropertiesConfig(Config sourceConfig, Properties properties) {
        checkNotNull(sourceConfig);
        checkNotNull(properties);
        ConfigEntryReader<String> reader = new PropertiesReader(properties);
        Set<String> keys = properties.stringPropertyNames();
        config = ConfigFactory.create(sourceConfig, reader, keys);
    }

    @Override
    public Collection<String> getKeys() {
        return config.getKeys();
    }

    @Override
    public String getValue(String key) {
        return config.get(key);
    }
}
