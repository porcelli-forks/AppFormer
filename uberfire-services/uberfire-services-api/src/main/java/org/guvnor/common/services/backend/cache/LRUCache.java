/*
 * Copyright 2014 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.guvnor.common.services.backend.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.synchronizedMap;
import static org.kie.soup.commons.validation.PortablePreconditions.checkNotNull;

/**
 * A simple LRU cache keyed on Paths
 */
public abstract class LRUCache<K, V> implements Cache<K, V> {

    private static final int MAX_ENTRIES = 20;

    private final Map<K, V> cache = synchronizedMap(new LinkedHashMap<K, V>(MAX_ENTRIES + 1,
                                                                            0.75f,
                                                                            true) {
        public boolean removeEldestEntry(Map.Entry eldest) {
            return size() > MAX_ENTRIES;
        }
    });

    @Override
    public boolean contains(K key) {
        return cache.containsKey(checkNotNull("key", key));
    }

    @Override
    public void remove(K key) {
        cache.remove(checkNotNull("key", key));
    }

    @Override
    public V getEntry(final K key) {
        return cache.get(checkNotNull("key", key));
    }

    @Override
    public void setEntry(final K key,
                         final V value) {
        cache.put(checkNotNull("key", key), checkNotNull("value", value));
    }

    @Override
    public void invalidateCache() {
        cache.clear();
    }

    @Override
    public void invalidateCache(final K key) {
    }

    public Set<K> getKeys() {
        return cache.keySet();
    }
}
