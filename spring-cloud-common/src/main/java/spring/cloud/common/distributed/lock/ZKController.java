package spring.cloud.common.distributed.lock;

import com.test.domi.common.utils.SpringContextUtil;
import com.test.domi.service.impl.IMailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;


@RestController
@RequestMapping("/zk")
public class ZKController {

    @Autowired
    private IMailServiceImpl iMailServiceImpl;
    @Resource(name = "testpool")
    private ExecutorService exs;

    private int k = 1;

    @GetMapping("/lock")
    public Boolean getLock() throws Exception{

        //直接获取，3秒内还未执行完毕则获取失败,必须try-catch
//        submit.get(3000,TimeUnit.MICROSECONDS);
        for (int i = 0; i < 20; i++) {
            exs.submit(new Runnable() {
                @Override
                public void run() {
                    ZKlock zklock = SpringContextUtil.getBean(ZKlock.class);
                    zklock.lock();
                    try {
                        System.out.println(Thread.currentThread().getName() + "获得锁，生成唯一的单据编号" + k++);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        zklock.unlock();
                    }
                }
            });
        }

        return true;
    }
}
