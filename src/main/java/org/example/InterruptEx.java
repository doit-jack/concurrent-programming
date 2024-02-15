package org.example;

public class InterruptEx {

    public static void main(String[] args) {

        Thread mainThread = Thread.currentThread();

        Thread thread1 = new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("긴 작업 진행중");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
//                mainThread.interrupt();
                System.out.println("긴 작업 스레드가 인터럽트 되었음");
            }
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("인터럽트 스레드가 2초 후에 긴 작업 스레드를 인터럽트");
            try {
                Thread.sleep(2000);
                thread1.interrupt();
            } catch (InterruptedException e) {
            }
        });

        thread1.start();
        thread2.start();

        try {
            System.out.println("메인 스레드가 긴 작업 스레드의 완료를 기다립니다");
            thread1.join();
            System.out.println("메인 스레드 작업 완료");
        } catch (InterruptedException e) {
            System.out.println("메인 스레드가 인터럽트 되었습니다");
            throw new RuntimeException(e);
        }
    }
}
