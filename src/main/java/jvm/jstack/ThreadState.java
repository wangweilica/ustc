package jvm.jstack;

public class ThreadState {

    public static void main(String[] args) {
        blocked();
    }

    public static void blocked() {
        final Object lock = new Object();
        new Thread("work-0") {
            public void run() {
                synchronized (lock) {
                    System.out.println("i got lock, but don't release");
                    try {
                        Thread.sleep(1000L * 1000);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }.start();

        try { Thread.sleep(100); } catch (InterruptedException e) {}

        synchronized (lock) {
            try {
                System.out.println("main thread got the lock");
                Thread.sleep(30 * 1000);
            } catch (InterruptedException e) {
            }
        }
    }
}
