
package com.github.yavin.eventloop;

import java.util.concurrent.ScheduledExecutorService;

public interface EventExecutorGroup extends ScheduledExecutorService, Iterable<EventExecutor> {

    EventExecutor next();
}
