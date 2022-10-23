package BlockQueueTest.DelayQueue;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.DelayQueue;

@Slf4j
public class TestDelayQueue {

    /**
     * 程序启动入口方法
     * @param args args
     */
    public static void main(String[] args) throws Exception {
        // 创建延迟消息队列
        DelayQueue<DelayMessage> delayQueue = new DelayQueue<>();
        // 创建并启动延迟队列的消费者线程
        new Thread(new DelayQueueConsumer(delayQueue)).start();
        // 执行测试样例1
        // test1(delayQueue);
        // 执行测试样例2
        // test2(delayQueue);
        // 执行测试样例3
        test3(delayQueue);
    }

    /**
     * 测试用例1：生成5条TTL时间依次增大的延迟消息：1秒，2秒，3秒，4秒，5秒
     * @param delayQueue 延迟队列
     */
    private static void test1(DelayQueue<DelayMessage> delayQueue) {
        for (int i = 1; i <= 5; i++) {
            DelayMessage delayMessage = new DelayMessage(String.valueOf(i), i*1000L);
            log.info("Producer publish message: {}", String.valueOf(i));
            delayQueue.offer(delayMessage);
        }
    }

    /**
     * 测试用例2：生成5条TTL时间依次减小的延迟消息：5秒，4秒，3秒，2秒，1秒
     * @param delayQueue 延迟队列
     */
    private static void test2(DelayQueue<DelayMessage> delayQueue) {
        for (int i = 5; i > 0; i--) {
            String message = String.valueOf(i);
            DelayMessage delayMessage = new DelayMessage(message, i*1000L);
            log.info("Producer publish message: {}", message);
            delayQueue.offer(delayMessage);
        }
    }

    /**
     * 测试用例3：生成5个延迟时间随机的延迟消息
     * @param delayQueue 延迟队列
     */
    private static void test3(DelayQueue<DelayMessage> delayQueue) {
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            // 生成1~10的随机数：作为1秒-10秒的延迟时间
            int ttl = 1 + random.nextInt(10);
            String message = String.valueOf(ttl);
            DelayMessage delayMessage = new DelayMessage(message, ttl*1000L);
            log.info("Producer publish message: {}", message);
            delayQueue.offer(delayMessage);
        }
    }

}
