package com.cicd.admin.mapper;

import com.cicd.admin.dto.ShowMainSiteDto;
import com.cicd.admin.entity.ShowMainSiteEntity;
import org.springframework.stereotype.Component;

@Component
public class ShowMainSiteMapper {

    public ShowMainSiteDto convertDto(ShowMainSiteEntity entity) {
        return ShowMainSiteDto.builder()
                .id(entity.getId())
                .siteName(entity.getSiteName())
                .siteLink(entity.getSiteLink())
                .showMain(entity.getShowMain())
                .build();
    }

    public ShowMainSiteEntity convertEntity(ShowMainSiteDto dto) {
        return ShowMainSiteEntity.builder()
                .id(dto.getId())
                .siteName(dto.getSiteName())
                .siteLink(dto.getSiteLink())
                .showMain(dto.isShowMain())
                .deleted(false)
                .build();
    }
}
