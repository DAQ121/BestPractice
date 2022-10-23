package BlockQueueTest;

import java.util.Objects;
import java.util.Random;
import java.util.concurrent.*;

public class TestBlockQueue {

    public static void main(String[] args) throws InterruptedException {
        //将每个用户访问百度服务的请求参数，存入阻塞队列BlockingQueue中
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        for (int i = 0; i < 1000; i++) {
            queue.put("https://www.baidu.com?paramKey=" + i);
        }

        //模拟100个线程，执行1000次请求访问百度
        long start = System.currentTimeMillis();

        final int threadNum = 100;
        //线程计数器
        final CountDownLatch countDownLatch = new CountDownLatch(threadNum);

        //执行线程池
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(threadNum);

        for (int i = 0; i < threadNum; i++) {
            final int threadCount = i + 1;
            fixedThreadPool.execute(() -> {
                System.out.println("thread " + threadCount + " start");
                boolean over = false;
                while (!over) {
                    String url = queue.poll();
                    if(Objects.nonNull(url)) {
                        //发起请求
//                            String result = HttpUtils.getUrl(url);
                        System.out.println("thread " + threadCount + " run result:" + new Random().nextInt());
                    }else {
                        //任务结束
                        over = true;
                        System.out.println("thread " + threadCount + " final");
                        countDownLatch.countDown();
                    }
                }
            });
        }
        countDownLatch.await();
        fixedThreadPool.shutdown();
        System.out.println("执行耗时：" + (System.currentTimeMillis() - start) + "ms");
    }


}
