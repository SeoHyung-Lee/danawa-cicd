package com.cicd.admin.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QPriceCompareEntity is a Querydsl query type for PriceCompareEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QPriceCompareEntity extends EntityPathBase<PriceCompareEntity> {

    private static final long serialVersionUID = -1477318095L;

    public static final QPriceCompareEntity priceCompareEntity = new QPriceCompareEntity("priceCompareEntity");

    public final BooleanPath deleted = createBoolean("deleted");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> productId = createNumber("productId", Long.class);

    public final StringPath siteLink = createString("siteLink");

    public final StringPath siteName = createString("siteName");

    public final NumberPath<Long> sitePrice = createNumber("sitePrice", Long.class);

    public QPriceCompareEntity(String variable) {
        super(PriceCompareEntity.class, forVariable(variable));
    }

    public QPriceCompareEntity(Path<? extends PriceCompareEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPriceCompareEntity(PathMetadata metadata) {
        super(PriceCompareEntity.class, metadata);
    }

}

