package co.bobrocket.concurrentmouse.mouse;

import co.bobrocket.concurrentmouse.mouse.data.IMouseTarget;
import org.osbot.rs07.script.Script;

import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Bobrocket on 03/01/2016.
 */
public class MouseScheduler {
    private Script context;

    private Thread schedulingThread;
    private Queue<IMouseTarget> targetQueue = new PriorityBlockingQueue<>(4, (one, two) -> Integer.compare(two.getPriority().getOrdinal(), one.getPriority().getOrdinal()));

    public MouseScheduler(Script parent) {
        this.context = parent;
        schedulingThread = new Thread(() -> loop());
    }

    /**
     * Schedule a new {@link IMouseTarget}
     *
     * @param target - The target to schedule
     * */
    public void schedule(IMouseTarget target) {
        targetQueue.offer(target);
    }

    private void loop() {
        while (true) {
            try {
                IMouseTarget currentTarget = targetQueue.poll();
                if (currentTarget != null) {
                    while (!currentTarget.run(context)) Thread.sleep(50);
                }
                Thread.sleep(20);
            }
            catch (Exception ex) { }
        }
    }

    /**
     * Start the scheduling thread, which is what allows concurrent, prioritised mouse movements
     * */
    public void start() {
        schedulingThread.start();
    }

    /**
     * Stop the scheduling thread (IMPERATIVE)
     * */
    public void stop() {
        schedulingThread.stop();
    }
}
