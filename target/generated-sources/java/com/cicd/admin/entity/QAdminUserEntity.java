package com.cicd.admin.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAdminUserEntity is a Querydsl query type for AdminUserEntity
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAdminUserEntity extends EntityPathBase<AdminUserEntity> {

    private static final long serialVersionUID = 1429725611L;

    public static final QAdminUserEntity adminUserEntity = new QAdminUserEntity("adminUserEntity");

    public final BooleanPath deleted = createBoolean("deleted");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath name = createString("name");

    public final StringPath userId = createString("userId");

    public final StringPath userPwd = createString("userPwd");

    public QAdminUserEntity(String variable) {
        super(AdminUserEntity.class, forVariable(variable));
    }

    public QAdminUserEntity(Path<? extends AdminUserEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAdminUserEntity(PathMetadata metadata) {
        super(AdminUserEntity.class, metadata);
    }

}

