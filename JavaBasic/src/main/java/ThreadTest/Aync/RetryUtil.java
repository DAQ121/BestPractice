package ThreadTest.Aync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
public class RetryUtil {

    public static void main(String[] args) {
        int x = 1;
        int y = 0;

        // 为异步调用赋予了出现异常异常重试的能力
        CompletableFuture<Integer> reportAsync =
                retry(() -> doCalculate(x, y), 3);

        try {
            System.out.println(reportAsync.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将真正要执行的工作，包装成一个CompletableFuture
     */
    public static CompletableFuture<Integer> doCalculate(int x, int y) {
        return CompletableFuture.supplyAsync(() -> {
            int res = x / y;
            return res;
            // do anything else...
        });
    }

    /**
     * 异步方法retry的工具类
     */
    public static <R> CompletableFuture<R> retry(Supplier<CompletableFuture<R>> supplier, int maxRetries) {
        CompletableFuture<R> result = supplier.get();
        for(int i = 0; i < maxRetries; i++) {
            result = result.thenApply(CompletableFuture::completedFuture)
                    .exceptionally(t -> {
                        log.warn("retry for: "+ t.getMessage());
                        return supplier.get();
                    })
                    .thenCompose(Function.identity());
        }
        return result;
    }
}