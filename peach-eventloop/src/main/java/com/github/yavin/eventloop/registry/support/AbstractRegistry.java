/*
 * Copyright (c) 2017, TP-Link Co.,Ltd.
 * Author:  taoyang <taoyang@tp-link.com.cn>
 * Created: 2017-03-19
 */
package com.github.yavin.eventloop.registry.support;

import com.tplink.cloud.connector.common.extension.addon.Addon;
import com.tplink.cloud.connector.common.extension.registry.Registry;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class AbstractRegistry<T> implements Registry<T> {

    private final ConcurrentMap<String, AddonHolder> addons = new ConcurrentHashMap<>();

    @Override
    public T get(String addonName) {
        AddonHolder holder = addons.get(addonName);
        return holder == null ? null : holder.addon;
    }

    @Override
    public T register(T addon) {
        Objects.requireNonNull(addon);

        AddonHolder newHolder = resolveAddonName(addon);
        AddonHolder oldHolder = addons.put(newHolder.name, newHolder);
        return oldHolder == null ? null : oldHolder.addon;
    }

    @Override
    public T register(String name, T addon) {
        AddonHolder newHolder = new AddonHolder(name, addon);
        AddonHolder oldHolder = addons.put(newHolder.name, newHolder);
        return oldHolder == null ? null : oldHolder.addon;
    }

    @Override
    public T registerIfAbsent(T addon) {
        Objects.requireNonNull(addon);

        AddonHolder newHolder = resolveAddonName(addon);
        AddonHolder oldHolder = addons.putIfAbsent(newHolder.name, newHolder);
        return oldHolder == null ? null : oldHolder.addon;
    }

    @Override
    public void unregister(T instance) {

    }

    private AddonHolder resolveAddonName(T addon) {
        Class<?> addonType = addon.getClass();
        if (addonType.isAnnotationPresent(Addon.class)) {
            Addon $addon = addonType.getAnnotation(Addon.class);
            return new AddonHolder($addon.value(), addon);
        }

        return new AddonHolder(addonType.getCanonicalName(), addon);
    }

    private class AddonHolder {
        final T addon;
        final String name;

        AddonHolder(String name, T addon) {
            this.addon = addon;
            this.name = name;
        }
    }

}
