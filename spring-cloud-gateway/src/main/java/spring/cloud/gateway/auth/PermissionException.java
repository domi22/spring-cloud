package spring.cloud.gateway.auth;

public class PermissionException extends RuntimeException {
    private static final long serialVersionUID = -1934189489095679591L;
    public PermissionException(String msg) {
        super(msg);
    }
}
