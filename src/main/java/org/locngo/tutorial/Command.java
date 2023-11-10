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
public class Command {
    private final long started = System.currentTimeMillis();
    private int id;

    public int getId() {
        return id;
    }

    public Command setId(int id) {
        this.id = id;
        return this;
    }

    public long getStarted() {
        return started;
    }

    public void run() throws InterruptedException {
        Thread.sleep(1000);
    }
}
