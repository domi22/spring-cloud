package spring.cloud.common.redis.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Person implements Serializable {
    private static final long serialVersionUID = -6685782006612876998L;

    private Integer id;
    private String name;
    private BigDecimal num;
    @JsonFormat(pattern = "yyyy/MM/dd", timezone = "GMT+8")
    private Date uadteTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }

    public Date getUadteTime() {
        return uadteTime;
    }

    public void setUadteTime(Date uadteTime) {
        this.uadteTime = uadteTime;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", num=" + num +
                ", uadteTime=" + uadteTime +
                '}';
    }
}
