package ThreadTest.GuavaExecutorTest;

import com.google.common.util.concurrent.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class testGuavaExecutor {

    public static void main(String[] args) {

    }

    public static void testMoreExecutor() {
        // 单例获取线程池, 可以作为全局的线程池
        Executor executor = MoreExecutors.directExecutor();

        // 每次调用都会获取新对象
//        ListeningExecutorService listeningExecutorService = MoreExecutors.newDirectExecutorService();
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(8));

        // 使用jdk原生的ExecutorService 的 submit提交将会返回一个实现了Future接口的FutureTask对象，我们通过调用Future.get方法获取任务的执行结果，如果任务没有执行完，get方法将会导致调用线程的阻塞。
        // 使用Guava提供的增强型的Future接口 ListenableFuture，能以异步回调的方式获取执行结果
        List<ListenableFuture<Integer>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            ListenableFuture<Integer> submit = listeningExecutorService.submit(() -> {
                Random random = new Random();
                if (finalI == 5) {
                    return random.nextInt() / 0;
                }
                return random.nextInt();
            });

//            submit.addListener(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("result:" + submit.get());
//                }
//            }, MoreExecutors.directExecutor());

            // Futures工具类提供了工具方法用于任务正常或异常情况的处理。
            Futures.addCallback(submit, new FutureCallback<Integer>() {
                public void onSuccess(Integer result) {
                    // 任务正常返回结果
                    System.out.println("result:" + result);
                }
                public void onFailure(Throwable t) {
                    // 任务抛异常了
                    t.printStackTrace();
                }
            }, MoreExecutors.directExecutor());


            futures.add(submit);
        }

    }
}
