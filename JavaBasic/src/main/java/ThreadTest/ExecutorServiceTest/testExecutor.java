package ThreadTest.ExecutorServiceTest;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @description:
 * @author: daiaoqi
 * @date: 2022/9/16
 **/
@Slf4j
public class testExecutor {

    public static void main(String[] args) throws InterruptedException {

        // 手动创建线程池
        testThreadPoolExecutor();

        // 使用Executors创建
//        testExecutors();

    }

    /**
     * 最好手动创建线程池,这样可以控制一些参数
     * 1. corePoolSize: 核心池的大小
     * 2. maximumPoolSize: 在线程池中最多能创建多少个线程，当线程池中的线程数目达到corePoolSize后，就会把新加入的任务放到缓存队列当中，
     *    如果入队失败(队列已满)则尝试创建临时线程，但临时线程和核心线程的总数不能超过maximumPoolSize，
     *    当线程总数达到maximumPoolSize后会拒绝新任务；所以有两种方式可以让任务绝不被拒绝：
     *      ① 将maximumPoolSize设置为Integer.MAX_VALUE(线程数不可能达到这个值)，CachedThreadPool就是这么做的，最好不要这样做，因为可能会产生大量请求，导致OOM
     *      ② 使用无限容量的阻塞队列(比如LinkedBlockingQueue)，所有处理不过来的任务全部排队去，FixedThreadPool就是这么做的
     *
     * 3. keepAliveTme: 表示线程没有任务执行时, 最多保持多久时间终止会话
     * 4. TimeUnit
     * 5. ThreadFactory 使用的线程池,最好自定义一个线程池,这样可以设置线程池名字
     * 6. 阻塞队列
     *   ① ArrayBlockingQueue 数组实现的阻塞队列，数组不支持自动扩容。所以当阻塞队列已满，线程池会根据handler参数中指定的拒绝任务的策略决定如何处理后面加入的任务
     *   ② LinkedBlockingQueue 链表实现的阻塞队列，默认容量Integer.MAX_VALUE(不限容)，当然也可以通过构造方法限制容量
     *   ③ SynchronousQueue 零容量的同步阻塞队列，添加任务直到有线程接受该任务才返回，用于实现生产者与消费者的同步，所以被叫做同步队列
     *   ④ PriorityBlockingQueue 二叉堆实现的优先级阻塞队列
     *   ⑤ DelayQueue 延时阻塞队列，该队列中的元素需要实现Delayed接口，底层使用PriorityQueue的二叉堆对Delayed元素排序
     *                 ScheduledThreadPoolExecutor底层就用了DelayQueue的变体"DelayWorkQueue"
     *                 队列中所有的任务都会封装成ScheduledFutureTask对象(该类已实现Delayed接口)
     *
     * 7. 拒绝策略
     *   ①ThreadPoolExecutor.AbortPolicy: 这是默认的拒绝策略, 丢弃任务并抛出RejectedExecutionException异常。可通过实现RejectedExecutionHandler接口来自定义任务拒绝后的处理策略
     *   ②ThreadPoolExecutor.DiscardPolicy：也是丢弃任务，但是不抛出异常。
     *   ③ThreadPoolExecutor.DiscardOldestPolicy：丢弃队列最前面的任务，然后重新尝试执行任务（重复此过程）
     *   ④ThreadPoolExecutor.CallerRunsPolicy：由调用线程处理该任务
     */
    public static void testThreadPoolExecutor() throws InterruptedException {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(8, 200, 60L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(2),
                new ThreadFactoryBuilder().setNameFormat("heartbeat-%d").build(),
                new ThreadPoolExecutor.AbortPolicy());

        // 创建出线程池实例之后,不会立即创建出所有线程,只有等到任务进来的时候,才会创建出与任务size相等的核心线程
        // 除非手动调用prestartAllCoreThreads()方法,在创建线程池之后立马创建出所有线程
//        executor.prestartAllCoreThreads();

        // 核心池中的线程会一致保存在线程池中(即使线程空闲)
        // 除非调用allowCoreThreadTimeOut方法,允许核心线程在空闲后一定时间内销毁
//        executor.allowCoreThreadTimeOut(true);

        for (int i = 0; i < 10; i++) {
            Integer finalI = i;
            // 带retry多线程执行, 可配置重试次数, 以及重试的等待时间
            executor.execute(() -> executeWithRetry(() -> {
                if (finalI == 5) {
                    log.info(String.valueOf(1 / 0));
                } else {
                    log.info(String.valueOf(finalI));
                }
            },3,1000));
        }

    }

    /**
     * 使用java基于ThreadPool封装的ExecutorService,更简单方便,但是参数不由自己控制了
     * 1. submit最终都会调用execute方法去执行任务，区别在于submit方法返回一个FutureTask对象,可以通过FutureTask对象获取任务执行的结果
     *
     */
    public static void testExecutors() {
        ExecutorService executorService = Executors.newFixedThreadPool(8);
        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Future<Integer> submit = executorService.submit(() -> {
                Random random = new Random();
                if (finalI == 5) {
                    return random.nextInt() / 0;
                }
                return random.nextInt();
            });

            futures.add(submit);
        }

        futures.forEach(future -> {
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * 用于多线程重试
     * @param task
     * @param maxRetries
     * @param delay
     */
    public static void executeWithRetry(Runnable task, int maxRetries, long delay) {
        int retries = 0;
        while (retries < maxRetries) {
            try {
                task.run();
                return;
            } catch (Exception e) {
                if (retries < maxRetries - 1) {
                    retries++;
                    try {
                        log.info("retry " + retries);
                        Thread.sleep(delay);
                    } catch (InterruptedException ex) {
                        log.error("error occurred while thread sleep ...");
                    }
                } else {
                    throw e;
                }
            }
        }
    }


}
