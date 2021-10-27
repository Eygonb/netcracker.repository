package com.netcracker.db.entity;

import java.util.Date;

public class MobileContract extends Contract{
    private Integer minuteCount;
    private Integer smsCount;
    private Integer gbCount;

    public MobileContract(Integer id, Date contractStartDate, Date contractEndDate, String contactNumber,
                          Client contractOwner, Integer minuteCount, Integer smsCount, Integer gbCount) {
        super(id, contractStartDate, contractEndDate, contactNumber, contractOwner);
        this.minuteCount = minuteCount;
        this.smsCount = smsCount;
        this.gbCount = gbCount;
    }

    public Integer getMinuteCount() {
        return minuteCount;
    }

    public void setMinuteCount(Integer minuteCount) {
        this.minuteCount = minuteCount;
    }

    public Integer getSmsCount() {
        return smsCount;
    }

    public void setSmsCount(Integer smsCount) {
        this.smsCount = smsCount;
    }

    public Integer getGbCount() {
        return gbCount;
    }

    public void setGbCount(Integer gbCount) {
        this.gbCount = gbCount;
    }
}
