package com.cicd.admin.common;

public enum CategoryType {
    BRAND("λΈλλ"),
    TASTE("λ§");

    private String categoryTypeName;

    private CategoryType(String categoryTypeName) {
        this.categoryTypeName = categoryTypeName;
    }

    public String getCategoryTypeName() {
        return categoryTypeName;
    }
}
