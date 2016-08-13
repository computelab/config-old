package org.computelab.config;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;

public final class SystemEnvConfig extends AbstractLocalStackConfig {

    private final Config config;

    public SystemEnvConfig(Collection<String> keys) {
        this(new SimpleConfig(), keys);
    }

    public SystemEnvConfig(Config source) {
        this(source, Collections.<String>emptyList());
    }

    public SystemEnvConfig(Config sourceConfig, Collection<String> keys) {
        checkNotNull(sourceConfig);
        checkNotNull(keys);
        config = ConfigFactory.create(sourceConfig, new SystemEnvReader(), keys);
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
