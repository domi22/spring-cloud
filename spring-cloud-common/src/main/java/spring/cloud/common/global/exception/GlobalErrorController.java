package spring.cloud.common.global.exception;

import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import spring.cloud.common.transdto.ResultInfo;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/error")
public class GlobalErrorController implements ErrorController{

    /**
     * 1 ErrorController 接口的默认实现类是abstract：AbstractErrorController
     * 2 AbstractErrorController 的子类 BasicErrorController 才是真正干活儿的实现类（分html、json 两个方法处理，我们只需要在GlobalErrorController重写这2个方法即可）
     * 3 BasicErrorController 有 private final ErrorProperties errorProperties;属性
     * ErrorProperties里面记录了error的路径：
     * @Value("${error.path:/error}")
     * private String path = "/error";
     * -----
     * 4 BasicErrorController 的封装只能将状态码的提示信息返回前台，不能拿到手动抛异常的信息，因此需要实现HandlerExceptionResolver
     *  ------------------------------------------------------------
     *  BasicErrorController只有有参构造，无法直接继承
     *  如果不实现ErrorController，则会造成相同路径/error有2个类，冲突了。启动时报如下异常：
     *  org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'requestMappingHandlerMapping' defined in class path resource [org/springframework/web/servlet/config/annotation/DelegatingWebMvcConfiguration.class]: Invocation of init method failed; nested exception is java.lang.IllegalStateException: Ambiguous mapping. Cannot map 'basicErrorController' method
     * public org.springframework.web.servlet.ModelAndView org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController.errorHtml(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
     * to {[/error],produces=[text/html]}: There is already 'globalErrorController' bean method
     */
    private final static String DEFAULT_ERROR_VIEW = "/error";
    private final static  org.slf4j.Logger LOGGER = LoggerFactory.getLogger(GlobalErrorController.class);
    /**
     * ResultBuilder 实现 HandlerExceptionResolver 接口重写public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e)
     * 通过httpServletRequest.setAttribute("fp.error", e);将Exception放到request中
     * 这种方法的好处是能拿到手动抛异常的信息
     */
    @Resource
    private ResultBuilder resultBuilder;

    /** 1- BasicErrorController只有有参构造，无法直接继承,
     *  2- 如果不实现ErrorController，则会造成相同路径/error有2个类，冲突了。启动时报如下异常：
     *  org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'requestMappingHandlerMapping' defined in class path resource [org/springframework/web/servlet/config/annotation/DelegatingWebMvcConfiguration.class]: Invocation of init method failed; nested exception is java.lang.IllegalStateException: Ambiguous mapping. Cannot map 'basicErrorController' method
     * public org.springframework.web.servlet.ModelAndView org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController.errorHtml(javax.servlet.http.HttpServletRequest,javax.servlet.http.HttpServletResponse)
     * to {[/error],produces=[text/html]}: There is already 'globalErrorController' bean method
     * ----------------------
     * ErrorProperties里面记录了error的路径：
     * * @Value("${error.path:/error}")
     * * private String path = "/error";
     * 如果不需要从GlobalErrorController中的getErrorPath方法获取该路径，则该方法可以空实现
     */
    @Override
    public String getErrorPath(){
//        return null;
        return  resultBuilder.getErrorProperties().getPath();
    }

    /**
     * 如果请求头返回的类型是text/html，则返回到错误信息页面
     * @param request
     * @return
     */
    @RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView errorHtml(HttpServletRequest request) {
        return new ModelAndView(DEFAULT_ERROR_VIEW,"errorInfo",resultBuilder.getErrorInfo(request));
    }

    /**
     * 除了text/html的请求头信息，其它都返回json格式
     * @param request 请求对象
     * @return 错误信息字符串
     */
    @RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResultInfo error(HttpServletRequest request){
        return resultBuilder.getErrorInfo(request);
    }



}
