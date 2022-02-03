package com.netcracker.db.validator.impl;

import com.netcracker.db.entity.Client;
import com.netcracker.db.entity.Contract;
import com.netcracker.db.validator.ContractValidator;
import com.netcracker.db.validator.model.ValidationResult;

import java.util.Date;

public class AbstractContractValidator implements ContractValidator {
    @Override
    public ValidationResult validateContract(Contract contract) {
        ValidationResult result = new ValidationResult();

        validateContract(result, contract);
        validateOwner(result, contract);

        return result;
    }

    private void validateContract(ValidationResult result, Contract contract) {
        Date contractStartDate = contract.getContractStartDate();

        if (contractStartDate == null) {
            result.addError("Contract start date is null");
        }

        Date contractEndDate = contract.getContractEndDate();

        if (contractEndDate == null) {
            result.addError("Contract end date is null");
        }

        if (contractStartDate != null && contractEndDate != null) {
            if (contractStartDate.after(contractEndDate)) {
                result.addError("Contract start date is after contract end date");
            }
        }

        if (contract.getContractNumber() == null) {
            result.addError("Contract number is null");
        } else if (contract.getContractNumber().isEmpty()) {
            result.addError("Contract number is empty");
        }
    }

    private void validateOwner(ValidationResult result, Contract contract) {
        Client owner = contract.getContractOwner();

        if (owner == null) {
            result.addError("Contract owner is null");
        } else {
            if (owner.getFullName() == null) {
                result.addError("Contract owner full name is null");
            } else if (owner.getFullName().isEmpty()) {
                result.addError("Contract owner full name is empty");
            }

            if (owner.getBirthDate() == null) {
                result.addError("Contract owner birth date is null");
            } else if (owner.getAge() < 0 || owner.getAge() > 121) {
                result.addError("Contract owner birth date is invalid");
            }

            if(owner.getSex() == null) {
                result.addError("Contract owner sex is null");
            }

            if(owner.getPassportData() == null) {
                result.addError("Contract owner passport data is null");
            } else if(owner.getPassportData().isEmpty()) {
                result.addError("Contract owner passport data is empty");
            }
        }
    }
}
