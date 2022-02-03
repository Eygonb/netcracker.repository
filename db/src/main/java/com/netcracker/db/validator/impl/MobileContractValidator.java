package com.netcracker.db.validator.impl;

import com.netcracker.db.entity.Contract;
import com.netcracker.db.entity.MobileContract;
import com.netcracker.db.validator.ContractValidator;
import com.netcracker.db.validator.model.ValidationResult;

public class MobileContractValidator implements ContractValidator {
    @Override
    public ValidationResult validateContract(Contract contract) {
        if (!MobileContract.class.equals(contract.getClass())) {
            return new ValidationResult();
        }

        ValidationResult result = new ValidationResult();
        MobileContract mobileContract = (MobileContract) contract;

        if (mobileContract.getSmsCount() < 0 ||
                mobileContract.getMinuteCount() < 0 ||
                mobileContract.getTrafficCount() < 0) {
            result.addError("Contract minutes, sms and traffic count is invalid");
        } else if (mobileContract.getSmsCount() == 0 &&
                mobileContract.getMinuteCount() == 0 &&
                mobileContract.getTrafficCount() == 0) {
            result.addError("Contract minutes, sms and traffic count is invalid");
        }

        return result;
    }
}
