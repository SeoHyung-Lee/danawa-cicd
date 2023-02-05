package com.cicd.admin.mapper;

import com.cicd.admin.dto.PriceCompareDto;
import com.cicd.admin.entity.PriceCompareEntity;
import org.springframework.stereotype.Component;

@Component
public class PriceCompareMapper {

    public PriceCompareDto convertDto(PriceCompareEntity entity) {
        return PriceCompareDto.builder()
                .id(entity.getId())
                .productId(entity.getProductId())
                .siteName(entity.getSiteName())
                .siteLink(entity.getSiteLink())
                .sitePrice(entity.getSitePrice())
                .build();
    }

    public PriceCompareEntity convertEntity(PriceCompareDto dto) {
        return PriceCompareEntity.builder()
                .id(dto.getId())
                .productId(dto.getProductId())
                .siteName(dto.getSiteName())
                .siteLink(dto.getSiteLink())
                .sitePrice(dto.getSitePrice())
                .deleted(false)
                .build();
    }

}
