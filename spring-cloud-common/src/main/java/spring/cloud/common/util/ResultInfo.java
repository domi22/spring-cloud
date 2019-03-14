package spring.cloud.common.util;

public class ResultInfo<T> {

    private String code;
    private String message;
    private T data;

    public ResultInfo(){
    }

    public  String getCode(){
        return this.code;
    }

    public void setCode(String code){
        this.code = code;
    }

    public  String getMessage(){
        return this.message;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public  Object getData(){
        return this.data;
    }

    public void setData(T data){
        this.data = data;
    }
}