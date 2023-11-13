package org.locngo.tutorial;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuanloc0511@gmail.com
 */
@Setter
@Getter
@Accessors(chain = true)
public class Handler implements Runnable {

    private EndHandler endHandler;
    private Command cmd;

    private String getThreadName() {
        return Thread.currentThread().getName();
    }

    private long getThreadId() {
        return Thread.currentThread().threadId();
    }

    /**
     * Runs this operation.
     */
    @Override
    public void run() {
        try {
            cmd.run();
            long duration = System.currentTimeMillis() - cmd.getStarted();
            String msg = String.format("Handle command id=%d successful after %d ms at thread (name=%s, id=%d)",
                                       cmd.getId(), duration,
                                       getThreadName(), getThreadId());
            System.out.println(msg);
            if (endHandler != null) {
                endHandler.handle();
            }
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
