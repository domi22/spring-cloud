package spring.cloud.common.controller;

import com.sun.org.apache.bcel.internal.generic.I2F;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.cloud.common.util.ResultInfo;
import spring.cloud.common.util.ResultUtil;
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
