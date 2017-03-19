
package com.github.yavin.eventloop;

import java.util.Queue;

public abstract class AbstractScheduledEventExecutor extends AbstractEventExecutor {

    //Queue<ScheduledFutureTask<?>> scheduledTaskQueue;

    protected AbstractScheduledEventExecutor(EventExecutorGroup parent) {
        super(parent);
    }
}
