package com.cicd.admin.repository;

import com.cicd.admin.entity.PriceCompareEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceCompareRepository extends JpaRepository<PriceCompareEntity, Long> {

    List<PriceCompareEntity> findAllByProductId(Long productId);
}
