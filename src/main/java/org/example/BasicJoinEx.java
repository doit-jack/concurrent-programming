package org.example;

public class BasicJoinEx {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            try {
                System.out.println("스레드가 3초 동안 동작");
                Thread.sleep(3000);
                System.out.println("쓰레드 동작 완료");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        System.out.println("메인 스레드가 다른 스레드의 완료를 기다립니다");

        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
