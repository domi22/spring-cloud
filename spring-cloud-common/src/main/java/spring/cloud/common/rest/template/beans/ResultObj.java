package spring.cloud.common.rest.template.beans;

import java.io.Serializable;

public class ResultObj implements Serializable {
    private static final long serialVersionUID = 1107469674474509538L;
    private String name;
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
