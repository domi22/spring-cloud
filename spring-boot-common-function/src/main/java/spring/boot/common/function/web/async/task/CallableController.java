package spring.boot.common.function.web.async.task;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 耗时接口，异步返回给前端，提高吞吐量
 * spring5请关注响应式编程 webflux等等
 */
@RestController
@RequestMapping("/test")
public class CallableController {

    /**
     * spring 3.2 + Servlet3.0
     * 如果我们需要超时处理的回调或者错误处理的回调，我们可以使用WebAsyncTask代替Callable，实际使用中，建议直接使用WebAsyncTask
     * https://blog.csdn.net/f641385712/article/details/88692534
     * @return
     */
    @GetMapping("/callable")
    public Callable<Map<String,Object>> getCallableResult(){
        //注意lambda表达式是不能抛出异常的（Function中的apply方法不能抛出异常）
        Callable<Map<String,Object>> call = () -> {
            Map<String, Object> map = new HashMap<>();
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return map;
            }
            map.put("id", 123);
            map.put("name", "zhangsan");
            System.out.println(map.toString());
            return map;
        };
        System.out.println("主线程执行完毕");
        /**
         * 如果此处的异步执行时间很长，如何主动断开呢？用WebAsyncTask代替
         */
        return call;
    }

    /**
     * spring 3.2 + Servlet3.0
     * 如果我们需要超时处理的回调或者错误处理的回调，我们可以使用WebAsyncTask代替Callable
     */
    @GetMapping("/webtask")
    public WebAsyncTask<String> getWebAsyncTask() {
        // 1000 为超时设置
        WebAsyncTask<String> webAsyncTask = new WebAsyncTask<>(10000L,()->{
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return "";
            }
            return "异步执行的结果集";
        });

        webAsyncTask.onCompletion(() -> System.out.println("调用完成"));

        webAsyncTask.onTimeout(() -> {
            System.out.println("调用超时");
            return "time out";
        });

        webAsyncTask.onError(() -> {
            System.out.println("调用出错");
            return "error...";
        });

        System.out.println("主线程执行完毕...");
        return webAsyncTask;
    }


    /**
     * spring 3.2 + Servlet3.0
     * CompletableFuture + DeferredResult 异步返回
     * api：https://www.jianshu.com/p/6f3ee90ab7d3
     * api: https://blog.csdn.net/u011726984/article/details/79320004
     * api: https://blog.csdn.net/qq_36597450/article/details/81232051
     */
    @GetMapping("/deferred")
    public DeferredResult<String> getDeferredResultResult() {
        DeferredResult<String> deferredResult = new DeferredResult<>();
        //deferredResult.onCompletion(Runnable run); 执行完毕之后 执行该逻辑
        //deferredResult.onError(Runnable run); 发生错误之后 执行该逻辑
        //deferredResult.onTimeout(Runnable run); 超时之后 执行该逻辑

        //单独一个CompletableFuture任务，执行完毕之后，封装参数
        CompletableFuture.supplyAsync(()->{
            try {
                Thread.sleep(6000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).whenCompleteAsync((result, throwable) -> deferredResult.setResult(result))
                .exceptionally(e-> e.getMessage());

        //thenAccept必须传入结果s，进行消费处理
        CompletableFuture.supplyAsync(()->"hello").thenAcceptAsync((s) -> deferredResult.setResult(s));

        //thenRun不关心上一步结果，直接执行func函数
        CompletableFuture.supplyAsync(()->"hello").thenRunAsync(() -> deferredResult.setResult("nihao"));

        //thenCombine 表示用上一步的返回值和当前func的返回值，做合并处理；join()用于获取结果
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }), (s1, s2) -> s1 + " " + s2).join();

        //thenAcceptBoth 和 thenCombine 类似 都是对两个返回值的合并处理
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenAcceptBothAsync(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }), (s1, s2) -> System.out.println(s1 + " " + s2));

        //不关心这两个CompletionStage的结果，只关心这两个CompletionStage执行完毕，之后在进行操作（Runnable）
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s1";
        }).runAfterBothAsync(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s2";
        }), () -> System.out.println("hello world"));


        //两个CompletionStage，谁计算的快，我就用那个CompletionStage的结果进行下一步的转化操作。
        String result2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s1";
        }).applyToEitherAsync(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello world";
        }), s -> s).join();

        //两个CompletionStage，谁计算的快，我就用那个CompletionStage的结果进行下一步的消耗操作。
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s1";
        }).acceptEitherAsync(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello world";
        }), s -> deferredResult.setResult(s));

        //任何一个执行完了，都会执行的操作
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s1";
        }).runAfterEitherAsync(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s2";
        }), () -> System.out.println("hello world"));


        String result3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (1 == 1) {
                throw new RuntimeException("测试一下异常情况");
            }
            return "s1";
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return "do exception ...";
        }).join();

        return deferredResult;
    }






}
