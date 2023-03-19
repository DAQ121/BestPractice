package ThreadTest.Aync;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class testCompletableFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        testCompletableFuture testCompletableFuture = new testCompletableFuture();

//        testCompletableFuture.testThenApply();

        // 测试各个API
//        testCompletableFuture.testAPI();

        // 测试异常处理
//        testCompletableFuture.testExceptionAPI();

        // 测试组合API
        testCompletableFuture.testCombineAPI();

        // 最佳实践
//        testCompletableFuture.bastPractice();
    }

    private void testCombineAPI() {

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            // 异步执行任务
            return "world";
        });

        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            // 异步执行任务
            return "Hello";
        });

        CompletableFuture<Void> allFutures = CompletableFuture.allOf(future1, future2);
        allFutures.join();

        System.out.println(future1.join() + " " + future2.join());


        CompletableFuture<Object> anyFuture = CompletableFuture.anyOf(future2, future1);
        System.out.println(anyFuture.join());

    }


    public void testThenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<String> stringCompletableFuture = CompletableFuture.supplyAsync(this::do1thing)
                .thenApply(this::do2thing)
                .thenApply(this::do3thing);

        System.out.println(stringCompletableFuture.get());

    }

    /**
     * 1. 避免使用线程池中的固定线程数，应该使用合适的线程数，根据系统负载和硬件性能动态调整线程数。
     * 2. 避免在异步任务中阻塞线程，尽可能让异步任务在后台执行，不影响主线程的响应能力。
     * 3. 合理设置异步任务的超时时间，避免长时间的阻塞，影响程序的性能和响应能力。
     * 4. 使用回调函数或者 CompletableFuture 的 thenApply、thenAccept、thenRun 方法来处理异步任务的结果，避免在主线程中阻塞等待异步任务的完成。
     * 5. 在处理异步任务的结果时，需要考虑异常情况，可以使用 CompletableFuture 的 exceptionally 方法来处理异常。
     */
    public void bastPractice() {

        // 创建异步任务
        CompletableFuture.supplyAsync(() -> {
            String res = "";
            // 异步任务
            for (int i = 0; i < 10; i++) {
                try {
                    res = String.valueOf(i);
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return res;
        }).thenAccept(result -> {
            // 处理异步任务的结果
            System.out.println("异步任务完成，结果为：" + result);
        }).exceptionally(ex -> {
            // 异常处理
            System.out.println("异步任务出现异常：" + ex.getMessage());
            return null;
        }).thenRun(() -> {
            // 在异步任务完成后，执行其他操作
            System.out.println("继续执行其他操作");
        }).join();
        // 用join阻塞住当前线程，当异步任务返回时候，在继续主线程的内容。


        // 继续执行主线程
        System.out.println("继续执行主线程");
    }

    public String do1thing() {
        return "the first thing";
    }

    public String do2thing(String str) {
        return str + "the second thing";
    }

    public String do3thing(String str) {
        return str + "the therids thing";
    }

    public void testAPI () throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // 异步执行任务
            return "Hello";
        });

        CompletableFuture<Integer> future2 = future.thenApply(result -> {
            // 将结果转换为另一个类型的对象
            return result.length();
        });

        CompletableFuture<Void> future3 = future.thenAccept(result -> {
            // 对结果进行消费，不返回值
            System.out.println(result);
        });

        CompletableFuture<String> future4 = future.thenCompose(result -> {
            // 将结果转换为另一个CompletableFuture对象并返回
            return CompletableFuture.supplyAsync(() -> {
                return result + " world";
            });
        });

        CompletableFuture<String> future5 = future.thenCombine(future4, (result1, result2) -> {
            // 将两个CompletableFuture对象的结果合并为一个
            return result1 + " " + result2;
        });

        future5.join();
        System.out.println(future5.get());

    }

    public void testExceptionAPI() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            // 异步执行任务
            for (int i = 0; i < 10; i++) {
                double random = Math.random();
                if (random > 0.5) {
                    System.out.println(random);
                    throw new RuntimeException("Failed");
                }
                System.out.println(random);

            }
            return "Hello";
        });

        CompletableFuture<String> future2 = future.exceptionally(ex -> {
            // 对异常进行处理，并返回一个新的CompletableFuture对象
            return "Recovered";
        });

        System.out.println(future2.get());


        CompletableFuture<String> future3 = future.handle((result, ex) -> {
            // 对结果或异常进行处理，并返回一个新的CompletableFuture对象
            if (ex != null) {
                return "Recovered";
            }
            return result + " world";
        });

        System.out.println(future3.get());


    }




}
