package com.ingenico.epayments.transferservice.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class Transfer implements Identifiable {

    @ApiModelProperty(hidden = true)
    private Long id;
    private Long sourceAccountId;
    private Long targetAccountId;

    @ApiModelProperty(hidden = true)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date date;

    private double amount;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Long getSourceAccountId() {
        return sourceAccountId;
    }

    public void setSourceAccountId(Long sourceAccountId) {
        this.sourceAccountId = sourceAccountId;
    }

    public Long getTargetAccountId() {
        return targetAccountId;
    }

    public void setTargetAccountId(Long targetAccountId) {
        this.targetAccountId = targetAccountId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
