package spring.cloud.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RefundBillLine implements Serializable {
    private static final long serialVersionUID = -1022959238346661759L;
    private String id;
    private String headId;
    private String economicIssueId;
    private String economicIssueName;
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
    private List<Allocation> allocations = new ArrayList<>();


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

    public List<Allocation> getAllocations() {
        return allocations;
    }

    public void setAllocations(List<Allocation> allocations) {
        this.allocations = allocations;
    }
}

