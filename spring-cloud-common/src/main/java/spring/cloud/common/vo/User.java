package spring.cloud.common.vo;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable,Cloneable {

    private static final long serialVersionUID = 6260893611011505880L;

    public final static String CONTEXT_KEY_USERID = "x-user-id";

    private String userId;

    private String userName;

    private List<String> allowPermissionService;

    public User() {
    }

    public List<String> getAllowPermissionService() {
        return allowPermissionService;
    }

    public void setAllowPermissionService(List<String> allowPermissionService) {
        this.allowPermissionService = allowPermissionService;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 仅仅是浅拷贝（拷贝基本成员属性，对于引用类型仅返回指向改地址的引用）
     * @return
     * @throws CloneNotSupportedException
     */
//    public Object clone() throws CloneNotSupportedException{
//        return super.clone();
//    }

    /**
     * 深拷贝（属性对象也需要实现Cloneable的，否则无法Clone）
     * @return
     * @throws CloneNotSupportedException
     */
    public Object clone() throws CloneNotSupportedException {
        User o = (User) super.clone();
        o.userName = new String(this.userName);
        //o.a = (A) a.clone();
        return o;
    }

}
