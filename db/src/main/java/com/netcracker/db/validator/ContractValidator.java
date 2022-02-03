package com.netcracker.db.validator;

import com.netcracker.db.entity.Contract;
import com.netcracker.db.validator.model.ValidationResult;

public interface ContractValidator {
    ValidationResult validateContract(Contract contract);
}
