package org.example;

public class SynchronizedEx {

    private int instanceCnt = 0;
    private static int staticCnt = 0;

    public synchronized void instanceMethod() { // #1 이것과
        instanceCnt++;
        staticCnt++;
        System.out.println("인스턴스 메서드 동기화: " + instanceCnt);
    }

    public static synchronized void staticMethod() { // #2 그리고 이것과
        staticCnt++;
        System.out.println("정적 메서드 동기화: " + staticCnt);
    }

    public void instanceBlock() { // #1 이거는 같은 모니터를 갖는다.

        synchronized (this) {
            instanceCnt++;
            staticCnt++; // 원자성 보장이 안됨, 인스턴스와 클래스는 모니터가 다르다.
            System.out.println("인스턴스 블록 동기화: " + instanceCnt);
        }

    }

    public static void staticBlock() { // #2 이것이 같은 모니터를 갖는다.
        synchronized (SynchronizedEx.class) {
            staticCnt++;
            System.out.println("정적 블록 동기화: " + staticCnt);
        }
    }

    public static void main(String[] args) {

        SynchronizedEx ex = new SynchronizedEx();
        new Thread(ex::instanceMethod).start();
        new Thread(ex::instanceBlock).start();
        new Thread(SynchronizedEx::staticMethod).start();
        new Thread(SynchronizedEx::staticBlock).start();

    }

}
