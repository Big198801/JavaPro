package ru.big198801;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        CustomThreadPoolExecutor customThreadPoolExecutor = new CustomThreadPoolExecutor(4);
        for (int i = 0; i < 10; i++) {
            customThreadPoolExecutor.execute(() -> {
                System.out.printf("Task is work on %s \n", Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        customThreadPoolExecutor.shutdown();
    }

}