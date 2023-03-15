package retry;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

/**
 * @ClassName RetryTemplate
 * @Description TODO
 * @Date 2021/8/13 11:39 上午
 * @Created by liyanyan
 */
@Slf4j
public abstract class RetryTemplate {

    private static final int DEFAULT_RETRY_TIME = 1;
    private int retryTime = DEFAULT_RETRY_TIME;

    //重试的睡眠时间
    private int sleepTime = 0;

    public int getSleepTime() {
        return sleepTime;
    }

    public RetryTemplate setSleepTime(int sleepTime) {
        if(sleepTime < 0) {
            throw new IllegalArgumentException("sleepTime should equal or bigger than 0");
        }
        this.sleepTime = sleepTime;
        return this;
    }

    public int getRetryTime() {
        return retryTime;
    }

    public RetryTemplate setRetryTime(int retryTime) {
        if(retryTime <= 0) {
            throw new IllegalArgumentException("retryTime should bigger than 0");
        }
        this.retryTime = retryTime;
        return this;
    }

    /**
     * 重试的业务执行代码
     * 失败时请抛出一个异常
     *
     * todo 去定返回的封装类，根据返回结果的状态来判定是否需要重试 这里定义的是ResultData
     * @return
     * @throws Exception
     */
    protected abstract Object doBiz() throws Throwable;

    public Object execute() throws Throwable {
        ResultData result;
        for(int i=0; i<retryTime; i++) {
            result = (ResultData) doBiz();
            if(result.getCode()==200) {
                return doBiz();
            }
            Thread.sleep(sleepTime);
        }
        result = (ResultData) doBiz();
        return result;
    }

    public Object submit(ExecutorService executorService) {
        if(executorService == null) {
            throw new IllegalArgumentException("please choose executorService!");
        }

        return executorService.submit((Callable<? extends Object>) () -> {
            try {
                return execute();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            throw new RuntimeException("异步问题");
        });
    }
}