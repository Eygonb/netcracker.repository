package com.netcracker.db.entity;

import java.util.Date;
import java.util.Objects;

public class WiredInternetContract extends Contract{
    private Integer connectionSpeed;

    public WiredInternetContract(Integer id, Date contractStartDate, Date contractEndDate, String contractNumber,
                                 Client contractOwner, Integer connectionSpeed) {
        super(id, contractStartDate, contractEndDate, contractNumber, contractOwner);
        this.connectionSpeed = connectionSpeed;
    }

    public WiredInternetContract() {
    }

    public Integer getConnectionSpeed() {
        return connectionSpeed;
    }

    public void setConnectionSpeed(Integer connectionSpeed) {
        this.connectionSpeed = connectionSpeed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        WiredInternetContract that = (WiredInternetContract) o;
        return Objects.equals(connectionSpeed, that.connectionSpeed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), connectionSpeed);
    }

    @Override
    public WiredInternetContract clone() {
        return new WiredInternetContract(getId(), getContractStartDate(), getContractEndDate(), getContractNumber(),
                getContractOwner().clone(), getConnectionSpeed());
    }
}
