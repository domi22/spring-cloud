package spring.cloud.service;

import java.io.Serializable;

public class Allocation implements Serializable {
    private static final long serialVersionUID = -8209722457936284366L;
    private String id;
    private String sqe;
    private String lineId;
    private String allocationId;
    private String segment1;
    private String segment3;
    private String segment2;
    private String segment5;
    private String segment6;
    private String allocationAmount;
    private String allocationDesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSqe() {
        return sqe;
    }

    public void setSqe(String sqe) {
        this.sqe = sqe;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getAllocationId() {
        return allocationId;
    }

    public void setAllocationId(String allocationId) {
        this.allocationId = allocationId;
    }

    public String getSegment1() {
        return segment1;
    }

    public void setSegment1(String segment1) {
        this.segment1 = segment1;
    }

    public String getSegment3() {
        return segment3;
    }

    public void setSegment3(String segment3) {
        this.segment3 = segment3;
    }

    public String getSegment2() {
        return segment2;
    }

    public void setSegment2(String segment2) {
        this.segment2 = segment2;
    }

    public String getSegment5() {
        return segment5;
    }

    public void setSegment5(String segment5) {
        this.segment5 = segment5;
    }

    public String getSegment6() {
        return segment6;
    }

    public void setSegment6(String segment6) {
        this.segment6 = segment6;
    }

    public String getAllocationAmount() {
        return allocationAmount;
    }

    public void setAllocationAmount(String allocationAmount) {
        this.allocationAmount = allocationAmount;
    }

    public String getAllocationDesc() {
        return allocationDesc;
    }

    public void setAllocationDesc(String allocationDesc) {
        this.allocationDesc = allocationDesc;
    }
}

