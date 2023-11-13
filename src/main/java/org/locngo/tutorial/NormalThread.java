package org.locngo.tutorial;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author xuanloc0511@gmail.com
 */
public class NormalThread {

    public final static void main(String[] args) throws InterruptedException {
        int totalCommands = Integer.parseInt(System.getProperty("numberOfCommand", "100"));
        Thread.sleep(10000);
        final long started = System.currentTimeMillis();
        List<Command> commands = new ArrayList<>();
        for (int i = 0; i < totalCommands; i++) {
            commands.add(new Command().setId(i));
        }
        final CountDownLatch cdl = new CountDownLatch(totalCommands);
        for (Command cmd : commands) {
            Thread.ofPlatform().start(new Handler().setCmd(cmd).setEndHandler(cdl::countDown));
        }
        cdl.await(300, TimeUnit.SECONDS);
        System.out.println("complete example after " + (System.currentTimeMillis() - started) + " ms");
    }
}
