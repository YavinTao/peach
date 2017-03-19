
package com.github.yavin.eventloop.registry;


public interface Registry<T> {

    /**
     * @param addonName the name of addon.
     * @return the addon named {@code addonName}.
     */
    T get(String addonName);

    /**
     * Associates the specified {@code addon} with the specified {@link Addon#value()}
     * in this loader.  If the loader previously contained a addon associated with
     * the {@link Addon#value()}, the old addon is replaced by the specified addon.
     *
     * @param addon to be associated with this loader.
     * @return the old addon.
     */
    T register(T addon);


    /**
     * Associates the specified {@code addon} with the specified name
     *
     * @param name addon name
     * @param addon to be associated with this loader.
     * @return the old addon.
     */
    T register(String name, T addon);

    /**
     * If the specified {@link Addon#value()} is not already associated
     * with a addon, associate it with the given value.
     * This is equivalent to
     * <pre>
     *   T addon = loader.findAddon(addonName);
     *   if (addon != null)
     *       return loader.setAddon(addon);
     *   else
     *       return addon;</pre>
     * except that the action is performed atomically.
     *
     * @param addon to be associated with this loader.
     * @return the old addon if this loader is already associated with an addon.
     * Otherwise, return {@code null}.
     */
    T registerIfAbsent(T addon);

    void unregister(T instance);
}
