package com.cicd.admin.mapper;

import com.cicd.admin.dto.ProductCategoryDto;
import com.cicd.admin.entity.CategoryEntity;
import com.cicd.admin.entity.ProductCategoryEntity;
import com.cicd.admin.repository.CategoryRepository;
import org.springframework.stereotype.Component;

@Component
public class ProductCategoryMapper {

    private final CategoryRepository categoryRepository;

    public ProductCategoryMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public ProductCategoryDto convertDto(ProductCategoryEntity entity) {
        return ProductCategoryDto.builder()
                .id(entity.getId())
                .productId(entity.getProductId())
                .categoryId(entity.getCategory().getId())
                .categoryName(entity.getCategory().getCategoryName())
                .categoryOne(entity.getCategory().getCategoryOne())
                .categoryTwo(entity.getCategory().getCategoryTwo())
                .build();
    }

    public ProductCategoryEntity convertEntity(ProductCategoryDto dto) {
        return ProductCategoryEntity.builder()
                .id(dto.getId())
                .productId(dto.getProductId())
                .category(categoryRepository.findById(dto.getCategoryId()).orElse(new CategoryEntity()))
                .deleted(false)
                .build();
    }

}
