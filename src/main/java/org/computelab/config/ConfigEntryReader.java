package org.computelab.config;

/**
 * Reads a config entry whose value is of a specific type.
 *
 * @param <T> The type of the config entry's value.
 */
public interface ConfigEntryReader<T> {

    /**
     * Reads a config entry. If the entry does not exist for specified key,
     * null is returned.
     */
    ConfigEntry<T> get(String key);
}
