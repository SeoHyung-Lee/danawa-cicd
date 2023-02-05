package com.cicd.admin.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@Builder
@ToString
public class ProductDto {

    private long id;
    private String name;
    private String img;
    private boolean showMain;
    private List<PriceCompareDto> priceCompareDtos;
    private List<ProductCategoryDto> productCategoryDtos;

}
