package spring.cloud.service.vo;

import java.io.*;

public class TestClone implements Serializable,Cloneable {
    private static final long serialVersionUID = 7459621976780667160L;

    private String id;
    private String name;
    private TestDate testDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TestDate getTestDate() {
        return testDate;
    }

    public void setTestDate(TestDate testDate) {
        this.testDate = testDate;
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        TestClone testClone = (TestClone)super.clone();
        testClone.setTestDate((TestDate)getTestDate().clone());
        return testClone;
    }


}
