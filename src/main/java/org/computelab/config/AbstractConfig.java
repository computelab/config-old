package org.computelab.config;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.collect.Collections2;

abstract class AbstractConfig implements Config {

    abstract String getValue(String key);

    @Override
    public final String get(String key) {
        checkNotNull(key);
        return getValue(key);
    }

    @Override
    public final List<String> getList(String key, String delimiter) {
        checkNotNull(key);
        checkNotNull(delimiter);
        String value = get(key);
        String[] splits = value.split(delimiter);
        Collection<String> filtered = Collections2.filter(Arrays.asList(splits),
                new Predicate<String>() {
            @Override
            public boolean apply(String value) {
                return !Strings.isNullOrEmpty(value);
            }
        });
        return Collections.unmodifiableList(new ArrayList<String>(filtered));
    }

    @Override
    public final boolean getBoolean(String key) {
        return getTypedValue(key, Boolean::parseBoolean);
    }

    @Override
    public final int getInt(String key) {
        return getTypedValue(key, Integer::parseInt);
    }

    @Override
    public final long getLong(String key) {
        return getTypedValue(key, Long::parseLong);
    }

    @FunctionalInterface
    private static interface Parse<T> {
        T apply(String value);
    }

    private <T> T getTypedValue(String key, Parse<T> parse) {
        checkNotNull(key);
        final String value = get(key);
        return parse.apply(value);
    }
}
