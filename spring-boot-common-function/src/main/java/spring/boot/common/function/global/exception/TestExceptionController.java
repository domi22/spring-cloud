package spring.boot.common.function.global.exception;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.cloud.common.transdto.ResultInfo;
import spring.cloud.common.transdto.ResultUtil;

import java.util.ArrayList;

@RestController
@RequestMapping("/test")
public class TestExceptionController {

    @GetMapping("/t404")
    public ResultInfo getResultException() {
        if (3 / 0 == 1) {
            int size = new ArrayList<>().size();
        }
        return ResultUtil.getSuccessResult(null);
    }
}
