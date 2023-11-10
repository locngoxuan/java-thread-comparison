package org.locngo.tutorial;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

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
            String msg = String.format("Handle command id=%d successful after %d ms at thread (name=%s, id=%d)",
                                       cmd.getId(), System.currentTimeMillis() - cmd.getStarted(),
                                       getThreadName(), getThreadId());
            System.out.println(msg);
            if (endHandler != null) {
                endHandler.handle();
            }
        } catch (InterruptedException e) {
        }
    }
}
