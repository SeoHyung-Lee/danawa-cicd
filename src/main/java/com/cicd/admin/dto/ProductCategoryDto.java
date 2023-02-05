package com.cicd.admin.dto;

import com.cicd.admin.common.BreathingType;
import com.cicd.admin.common.CategoryType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ProductCategoryDto {

    private long id;
    private long productId;
    private long categoryId;
    private String categoryName;
    private BreathingType categoryOne;
    private CategoryType categoryTwo;

}
