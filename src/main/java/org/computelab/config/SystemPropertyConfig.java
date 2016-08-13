package org.computelab.config;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;

public final class SystemPropertyConfig extends AbstractLocalStackConfig {

    private final Config config;

    public SystemPropertyConfig(Collection<String> keys) {
        this(new SimpleConfig(), keys);
    }

    public SystemPropertyConfig(Config source) {
        this(source, Collections.<String>emptyList());
    }

    public SystemPropertyConfig(Config sourceConfig, Collection<String> keys) {
        checkNotNull(sourceConfig);
        checkNotNull(keys);
        config = ConfigFactory.create(sourceConfig, new SystemPropertyReader(), keys);
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
