package com.example.diplomapp.enums;

public enum  EmploymentCriteriaEnum {
    FORMAT(0.3),
    COMPLEXITY(0.15),
    INDEPENDENCE(0.25),
    SCALE_AND_COMPLEXITY(0.15),
    ADDITIONAL_RESPONSIBILITY(0.15);

    private double value;

    EmploymentCriteriaEnum(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
