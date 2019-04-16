package spring.boot.common.function.annotation.dto;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import spring.boot.common.function.annotation.TimeBetweenMax;
import spring.boot.common.function.annotation.WordMaxLength;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@TimeBetweenMax.List({
        @TimeBetweenMax(
              startTimeFied = "startTime",endTimeFied = "endTime",
                length = 1, unit = Calendar.YEAR, groups = {AnnotationClass.Test.class},
                message = "起始时间间隔不能超过1年"
        )
})
public class AnnotationClass implements Serializable {

    private static final long serialVersionUID = 3569400185788601556L;

    public interface Test{}
    public interface Ori{}

    private String id;
    private String headId;
    @WordMaxLength(length = 10 ,groups = {AnnotationClass.Test.class},message = "长度超过10个")
    @NotBlank(message = "经济事项不能为空")
    @Length(max = 20,groups = {AnnotationClass.Ori.class}, message = "原生length校验")
    private String economicIssueId;
    private String economicIssueName;
    @NotBlank(message = "发票类型不能为空",groups = {AnnotationClass.Test.class})
    private String invoiceType;
    private String consumeRecordNo;
    private String consumeRecordDetail;
    private String noConsumeReason;
    private String currencyTypeId;
    private String currencyTypeName;
    private BigDecimal amount;
    private BigDecimal exchageRate;
    private BigDecimal oriAmount;
    private BigDecimal reversePreAmount;
    private String description;
    private String createdBy;
    private Date createdDate;

    /**************************未入库字段，测试用******************************************/
    private String startTime;
    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    /**************************未入库字段，测试用******************************************/


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeadId() {
        return headId;
    }

    public void setHeadId(String headId) {
        this.headId = headId;
    }

    public String getEconomicIssueId() {
        return economicIssueId;
    }

    public void setEconomicIssueId(String economicIssueId) {
        this.economicIssueId = economicIssueId;
    }

    public String getEconomicIssueName() {
        return economicIssueName;
    }

    public void setEconomicIssueName(String economicIssueName) {
        this.economicIssueName = economicIssueName;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getConsumeRecordNo() {
        return consumeRecordNo;
    }

    public void setConsumeRecordNo(String consumeRecordNo) {
        this.consumeRecordNo = consumeRecordNo;
    }

    public String getConsumeRecordDetail() {
        return consumeRecordDetail;
    }

    public void setConsumeRecordDetail(String consumeRecordDetail) {
        this.consumeRecordDetail = consumeRecordDetail;
    }

    public String getNoConsumeReason() {
        return noConsumeReason;
    }

    public void setNoConsumeReason(String noConsumeReason) {
        this.noConsumeReason = noConsumeReason;
    }

    public String getCurrencyTypeId() {
        return currencyTypeId;
    }

    public void setCurrencyTypeId(String currencyTypeId) {
        this.currencyTypeId = currencyTypeId;
    }

    public String getCurrencyTypeName() {
        return currencyTypeName;
    }

    public void setCurrencyTypeName(String currencyTypeName) {
        this.currencyTypeName = currencyTypeName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getExchageRate() {
        return exchageRate;
    }

    public void setExchageRate(BigDecimal exchageRate) {
        this.exchageRate = exchageRate;
    }

    public BigDecimal getOriAmount() {
        return oriAmount;
    }

    public void setOriAmount(BigDecimal oriAmount) {
        this.oriAmount = oriAmount;
    }

    public BigDecimal getReversePreAmount() {
        return reversePreAmount;
    }

    public void setReversePreAmount(BigDecimal reversePreAmount) {
        this.reversePreAmount = reversePreAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "RefundBillLine{" +
                "id='" + id + '\'' +
                ", headId='" + headId + '\'' +
                ", economicIssueId='" + economicIssueId + '\'' +
                ", economicIssueName='" + economicIssueName + '\'' +
                ", invoiceType='" + invoiceType + '\'' +
                ", consumeRecordNo='" + consumeRecordNo + '\'' +
                ", consumeRecordDetail='" + consumeRecordDetail + '\'' +
                ", noConsumeReason='" + noConsumeReason + '\'' +
                ", currencyTypeId='" + currencyTypeId + '\'' +
                ", currencyTypeName='" + currencyTypeName + '\'' +
                ", amount=" + amount +
                ", exchageRate=" + exchageRate +
                ", oriAmount=" + oriAmount +
                ", reversePreAmount=" + reversePreAmount +
                ", description='" + description + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
