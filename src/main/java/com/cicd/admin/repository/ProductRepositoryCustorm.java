package com.cicd.admin.repository;

import com.cicd.admin.common.BreathingType;
import com.cicd.admin.common.CategoryType;
import com.cicd.admin.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepositoryCustorm {
    Page<ProductEntity> findPageByCategory(Pageable pageable, BreathingType cateOne, CategoryType cateTwo, String cateName);
    List<ProductEntity> findAllBySearchText(String searchText);
    List<ProductEntity> findAllByCategoryId(Long categoryId);
}
