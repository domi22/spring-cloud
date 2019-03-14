package spring.cloud.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 业务方法的参数传递类
 */
public class CommonMethedParam {

    /**
     * 业务方法的参数
     */
    private Object[] args;
    /**
     * 业务方法的返回值
     */
    private Object result;
    /**
     * 自定义参数map封装
     */
    private Map<String,Object> paramMap;

    public CommonMethedParam(Object[] args){
        this.args = args;
        this.paramMap = new HashMap<>();
    }

    public CommonMethedParam(){
        this.paramMap = new HashMap<>();
    }

    /**
     * 获取业务方法的参数
     */
    public Object[] getArgs(){
        return args;
    }

    /**
     * 获取业务方法的返回值
     */
    public Object getResult(){
        return result;
    }

    /**
     * 设置业务方法的返回值
     */
    public void setResult(Object result){
        this.result = result;
    }

    /**
     * 获取自定义参数的值
     */
    public Object get(String key){
        return paramMap.get(key);
    }

    /**
     * 设置自定义参数的值，用于在不同功能之间传递自定义参数
     */
    public void put(String key,Object value){
        paramMap.put(key,value);
    }

}
