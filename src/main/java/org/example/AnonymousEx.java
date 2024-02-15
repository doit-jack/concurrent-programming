package org.example;

public class AnonymousEx {
    public static void main(String[] args) {
        Thread thread = new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": Running");
            }
        };
        thread.start();
    }
}