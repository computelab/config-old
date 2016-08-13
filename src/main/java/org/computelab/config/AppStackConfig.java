package org.computelab.config;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

/**
 * Keeps only default entries and entries for the specified stack.
 */
public final class AppStackConfig extends AbstractConfig {

    private final AppStack stack;
    private final Config config;

    public AppStackConfig(AppStack stack, Config sourceConfig) {
        checkNotNull(stack);
        checkNotNull(sourceConfig);
        this.stack = stack;
        SimpleConfig simpleConfig = new SimpleConfig();
        config = simpleConfig;
        // First take keys that are not stack-specific
        final Collection<String> keys = sourceConfig.getKeys();
        for (String key : keys) {
            if (!isStackPrefixed(key)) {
                simpleConfig.put(key, sourceConfig.get(key));
            }
        }
        // Then overwrite with keys that are only for this stack
        for (String key : keys) {
            if (isStackPrefixed(key)) {
                AppStack keyStack = getStack(key);
                if (stack.equals(keyStack)) {
                    simpleConfig.put(removeStackPrefix(key),
                            sourceConfig.get(key));
                }
            }
        }
    }

    @Override
    public Collection<String> getKeys() {
        return config.getKeys();
    }

    @Override
    public AppStack getStack() {
        return stack;
    }

    @Override
    String getValue(String key) {
        return config.get(key);
    }

    private boolean isStackPrefixed(String key) {
        final String keyInUpperCase = key.toUpperCase();
        for (AppStack stack : AppStack.values()) {
            String prefix = stack.name().toUpperCase() + ConfigKey.DELIMITER;
            if (keyInUpperCase.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

    private String removeStackPrefix(String key) {
        // Stack name plus the separator
        return key.substring(stack.name().length() + 1);
    }

    private AppStack getStack(String key) {
        String name = key.substring(0, key.indexOf(ConfigKey.DELIMITER));
        return AppStack.valueOf(name.toUpperCase());
    }
}
