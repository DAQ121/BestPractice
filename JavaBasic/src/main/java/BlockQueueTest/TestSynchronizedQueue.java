package BlockQueueTest;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class TestSynchronizedQueue {

    public static void main(String[] args) {

        // 同步阻塞队列，里面只能有一个元素，必须先take出来，才能put进去
        BlockingQueue<String> blockingQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                blockingQueue.put("daq");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "T1").start();

        new Thread(() -> {
            try {
                System.out.println("blockingQueue.take() = " + blockingQueue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "T2").start();

    }
}
