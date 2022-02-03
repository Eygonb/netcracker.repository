package com.netcracker.db.validator.impl;

import com.netcracker.db.entity.Contract;
import com.netcracker.db.entity.WiredInternetContract;
import com.netcracker.db.validator.ContractValidator;
import com.netcracker.db.validator.model.ValidationResult;

public class WiredInternetContractValidator implements ContractValidator {
    @Override
    public ValidationResult validateContract(Contract contract) {
        if (!WiredInternetContract.class.equals(contract.getClass())) {
            return new ValidationResult();
        }

        ValidationResult result = new ValidationResult();
        WiredInternetContract wiredInternetContract = (WiredInternetContract) contract;

        if (wiredInternetContract.getConnectionSpeed() == null) {
            result.addError("Contract connection speed is null");
        } else if (wiredInternetContract.getConnectionSpeed() <= 0) {
            result.addError("Contract connection speed is invalid");
        }

        return result;
    }
}
