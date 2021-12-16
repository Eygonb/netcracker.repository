package com.netcracker.db.entity;

import java.util.Arrays;
import java.util.Date;

public class DigitalTVContract extends Contract {
    private String[] channelPackage;

    public DigitalTVContract(Integer id, Date contractStartDate, Date contractEndDate, String contractNumber,
                             Client contractOwner, String[] channelPackage) {
        super(id, contractStartDate, contractEndDate, contractNumber, contractOwner);
        this.channelPackage = channelPackage;
    }

    public String[] getChannelPackage() {
        return channelPackage;
    }

    public void setChannelPackage(String[] channelPackage) {
        this.channelPackage = channelPackage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DigitalTVContract that = (DigitalTVContract) o;
        return Arrays.equals(channelPackage, that.channelPackage);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Arrays.hashCode(channelPackage);
        return result;
    }

    @Override
    public DigitalTVContract clone() {
        return new DigitalTVContract(getId(), getContractStartDate(), getContractEndDate(), getContractNumber(),
                getContractOwner().clone(), getChannelPackage().clone());
    }
}
