package retry;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * @ClassName RetryAspect
 * @Description TODO
 * @Date 2021/8/13 2:27 下午
 * @Created by liyanyan
 */
@Aspect
@Component
@Slf4j
public class RetryAspect {

    ExecutorService executorService = new ThreadPoolExecutor(3, 5, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<Runnable>());

    @Around(value = "@annotation(retryDot)")
    public Object execute(ProceedingJoinPoint joinPoint, RetryDot retryDot) throws Throwable {
        RetryTemplate retryTemplate = new RetryTemplate() {
            @Override
            protected Object doBiz() throws Throwable {
                System.out.println("重试......");
                ResultData result = (ResultData) joinPoint.proceed();
                return result;
            }
        };
        //这里进行次数和间隔时间的注入，注解不传参也可以自行做远程配置 统一控制
        retryTemplate.setRetryTime(retryDot.count())
                .setSleepTime(retryDot.sleep());

        if(retryDot.asyn()) {
            return retryTemplate.submit(executorService);
        }else {
            return retryTemplate.execute();
        }
    }

}