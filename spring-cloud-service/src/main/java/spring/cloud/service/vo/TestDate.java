package spring.cloud.service.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class TestDate implements Serializable {

    private static final long serialVersionUID = -5952407619974202299L;

    @NotBlank(message = "id不能为空")
    private Long id;
    @NotBlank(message = "用户名不能为空")
    private String userName;

    //@DateTimeFormat(pattern = "yyyy-MM-dd") //前台到后台
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8") //后台到前台
    private Date start;
    //@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss", timezone = "GMT+8") //后台到前台
    private Timestamp end;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Timestamp getEnd() {
        return end;
    }

    public void setEnd(Timestamp end) {
        this.end = end;
    }


}
