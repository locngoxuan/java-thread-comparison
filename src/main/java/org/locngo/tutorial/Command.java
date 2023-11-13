package org.locngo.tutorial;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.IOException;

/**
 * @author xuanloc0511@gmail.com
 */
@Setter
@Getter
@Accessors(chain = true)
public class Command {
    private final long started = System.currentTimeMillis();
    private int id;

    public void run() throws InterruptedException, IOException {
        someInternalLogic();
    }


    private void someInternalLogic() throws InterruptedException {
        Thread.sleep(1000);
    }
}
