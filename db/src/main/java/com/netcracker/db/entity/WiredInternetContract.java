package com.netcracker.db.entity;

import java.util.Date;

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
}
