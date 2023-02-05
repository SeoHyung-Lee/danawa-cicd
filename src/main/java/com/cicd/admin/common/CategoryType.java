package com.cicd.admin.common;

public enum CategoryType {
    BRAND("브랜드"),
    TASTE("맛");

    private String categoryTypeName;

    private CategoryType(String categoryTypeName) {
        this.categoryTypeName = categoryTypeName;
    }

    public String getCategoryTypeName() {
        return categoryTypeName;
    }
}
