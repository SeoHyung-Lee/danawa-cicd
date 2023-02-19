package com.cicd.admin.repository;

import com.cicd.admin.entity.ShowMainSiteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowMainSiteRepository extends JpaRepository<ShowMainSiteEntity, Long> {

}
