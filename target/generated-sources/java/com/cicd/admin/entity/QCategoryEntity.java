package com.cicd.admin.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCategoryEntity is a Querydsl query type for CategoryEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCategoryEntity extends EntityPathBase<CategoryEntity> {

    private static final long serialVersionUID = -409972909L;

    public static final QCategoryEntity categoryEntity = new QCategoryEntity("categoryEntity");

    public final StringPath categoryName = createString("categoryName");

    public final EnumPath<com.cicd.admin.common.BreathingType> categoryOne = createEnum("categoryOne", com.cicd.admin.common.BreathingType.class);

    public final EnumPath<com.cicd.admin.common.CategoryType> categoryTwo = createEnum("categoryTwo", com.cicd.admin.common.CategoryType.class);

    public final BooleanPath deleted = createBoolean("deleted");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QCategoryEntity(String variable) {
        super(CategoryEntity.class, forVariable(variable));
    }

    public QCategoryEntity(Path<? extends CategoryEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCategoryEntity(PathMetadata metadata) {
        super(CategoryEntity.class, metadata);
    }

}

