package org.computelab.config;

/**
 * A configuration entry is a key-value pair whose key is a string
 * and value is of a specific type.
 *
 * @param <T> The type of the config entry's value.
 */
public interface ConfigEntry<T> {

    String getKey();

    T getValue();
}
