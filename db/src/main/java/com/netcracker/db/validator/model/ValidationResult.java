package com.netcracker.db.validator.model;

import com.netcracker.utils.List;
import com.netcracker.utils.impl.MyArrayList;

public class ValidationResult {
    private ValidationEnum result;
    private List<String> validationErrors;

    public ValidationResult() {
        result = ValidationEnum.OK;
        validationErrors = new MyArrayList<>();
    }

    public ValidationEnum getResult() {
        return result;
    }

    public void setResult(ValidationEnum result) {
        if (this.result.equals(ValidationEnum.ERR)) return;

        this.result = result;
    }

    public void addError(String error) {
        result = ValidationEnum.ERR;

        validationErrors.add(error);
    }

    public void addAllError(List<String> errors) {
        if (!errors.isEmpty()) result = ValidationEnum.ERR;

        for (String error : errors) {
            validationErrors.add(error);
        }
    }

    public int errorsCount() {
        return validationErrors.size();
    }

    public String getError(int index) {
        return validationErrors.get(index);
    }

    public void setError(int index, String error) {
        validationErrors.set(index, error);
    }
}
