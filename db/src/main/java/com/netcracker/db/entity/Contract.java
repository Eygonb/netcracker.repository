package com.netcracker.db.entity;

import java.util.Date;
import java.util.Objects;

public abstract class Contract {
    private Integer id;
    private Date contractStartDate;
    private Date contractEndDate;
    private String contractNumber;
    private Client contractOwner;

    public Contract(Integer id, Date contractStartDate, Date contractEndDate, String contactNumber,
                    Client contractOwner) {
        this.id = id;
        this.contractStartDate = contractStartDate;
        this.contractEndDate = contractEndDate;
        this.contractNumber = contactNumber;
        this.contractOwner = contractOwner;
    }

    public Contract() {
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

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public Client getContractOwner() {
        return contractOwner;
    }

    public void setContractOwner(Client contractOwner) {
        this.contractOwner = contractOwner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return id.equals(contract.id) && Objects.equals(contractStartDate, contract.contractStartDate) &&
                Objects.equals(contractEndDate, contract.contractEndDate) &&
                Objects.equals(contractNumber, contract.contractNumber) &&
                Objects.equals(contractOwner, contract.contractOwner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contractStartDate, contractEndDate, contractNumber, contractOwner);
    }

    public abstract Contract clone();
}
