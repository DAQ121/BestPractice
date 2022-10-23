package BlockQueueTest;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
public class TestArrayBlockingQueue {

    public static void main(String[] args) throws InterruptedException {

        log.info("-----测试添加元素的api-----");
//        testAddApi();

        log.info("-----测试删除元素的api-----");
//        testRemoveApi();

        log.info("-----测试元素检查api-----");
        testElementCheck();


        log.info("-----测试线程阻塞-----");
//        testBlocking();


    }

    /**
     * 测试集中添加操作的api
     * 1. add()  队列满了则直接抛出异常  java.lang.IllegalStateException: Queue full
     * 2. offer() 不会抛出异常，而是返回bool值false
     * 3. put()  队列满了，添加元素的线程会被阻塞，当队列不满的时候，添加元素的线程才会被唤醒。
     * 4. offer(element, timeout, timeunit) 添加不进去，线程阻塞几秒，就退出
     */
    private static void testAddApi() throws InterruptedException {
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(10);

        for (int i = 0; i < 11; i++) {
            // 1. add()
            System.out.println(arrayBlockingQueue.add("daq" + i));

            // 2. offer()
            System.out.println(arrayBlockingQueue.offer("kk" + i));

            // 3. put()
            try {
                arrayBlockingQueue.put("daq" + i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            // 4. offer("", 2, ) 超时两秒就退出
            System.out.println(arrayBlockingQueue.offer("daq" + i, 2, TimeUnit.SECONDS));
        }

        System.out.println(arrayBlockingQueue);

    }

    /**
     * 测试删除元素的API
     * 1. remove() 返回队首元素，也可以remove指定元素(没有则返回false)，当队列为空时，抛出异常NoSuchElementException
     * 2. poll() 队列为空时，不抛出异常，返回null
     * 3. take() 返回队首元素，队列空时，队列阻塞，当其他线程有元素添加进来的时候，当前被阻塞的线程才会被唤醒。
     * 4. poll(timeout, timeunit) 线程会被阻塞几秒
     */
    private static void testRemoveApi() throws InterruptedException {
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(10);
        arrayBlockingQueue.add("daq");
        arrayBlockingQueue.add("kk");

        // 1. remove
        System.out.println(arrayBlockingQueue.remove());
        System.out.println(arrayBlockingQueue.remove("kk"));
        System.out.println(arrayBlockingQueue.remove("aaa"));

        // 2. poll()
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());
        System.out.println(arrayBlockingQueue.poll());

        // 3. take，被阻塞，当队列中有新元素进来的时候，才会被唤醒
        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());
        new Thread(() -> {
            try {
                System.out.println(arrayBlockingQueue.take());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        Thread.sleep(3000);

        new Thread(() -> {
            arrayBlockingQueue.add("llll");
        }).start();

        // 4. 超时等待三秒没有结果则返回，不会抛出异常
        arrayBlockingQueue.poll(3, TimeUnit.SECONDS);

    }

    /**
     * 测试元素检查api
     * 1. element() 队列元素为空的情况下,会抛出NoSuchElementException异常
     * 2. peek() 只会返回 null。
     */
    private static void testElementCheck() throws InterruptedException {
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue(3);

        // 1. element()
        System.out.println("arrayBlockingQueue.element() = " + arrayBlockingQueue.element());

        // 2. peek
        System.out.println(arrayBlockingQueue.peek());

    }

    /**
     * 测试多线程阻塞队列，当队列中满时，添加元素的线程会被阻塞
     */
    public static void testBlocking() throws InterruptedException {
        // 指定队列的长度为10
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(10);

        // 运行15个线程，此时会有5个线程处于等待中
        for (int i = 0; i < 15; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        arrayBlockingQueue.put("daq" + new Random().nextInt(3));
                        log.info(Thread.currentThread().getName() + "put one element into arrayBlockingQueue");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }

        // 等待十秒
        Thread.sleep(10000);

        // 释放queue中的一个元素,会发现有个线程从wait状态变为运行态,等待线程进入运行状态遵循先进先出的原则。
        System.out.println(arrayBlockingQueue.poll());

    }
}
