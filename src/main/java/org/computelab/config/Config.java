package org.computelab.config;

import java.util.Collection;
import java.util.List;

public interface Config {

    AppStack getStack();

    Collection<String> getKeys();

    String get(String key);

    List<String> getList(String key, String delimiter);

    boolean getBoolean(String key);

    int getInt(String key);

    long getLong(String key);
}
