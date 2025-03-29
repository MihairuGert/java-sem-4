package task4.threadpool;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ThreadPool {
    private final BlockingQueue<Runnable> taskQueue;
    private final ArrayList<ThreadPoolThread> threads;

    private void createPooledThreads(int threadCount) {
        for (int i = 0; i < threadCount; i++) {
            ThreadPoolThread thread = new ThreadPoolThread(taskQueue);
            threads.add(thread);
            thread.start();
        }
    }

    public ThreadPool(int threadCount, int taskQueueSize) {
        taskQueue = new ArrayBlockingQueue<>(taskQueueSize);
        threads = new ArrayList<>(threadCount);
        createPooledThreads(threadCount);
    }

    public void addTask(Runnable task) {
        try {
            taskQueue.put(task);
        } catch (IllegalStateException | InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }

    public void stop() {
        for (ThreadPoolThread threadPoolThread : threads) {
            threadPoolThread.stopThread();
        }
    }
}
