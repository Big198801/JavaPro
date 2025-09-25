package ru.big198801;

import java.util.LinkedList;
import java.util.List;


public class CustomThreadPoolExecutor {
    private final LinkedList<Runnable> workQueue = new LinkedList<>();
    private final List<Thread> threads = new LinkedList<>();
    private final Object monitor = new Object();
    private volatile boolean isShutdown;


    public CustomThreadPoolExecutor(int poolSize) {
        if (poolSize < 0)
            throw new IllegalArgumentException("poolSize must be > 0");
        for (int i = 0; i < poolSize; i++) {
            Thread thread = new Thread(new MyCustomRunnable());
            thread.start();
            threads.add(thread);
            System.out.printf("Thread %s is running\n", thread.getName());
        }
    }

    public void execute(Runnable work) {
        if (!isShutdown) {
            synchronized (monitor) {
                workQueue.add(work);
                System.out.printf("New task added. %s tasks in queue\n", workQueue.size());
                monitor.notifyAll();
            }
        } else
            throw new IllegalStateException("ThreadPool is shutdown");
    }


    private class MyCustomRunnable implements Runnable {

        @Override
        public void run() {

        }
    }
}

