package com.example.diplomapp.enums;

public enum TaskType {
    MANAGEMENT(8.5, 9.0, 9.0, 10.0, 10.0),
    DEVELOPEMENT(6.5, 7.5, 7.0, 4.0, 6.0),
    TESTING(5.5, 6.0, 6.0, 3.0, 7.5),
    ANALYSIS(7.5, 8.0, 8.5, 7.0, 7.0),
    DOCUMENTATION(2.5, 3.0, 2.0, 2.0, 4.0),
    DESIGN(8.0, 9.0, 9.0, 8.0, 10.0);

    private double format;
    private double complexity;
    private double independence;
    private double scale_and_complexity;
    private double add_responsibility;

    TaskType(double coef1, double coef2, double coef3, double coef4, double coef5) {
        format = coef1;
        complexity = coef2;
        independence = coef3;
        scale_and_complexity = coef4;
        add_responsibility = coef5;
    }

    public double getFormat() {
        return format;
    }

    public double getComplexity() {
        return complexity;
    }

    public double getIndependence() {
        return independence;
    }

    public double getScale_and_complexity() {
        return scale_and_complexity;
    }

    public double getAdd_responsibility() {
        return add_responsibility;
    }
}
