package spring.cloud.service.controller;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import java.util.Date;
import java.util.concurrent.*;

public class BaseController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        ThreadPoolTaskExecutor rh = new ThreadPoolTaskExecutor();
        rh.setQueueCapacity(12);

        binder.registerCustomEditor(Date.class, new MyCustomDateEditor());
    }
}
