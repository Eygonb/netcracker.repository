package com.netcracker.db.entity;

import java.util.Date;
import java.util.Objects;

public class WiredInternetContract extends Contract{
    private Integer connectionSpeed;

    public WiredInternetContract(Integer id, Date contractStartDate, Date contractEndDate, String contactNumber,
                                 Client contractOwner, Integer connectionSpeed) {
        super(id, contractStartDate, contractEndDate, contactNumber, contractOwner);
        this.connectionSpeed = connectionSpeed;
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
}
