package org.computelab.config;

/**
 * Defaults to the local stack if the stack is not specified.
 */
abstract class AbstractLocalStackConfig extends AbstractConfig {

    @Override
    public final AppStack getStack() {
        final String value = getValue(ConfigKey.STACK);
        if (value == null) {
            return AppStack.LOCAL;
        }
        return AppStack.valueOf(value.toUpperCase());
    }
}
