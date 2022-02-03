package com.netcracker.db.validator.impl;

import com.netcracker.db.entity.Contract;
import com.netcracker.db.entity.DigitalTVContract;
import com.netcracker.db.validator.ContractValidator;
import com.netcracker.db.validator.model.ValidationResult;

public class DigitalTVContractValidator implements ContractValidator {
    @Override
    public ValidationResult validateContract(Contract contract) {
        if (!DigitalTVContract.class.equals(contract.getClass())) {
            return new ValidationResult();
        }

        ValidationResult result = new ValidationResult();
        DigitalTVContract digitalTVContract = (DigitalTVContract) contract;

        if (digitalTVContract.getChannelPackage() == null) {
            result.addError("Contract channel package is null");
        } else if (digitalTVContract.getContractNumber().isEmpty()) {
            result.addError("Contract channel package is empty");
        }

        return result;
    }
}
