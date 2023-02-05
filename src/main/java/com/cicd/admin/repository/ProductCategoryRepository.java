package com.cicd.admin.repository;

import com.cicd.admin.entity.ProductCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategoryEntity, Long> {

    List<ProductCategoryEntity> findAllByProductId(Long productId);
    List<ProductCategoryEntity> findAllByCategoryId(Long categoryId);
}
