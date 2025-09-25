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
            System.out.printf("Thread %s is running\n", thread.getName());
            threads.add(thread);
        }
    }

    public void execute(Runnable work) {
        if (!isShutdown) {
            synchronized (monitor) {
                System.out.printf("New task added. %s task(s) in queue\n", workQueue.size() + 1);
                workQueue.add(work);
                monitor.notifyAll();
            }
        } else
            throw new IllegalStateException("ThreadPool is shutdown");
    }

    public void shutdown() {
        synchronized (monitor) {
            isShutdown = true;
            monitor.notifyAll();
        }
    }

    private class MyCustomRunnable implements Runnable {
        @Override
        public void run() {
            while (true) {
                Runnable task = null;
                synchronized (monitor) {
                    if (!workQueue.isEmpty()) {
                        System.out.printf("%d task(s) in queue. Thread %s is running task\n", workQueue.size(),
                                          Thread.currentThread().getName());
                        task = workQueue.removeFirst();
                    } else {
                        if (isShutdown) {
                            System.out.printf("Thread %s is shutdown\n", Thread.currentThread().getName());
                            return;
                        }
                        System.out.printf("Queue is empty, %s is waiting for task\n", Thread.currentThread().getName());
                        try {
                            monitor.wait();
                        } catch (InterruptedException e) {
                        }
                    }
                }
                if (task != null) {
                    task.run();
                    System.out.printf("Thread %s is done his work\n", Thread.currentThread().getName());
                }
            }
        }
    }
}


