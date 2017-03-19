
package com.github.yavin.eventloop.registry.support;

import com.github.yavin.eventloop.registry.Registry;
import com.github.yavin.eventloop.registry.RegistryException;
import com.github.yavin.eventloop.registry.RegistryFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public abstract class AbstractRegistryFactory implements RegistryFactory {

    private static ConcurrentHashMap<String, Registry> registries = new ConcurrentHashMap<>();

    private static final ReentrantLock lock = new ReentrantLock();

    @Override
    public Registry registry(String key) {
        try {
            lock.lock();
            Registry registry = registries.get(key);
            if (registry != null) {
                return registry;
            }
            registry = createRegistry(key);
            if (registry == null) {
                throw new RegistryException("Create registry false for url:" + key);
            }
            registries.put(key, registry);
            return registry;
        } catch (Exception e) {
            throw new RegistryException("Create registry false for url:" + key, e);

        } finally {
            lock.unlock();
        }
    }


    protected abstract Registry createRegistry(String key);


}
