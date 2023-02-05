package com.cicd.admin.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.cicd.admin.common.BreathingType;
import com.cicd.admin.common.CategoryType;
import com.cicd.admin.entity.ProductEntity;
import com.cicd.admin.entity.QProductCategoryEntity;
import com.cicd.admin.entity.QProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepositoryCustorm {
    @Autowired
    EntityManager entityManager;

    private QProductEntity qProduct = QProductEntity.productEntity;
    private QProductCategoryEntity qProductCategory = QProductCategoryEntity.productCategoryEntity;

    @Override
    public Page<ProductEntity> findPageByCategory(Pageable pageable, BreathingType cateOne, CategoryType cateTwo, String cateName) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);

        Long count = jpaQueryFactory.select(qProduct)
                .from(qProduct)
                .innerJoin(qProductCategory).on(qProduct.id.eq(qProductCategory.productId))
                .where(makeWhere(cateOne, cateTwo, cateName))
                .distinct()
                .fetchCount();

        List<ProductEntity> productEntities = jpaQueryFactory.select(qProduct)
                .from(qProduct)
                .innerJoin(qProductCategory).on(qProduct.id.eq(qProductCategory.productId))
                .where(makeWhere(cateOne, cateTwo, cateName))
                .distinct()
                .orderBy(new OrderSpecifier<>(Order.DESC, qProduct.id))
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetch();

        return new PageImpl<>(productEntities, pageable, count);
    }

    @Override
    public List<ProductEntity> findAllBySearchText(String searchText) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        return jpaQueryFactory.select(qProduct)
                .from(qProduct)
                .where(qProduct.showMain.eq(Boolean.TRUE)
                .and(qProduct.name.contains(searchText)))
                .fetch();
    }

    @Override
    public List<ProductEntity> findAllByCategoryId(Long categoryId) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        return jpaQueryFactory.select(qProduct)
                .from(qProduct)
                .innerJoin(qProductCategory).on(qProduct.id.eq(qProductCategory.productId))
                .where(qProduct.showMain.eq(Boolean.TRUE)
                .and(qProductCategory.category.id.eq(categoryId)))
                .distinct()
                .fetch();
    }

    private BooleanBuilder makeWhere(BreathingType cateOne, CategoryType cateTwo, String cateName) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (cateOne != null) {
            booleanBuilder.and(qProductCategory.category.categoryOne.eq(cateOne));
        }

        if (cateTwo != null) {
            booleanBuilder.and(qProductCategory.category.categoryTwo.eq(cateTwo));
        }

        if (!cateName.equals("all")) {
            booleanBuilder.and(qProductCategory.category.categoryName.eq(cateName));
        }

        booleanBuilder.and(qProductCategory.deleted.isFalse());

        return booleanBuilder;
    }
}
