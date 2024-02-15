package org.example;

public class ConditionSyncEx {

    private boolean isAvailable = false;

    public synchronized void produce() throws InterruptedException {
        while (isAvailable) {
            wait();
        }

        System.out.println("생산됨");
        isAvailable = true;
        notify();
    }

    public synchronized void consume() throws InterruptedException {
        while (!isAvailable) {
            wait();
        }

        System.out.println("소비됨");
        isAvailable = false;
        notify();
    }

    public static void main(String[] args) {
        ConditionSyncEx conditionSyncEx = new ConditionSyncEx();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    conditionSyncEx.produce();
                } catch (InterruptedException e) {

                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                try {
                    conditionSyncEx.consume();
                } catch (InterruptedException e) {


                }
            }
        }).start();
    }


}
