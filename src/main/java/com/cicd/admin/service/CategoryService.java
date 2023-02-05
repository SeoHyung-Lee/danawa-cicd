package com.cicd.admin.service;

import com.cicd.admin.common.BreathingType;
import com.cicd.admin.common.CategoryType;
import com.cicd.admin.dto.CategoryDto;
import com.cicd.admin.entity.CategoryEntity;
import com.cicd.admin.entity.ProductCategoryEntity;
import com.cicd.admin.mapper.CategoryMapper;
import com.cicd.admin.repository.CategoryRepository;
import com.cicd.admin.repository.ProductCategoryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final ProductCategoryRepository productCategoryRepository;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper, ProductCategoryRepository productCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.productCategoryRepository = productCategoryRepository;
    }

    public List<CategoryDto> getCategoryNames(String cateOne, String cateTwo) {
        BreathingType breathingType = null;
        CategoryType categoryType = null;
        if (cateOne.equals("MOUTH")) {
            breathingType = BreathingType.MOUTH;
        } else if (cateOne.equals("LUNG")) {
            breathingType = BreathingType.LUNG;
        }

        if (cateTwo.equals("BRAND")) {
            categoryType = CategoryType.BRAND;
        } else if (cateTwo.equals("TASTE")) {
            categoryType = CategoryType.TASTE;
        }

        return categoryRepository.findAllByCategoryOneAndCategoryTwo(breathingType, categoryType)
                .stream()
                .map(item -> categoryMapper.convertDto(item))
                .collect(Collectors.toList());
    }

    public List<CategoryDto> getCategoryDtos(Pageable pageable) {
        Page<CategoryEntity> categoryEntities = categoryRepository.findAll(pageable);
        return categoryEntities.getContent().stream()
                .map(item -> categoryMapper.convertDto(item))
                .collect(Collectors.toList());
    }

    public List<CategoryDto> getCategoryDtos() {
        List<CategoryEntity> categoryEntities = categoryRepository.findAll();
        return categoryEntities.stream()
                .map(item -> categoryMapper.convertDto(item))
                .collect(Collectors.toList());
    }

    public CategoryDto createCategory(CategoryDto categoryDto) {
        CategoryEntity saveEntity = categoryRepository.save(categoryMapper.convertEntity(categoryDto));
        return categoryMapper.convertDto(saveEntity);
    }

    public CategoryDto deleteCategory(CategoryDto categoryDto) {
        CategoryEntity entity = categoryRepository.findById(categoryDto.getId()).orElse(null);
        if (entity != null) {
            entity.setDeleted(true);

            List<ProductCategoryEntity> productCategoryEntityList = productCategoryRepository.findAllByCategoryId(categoryDto.getId());
            productCategoryEntityList.forEach(item -> item.setDeleted(true));
            productCategoryRepository.saveAll(productCategoryEntityList);

            CategoryEntity saveEntity = categoryRepository.save(entity);
            return categoryMapper.convertDto(saveEntity);
        } else {
            return null;
        }
    }
}
