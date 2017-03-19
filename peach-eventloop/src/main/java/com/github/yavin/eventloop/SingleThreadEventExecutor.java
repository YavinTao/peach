
package com.github.yavin.eventloop;

public class SingleThreadEventExecutor extends AbstractEventExecutor {

    protected SingleThreadEventExecutor(EventExecutorGroup parent) {
        super(parent);
    }

    public void shutdown() {

    }

    public boolean inEventLoop(Thread thread) {
        return false;
    }

    public void execute(Runnable command) {

    }
}
