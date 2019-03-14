package spring.cloud.common.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ResultBuilder implements HandlerExceptionResolver,Ordered {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResultBuilder.class);
    private static final String ERROR_NAME = "xxx.error";
    private ErrorProperties errorProperties;

    public ErrorProperties getErrorProperties() {
        return errorProperties;
    }

    public ResultBuilder(ServerProperties serverProperties){
        LOGGER.info("serverProperties:{}",serverProperties.getError());
        this.errorProperties = serverProperties.getError();
    }

    public ResultInfo getErrorInfo(HttpServletRequest request){
        return  this.getErrorInfo(request,this.getError(request));
    }

    /**
     * 全局异常返回处理
     * @param request
     * @param error
     * @return
     */
    public ResultInfo getErrorInfo(HttpServletRequest request,Throwable error){
        ResultInfo resultInfo = new ResultInfo();
        //根据不同的error获取错误信息
        String resultCode = "";
        StringBuffer msg = new StringBuffer();
        if (error instanceof MethodArgumentNotValidException) {
            //1 参数校验异常
            resultCode = getString2((MethodArgumentNotValidException) error, resultCode, msg);
        }else {
            //3 httpStatu枚举code对应的异常
            resultCode = getString3(request, msg);
        }
        resultInfo.setCode(resultCode);
        resultInfo.setMessage(msg.toString());
        resultInfo.setData((Object)null);
        return resultInfo;
    }


    private String getString3(HttpServletRequest request, StringBuffer msg) {
        HttpStatus httpStatus = this.getHttpStatus(request);
        msg.append(httpStatus.getReasonPhrase());
        return String.valueOf(httpStatus.value());
    }

    private String getString2(MethodArgumentNotValidException error, String resultCode, StringBuffer msg) {
        BindingResult bindingResult = error.getBindingResult();
        if (bindingResult.hasErrors()) {
            List<FieldError> list = bindingResult.getFieldErrors();
            resultCode =ResultCode.CONNECT_ERROR.getCode();
            for (FieldError fieldError : list) {
                msg.append(fieldError.getDefaultMessage() + ";");
            }
        }
        return resultCode;
    }

    private String getString(Throwable error, StringBuffer msg) {
        msg.append(error.getMessage());
        return ResultCode.INSERT_ERROR.getCode();
    }

    /**
     *  拿到最根部的error,携带手动抛出的异常信息
     * @param request
     * @return
     */
    public Throwable getError(HttpServletRequest request){
        Throwable error = (Throwable)request.getAttribute(ERROR_NAME);
        if (error == null) {
            error = (Throwable)request.getAttribute("javax.servlet.error.exception");
        }

        if (error != null) {
            //while (error instanceof ServletException && ((Throwable) error).getCause() != null) {
            while (error instanceof Exception && ((Throwable) error).getCause() != null) {
                error = ((Throwable) error).getCause();
            }
        } else {
            String message = (String)request.getAttribute("javax.servlet.error.message");
            if (StringUtils.isNotEmpty(message)) {
                HttpStatus status = this.getHttpStatus(request);
                message = "Unknown Exception With" + status.value() + " " + status.getReasonPhrase();
            }

            error = new Exception(message);
        }

        return (Throwable)error;
    }

    public HttpStatus getHttpStatus(HttpServletRequest request){
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        try {
            return statusCode != null ? HttpStatus.valueOf(statusCode.intValue()) : HttpStatus.INTERNAL_SERVER_ERROR;
        } catch (Exception var4) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        httpServletRequest.setAttribute(ERROR_NAME, e);
        return null;
    }
}
