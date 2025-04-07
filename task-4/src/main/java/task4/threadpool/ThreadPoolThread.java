package task4.threadpool;

import java.util.concurrent.BlockingQueue;

public class ThreadPoolThread extends Thread{
    private final BlockingQueue<Runnable> tasks;
    private boolean isStopped = false;

    ThreadPoolThread(BlockingQueue<Runnable> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void run() {
        while (true) {
            if (isStopped) {
                return;
            }
            try {
                Runnable task = tasks.take();
                task.run();
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
                this.interrupt();
            }
        }
    }

    public void stopThread() {
        isStopped = true;
    }
}
