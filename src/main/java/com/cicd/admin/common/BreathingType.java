package com.cicd.admin.common;

public enum BreathingType {
    MOUTH("입호흡"),
    LUNG("폐호흡");

    private String breathingTypeName;

    private BreathingType(String breathingTypeName) {
        this.breathingTypeName = breathingTypeName;
    }

    public String getBreathingTypeName() {
        return breathingTypeName;
    }
}
