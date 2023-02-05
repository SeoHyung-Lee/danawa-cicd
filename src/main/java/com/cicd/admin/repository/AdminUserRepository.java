package com.cicd.admin.repository;

import com.cicd.admin.common.BreathingType;
import com.cicd.admin.common.CategoryType;
import com.cicd.admin.entity.AdminUserEntity;
import com.cicd.admin.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUserEntity, Long> {

    AdminUserEntity findByUserIdAndUserPwd(String userId, String userPwd);

}
