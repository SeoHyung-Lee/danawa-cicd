package com.cicd.admin.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QProductCategoryEntity is a Querydsl query type for ProductCategoryEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QProductCategoryEntity extends EntityPathBase<ProductCategoryEntity> {

    private static final long serialVersionUID = 640022590L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QProductCategoryEntity productCategoryEntity = new QProductCategoryEntity("productCategoryEntity");

    public final QCategoryEntity category;

    public final BooleanPath deleted = createBoolean("deleted");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public QProductCategoryEntity(String variable) {
        this(ProductCategoryEntity.class, forVariable(variable), INITS);
    }

    public QProductCategoryEntity(Path<? extends ProductCategoryEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QProductCategoryEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QProductCategoryEntity(PathMetadata metadata, PathInits inits) {
        this(ProductCategoryEntity.class, metadata, inits);
    }

    public QProductCategoryEntity(Class<? extends ProductCategoryEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.category = inits.isInitialized("category") ? new QCategoryEntity(forProperty("category")) : null;
    }

}

