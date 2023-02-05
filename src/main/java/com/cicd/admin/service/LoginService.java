package com.cicd.admin.service;

import com.cicd.admin.entity.AdminUserEntity;
import com.cicd.admin.repository.AdminUserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final AdminUserRepository adminUserRepository;

    public LoginService(AdminUserRepository adminUserRepository) {
        this.adminUserRepository = adminUserRepository;
    }

    public Boolean isAdminLoginUser(String id, String pwd) {
        AdminUserEntity adminUserEntity = adminUserRepository.findByUserIdAndUserPwd(id, pwd);

        if (adminUserEntity != null) {
            return true;
        }

        return false;
    }

}
