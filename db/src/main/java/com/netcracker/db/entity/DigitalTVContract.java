package com.netcracker.db.entity;

import java.util.Date;

public class DigitalTVContract extends Contract{
    private String[] channelPackage;

    public DigitalTVContract(Integer id, Date contractStartDate, Date contractEndDate, String contactNumber,
                             Client contractOwner, String[] channelPackage) {
        super(id, contractStartDate, contractEndDate, contactNumber, contractOwner);
        this.channelPackage = channelPackage;
    }

    public String[] getChannelPackage() {
        return channelPackage;
    }

    public void setChannelPackage(String[] channelPackage) {
        this.channelPackage = channelPackage;
    }
}
