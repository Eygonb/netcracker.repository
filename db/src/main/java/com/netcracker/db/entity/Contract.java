package com.netcracker.db.entity;

import java.util.Date;

public abstract class Contract {
    private Integer id;
    private Date contractStartDate;
    private Date contractEndDate;
    private String contactNumber;
    private Client contractOwner;

    public Contract(Integer id, Date contractStartDate, Date contractEndDate, String contactNumber,
                    Client contractOwner) {
        this.id = id;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.contactNumber = contactNumber;
        this.contractOwner = contractOwner;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(Date contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public Date getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(Date contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Client getContractOwner() {
        return contractOwner;
    }

    public void setContractOwner(Client contractOwner) {
        this.contractOwner = contractOwner;
    }
}
