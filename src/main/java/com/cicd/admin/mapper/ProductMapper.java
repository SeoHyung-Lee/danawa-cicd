package com.cicd.admin.mapper;

import com.cicd.admin.dto.PriceCompareDto;
import com.cicd.admin.dto.ProductCategoryDto;
import com.cicd.admin.dto.ProductDto;
import com.cicd.admin.entity.ProductEntity;
import com.cicd.admin.repository.PriceCompareRepository;
import com.cicd.admin.repository.ProductCategoryRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    private final PriceCompareRepository priceCompareRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryMapper productCategoryMapper;
    private final PriceCompareMapper priceCompareMapper;

    public ProductMapper(PriceCompareRepository priceCompareRepository, ProductCategoryRepository productCategoryRepository, ProductCategoryMapper productCategoryMapper, PriceCompareMapper priceCompareMapper) {
        this.priceCompareRepository = priceCompareRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.productCategoryMapper = productCategoryMapper;
        this.priceCompareMapper = priceCompareMapper;
    }

    public ProductDto convertDto(ProductEntity entity) {
        List<ProductCategoryDto> productCategoryDtos = productCategoryRepository.findAllByProductId(entity.getId()).stream()
                .map(item -> productCategoryMapper.convertDto(item)).collect(Collectors.toList());
        List<PriceCompareDto> priceCompareDtos = priceCompareRepository.findAllByProductId(entity.getId()).stream()
                .map(item -> priceCompareMapper.convertDto(item)).collect(Collectors.toList());

        String img = entity.getImg() != null ? new String(entity.getImg()) : "";

        return ProductDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .img(img)
                .showMain(entity.getShowMain())
                .productCategoryDtos(productCategoryDtos)
                .priceCompareDtos(priceCompareDtos)
                .build();
    }

    public ProductEntity convertEntity(ProductDto dto) {
        return ProductEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .img(dto.getImg().getBytes())
                .showMain(dto.isShowMain())
                .deleted(false)
                .build();
    }
}
