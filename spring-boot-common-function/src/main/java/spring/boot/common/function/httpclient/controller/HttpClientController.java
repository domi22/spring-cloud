package spring.boot.common.function.httpclient.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.concurrent.ConcurrentHashMap;


@RestController
@RequestMapping("/httpclient")
public class HttpClientController {


    @RequestMapping("/f5")
    @ResponseBody
    public String tenTime(){
        try {
            System.out.println("开始沉睡=" + Thread.currentThread().getId());
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "5秒...";
    }


    /**
     * 异步调用
     * 已经废弃的方法 -- 改用 webClient
     * @return
     */
    @RequestMapping("/syn")
    @ResponseBody
    public String async(){
//        WebClient we = new webClient();
        ConcurrentHashMap map = new ConcurrentHashMap(2);
        //建议使用 WebClient高性能异步API替换传统的resttemplate
        AsyncRestTemplate template = new AsyncRestTemplate();
        String url = "http://localhost:8887/spring-boot-common-function/httpclient/f5";//休眠5秒的服务
        ListenableFuture<ResponseEntity<String>> forEntity = template.getForEntity(url, String.class);
        //异步调用后的回调函数
        System.out.println("执行线程 ==" + Thread.currentThread().getId());
        forEntity.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {
            //调用失败
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("=====rest response faliure======");
            }
            //调用成功
            @Override
            public void onSuccess(ResponseEntity<String> result) {
                System.out.println("异步回调线程 ==" + Thread.currentThread().getId());
                System.out.println("=====async rest response success---======" + result.getBody());
            }
        });
        return "异步调用结束";

        /**
         * 前台显示> "异步调用结束"
         * 执行线程 ==33
         * 开始沉睡=34
         * 异步回调线程 ==49
         * =====async rest response success---======5秒...
         */
    }
}
