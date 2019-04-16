package spring.boot.common.function.annotation.func;

import java.util.HashMap;
import java.util.Map;

/**
 * 业务方法描述
 */
public class BusinessMethodParam {

    /**
     * 业务方法参数
     */
    private Object[] args;
    /**
     * 业务方法返回结果
     */
    private Object result;
    /**
     * 自定义参数Map
     */
    private Map<String,Object> paramMap;

    public BusinessMethodParam(){
        this.paramMap = new HashMap<>();
    }
    public BusinessMethodParam(Object[] args){
        this.args = args;
        this.paramMap = new HashMap<>();
    }

    /**
     * 获取业务方法参数
     */
    public Object[] getArgs(){
        return args;
    }

    /**
     * 获取业务方法的返回结果
     */
    public Object getResult(){
        return result;
    }

    /**
     * 设置业务方法参数返回结果
     */
    public void setResult(Object result){
      this.result = result;
    }

    /**
     * 获取自定义参数值
     */
    public Object get(String key){
        return paramMap.get(key);
    }

    /**
     * 设置子弟你参数值，可用于不同功能之间传递自定义参数
     */
    public void put(String key,Object value){
       paramMap.put(key,value);
    }

}
