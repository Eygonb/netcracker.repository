package com.netcracker.db.entity;

import java.util.Date;
import java.util.Objects;

public class MobileContract extends Contract{
    private Integer minuteCount;
    private Integer smsCount;
    private Integer trafficCount;

    public MobileContract(Integer id, Date contractStartDate, Date contractEndDate, String contractNumber,
                          Client contractOwner, Integer minuteCount, Integer smsCount, Integer trafficCount) {
        super(id, contractStartDate, contractEndDate, contractNumber, contractOwner);
        this.minuteCount = minuteCount;
        this.smsCount = smsCount;
        this.trafficCount = trafficCount;
    }

    public MobileContract() {
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

    public Integer getTrafficCount() {
        return trafficCount;
    }

    public void setTrafficCount(Integer trafficCount) {
        this.trafficCount = trafficCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MobileContract that = (MobileContract) o;
        return Objects.equals(minuteCount, that.minuteCount) && Objects.equals(smsCount, that.smsCount) &&
                Objects.equals(trafficCount, that.trafficCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), minuteCount, smsCount, trafficCount);
    }

    @Override
    public MobileContract clone() {
        return new MobileContract(getId(), getContractStartDate(), getContractEndDate(), getContractNumber(),
                getContractOwner().clone(), getMinuteCount(), getSmsCount(), getTrafficCount());
    }
}
