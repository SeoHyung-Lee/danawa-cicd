package com.cicd.admin.mapper;

import com.cicd.admin.dto.CategoryDto;
import com.cicd.admin.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDto convertDto(CategoryEntity entity) {
        return CategoryDto.builder()
                .id(entity.getId())
                .categoryName(entity.getCategoryName())
                .categoryOne(entity.getCategoryOne())
                .categoryTwo(entity.getCategoryTwo())
                .build();
    }

    public CategoryEntity convertEntity(CategoryDto dto) {
        return CategoryEntity.builder()
                .id(dto.getId())
                .categoryName(dto.getCategoryName().replaceAll(" ",""))
                .categoryOne(dto.getCategoryOne())
                .categoryTwo(dto.getCategoryTwo())
                .deleted(false)
                .build();
    }
}
