package org.locngo.tutorial;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author xuanloc0511@gmail.com
 */
public class VirtualThread {
    public final static void main(String[] args) throws InterruptedException {
        configurePlatformThread();
        int totalCommands = Integer.parseInt(System.getProperty("numberOfCommand", "100"));
        Thread.sleep(10000);
        final long started = System.currentTimeMillis();
        List<Command> commands = new ArrayList<>();
        for (int i = 0; i < totalCommands; i++) {
            commands.add(new Command().setId(i));
        }
        final CountDownLatch cdl = new CountDownLatch(totalCommands);
        for (Command cmd : commands) {
            Thread.ofVirtual().start(new Handler().setCmd(cmd).setEndHandler(cdl::countDown));
        }
        cdl.await(300, TimeUnit.SECONDS);
        System.out.println("complete example after " + (System.currentTimeMillis() - started) + " ms");
    }

    public final static void configurePlatformThread() {
        System.setProperty("jdk.virtualThreadScheduler.parallelism", "4");
        System.setProperty("jdk.virtualThreadScheduler.maxPoolSize", "8");
        System.setProperty("jdk.virtualThreadScheduler.minRunnable", "4");
    }
}
