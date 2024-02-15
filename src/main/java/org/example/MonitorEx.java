package org.example;

public class MonitorEx {

    private int counter = 0;

    public synchronized void increment() {
        counter++;
        System.out.println("스레드: " + Thread.currentThread().getName() + " 카운터 값: " + counter);
    }

    public static void main(String[] args) throws InterruptedException {

        MonitorEx ex = new MonitorEx();
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                ex.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                ex.increment();
            }
        });

        thread.start();
        thread2.start();
        thread.join();
        thread2.join();
    }
}
