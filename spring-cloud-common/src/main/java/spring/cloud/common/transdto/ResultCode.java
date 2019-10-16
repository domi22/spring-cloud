package spring.cloud.common.transdto;

public enum ResultCode {

    SUCCESS("000000","成功"),
    CONNECT_ERROR("100001","网络连接失败"),
    CONNECT_TIMEOUT("100002","网络连接超时"),
    INTERNAL_SERVER_ERROR("100003","服务器内部错误"),
    QUERY_ERROR("100004","查询失败"),
    INSERT_ERROR("100005","保存数据失败"),
    UPDATE_ERROR("100006","更新数据失败"),
    DELETE_ERROR("100007","删除数据失败");

    private String code;
    private String msg;

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
