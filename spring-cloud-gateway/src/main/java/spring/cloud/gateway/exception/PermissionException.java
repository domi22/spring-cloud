package spring.cloud.gateway.exception;

public class PermissionException extends RuntimeException {
    private static final long serialVersionUID = -1934189489095679591L;
    public PermissionException(String msg) {
        super(msg);
    }
}
