package spring.cloud.common.util;

public class ResultUtil {

    public ResultUtil(){
    }

    public static ResultInfo getSuccessResult(Object object){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(ResultCode.SUCCESS.getCode());
        resultInfo.setMessage(ResultCode.SUCCESS.getMsg());
        resultInfo.setData(object);
        return resultInfo;
    }

    public static ResultInfo getFailResult(ResultCode resultCode){
        return getFailResult(resultCode,(Object)null);
    }

    public static ResultInfo getFailResult(String resultCode,String resultMessage,Object data){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(resultCode);
        resultInfo.setMessage(resultMessage);
        resultInfo.setData(data);
        return resultInfo;
    }

    public  static  ResultInfo getFailResult(ResultCode resultCode,Object data){
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setCode(resultCode.getCode());
        resultInfo.setMessage(resultCode.getMsg());
        resultInfo.setData(data);
        return resultInfo;
    }

}
