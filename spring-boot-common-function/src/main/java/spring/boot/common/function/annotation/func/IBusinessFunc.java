package spring.boot.common.function.annotation.func;

/**
 * 报销类型关联功能接口
 */
public interface IBusinessFunc {

    /**
     * 执行业务功能
     */
    void executeFunc(BusinessMethodParam businessMethodParam) throws Exception;
}
