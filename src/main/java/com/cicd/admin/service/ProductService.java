package com.cicd.admin.service;

import com.cicd.admin.common.BreathingType;
import com.cicd.admin.common.CategoryType;
import com.cicd.admin.dto.MainProductDto;
import com.cicd.admin.dto.PriceCompareDto;
import com.cicd.admin.dto.ProductCategoryDto;
import com.cicd.admin.dto.ProductDto;
import com.cicd.admin.entity.CategoryEntity;
import com.cicd.admin.entity.PriceCompareEntity;
import com.cicd.admin.entity.ProductCategoryEntity;
import com.cicd.admin.entity.ProductEntity;
import com.cicd.admin.mapper.PriceCompareMapper;
import com.cicd.admin.mapper.ProductCategoryMapper;
import com.cicd.admin.mapper.ProductMapper;
import com.cicd.admin.repository.CategoryRepository;
import com.cicd.admin.repository.PriceCompareRepository;
import com.cicd.admin.repository.ProductCategoryRepository;
import com.cicd.admin.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final PriceCompareRepository priceCompareRepository;
    private final PriceCompareMapper priceCompareMapper;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductCategoryMapper productCategoryMapper;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper, PriceCompareRepository priceCompareRepository, PriceCompareMapper priceCompareMapper, ProductCategoryRepository productCategoryRepository, ProductCategoryMapper productCategoryMapper, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.priceCompareRepository = priceCompareRepository;
        this.priceCompareMapper = priceCompareMapper;
        this.productCategoryRepository = productCategoryRepository;
        this.productCategoryMapper = productCategoryMapper;
        this.categoryRepository = categoryRepository;
    }

    public Page<ProductDto> getProductDtos(Pageable pageable, String cateOne, String cateTwo, String cateName) {
        if (cateOne.equals("all") && cateTwo.equals("all") && cateName.equals("all")) {
            return getAllProductDtos(pageable);
        } else {
            BreathingType breathingType = null;
            CategoryType categoryType = null;
            if (cateOne.equals("MOUTH")) {
                breathingType = BreathingType.MOUTH;
            } else if (cateOne.equals("LUNG")) {
                breathingType = BreathingType.LUNG;
            }

            if (cateTwo.equals("BRAND")) {
                categoryType = CategoryType.BRAND;
            } else if (cateTwo.equals("TASTE")) {
                categoryType = CategoryType.TASTE;
            }


            Page<ProductEntity> productEntities = productRepository.findPageByCategory(pageable, breathingType, categoryType, cateName);
            List<ProductDto> productDtoList = productEntities.getContent().stream()
                    .map(item -> productMapper.convertDto(item))
                    .collect(Collectors.toList());
            return new PageImpl<>(productDtoList, pageable, productEntities.getTotalElements());
        }
    }

    public Page<ProductDto> getAllProductDtos(Pageable pageable) {
        Page<ProductEntity> productEntities = productRepository.findAll(pageable);
        List<ProductDto> productDtoList = productEntities.getContent().stream()
                .map(item -> productMapper.convertDto(item))
                .collect(Collectors.toList());
        return new PageImpl<>(productDtoList, pageable, productEntities.getTotalElements());
    }

    public List<MainProductDto> getMainProductDtosByMain() {
        List<ProductEntity> productEntities = productRepository.findAllByShowMain(true);

        List<ProductDto> productDtos = productEntities.stream()
                .map(item -> productMapper.convertDto(item))
                .collect(Collectors.toList());

        List<MainProductDto> mainProductDtos = new ArrayList<>();

        List<MainProductDto> mouthList = getMainProductDtoListByProductDtosAndCategory(productDtos, BreathingType.MOUTH)
                .stream()
                .sorted(Comparator.comparing(MainProductDto::getId).reversed())
                .limit(4).collect(Collectors.toList());
        List<MainProductDto> lungList = getMainProductDtoListByProductDtosAndCategory(productDtos, BreathingType.LUNG)
                .stream()
                .sorted(Comparator.comparing(MainProductDto::getId).reversed())
                .limit(4).collect(Collectors.toList());

        mainProductDtos.addAll(mouthList);
        mainProductDtos.addAll(lungList);

        return mainProductDtos;
    }

    public List<MainProductDto> getProductDtosBySearch(String searchText) {
        List<ProductEntity> productEntities = productRepository.findAllBySearchText(searchText);

        List<ProductDto> productDtos = productEntities.stream()
                .map(item -> productMapper.convertDto(item))
                .collect(Collectors.toList());
        return getMainProductDtoListByProductDtos(productDtos);
    }

    public List<MainProductDto> getProductDtosByCategory(String category) {
        List<ProductEntity> productEntities = productRepository.findAllByShowMain(true);

        List<ProductDto> productDtos = productEntities.stream()
                .map(item -> productMapper.convertDto(item))
                .collect(Collectors.toList());

        List<MainProductDto> mainProductDtos = new ArrayList<>();

        if (category.equals("MOUTH")) {
            mainProductDtos.addAll(getMainProductDtoListByProductDtosAndCategory(productDtos, BreathingType.MOUTH)
                    .stream()
                    .sorted(Comparator.comparing(MainProductDto::getId).reversed())
                    .collect(Collectors.toList()));
        } else if(category.equals("LUNG")) {
            mainProductDtos.addAll(getMainProductDtoListByProductDtosAndCategory(productDtos, BreathingType.LUNG)
                    .stream()
                    .sorted(Comparator.comparing(MainProductDto::getId).reversed())
                    .collect(Collectors.toList()));
        }
        return mainProductDtos;
    }

    public List<MainProductDto> getProductDtosByCategoryId(Long categoryId) {
        List<ProductEntity> productEntities = productRepository.findAllByCategoryId(categoryId);

        List<ProductDto> productDtos = productEntities.stream()
                .map(item -> productMapper.convertDto(item))
                .collect(Collectors.toList());

        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).orElse(null);

        return getMainProductDtoListByProductDtosAndCategory(productDtos, categoryEntity != null ? categoryEntity.getCategoryOne() : null)
                .stream()
                .sorted(Comparator.comparing(MainProductDto::getId).reversed())
                .collect(Collectors.toList());
    }

    public ProductDto getProductDto(Long id) {
        ProductEntity productEntity = productRepository.findById(id).orElse(null);
        ProductDto productDto = null;
        if (productEntity != null) {
            productDto = productMapper.convertDto(productEntity);
        }
        return productDto;
    }

    public ProductDto createProduct(ProductDto productDto) {
        ProductEntity saveEntity = productRepository.save(productMapper.convertEntity(productDto));
        return productMapper.convertDto(saveEntity);
    }

    public ProductDto deleteProduct(ProductDto productDto) {
        ProductEntity entity = productRepository.findById(productDto.getId()).orElse(null);
        if (entity != null) {
            entity.setDeleted(true);
            ProductEntity saveEntity = productRepository.save(entity);

            List<ProductCategoryEntity> productCategoryEntityList = productCategoryRepository.findAllByProductId(productDto.getId());
            productCategoryEntityList.forEach(item -> item.setDeleted(true));
            productCategoryRepository.saveAll(productCategoryEntityList);

            return productMapper.convertDto(saveEntity);
        } else {
            return null;
        }
    }

    public PriceCompareDto createPriceCompare(PriceCompareDto priceCompareDto) {
        PriceCompareEntity entity = priceCompareRepository.save(priceCompareMapper.convertEntity(priceCompareDto));
        return priceCompareMapper.convertDto(entity);
    }

    public PriceCompareDto deletePriceCompare(PriceCompareDto priceCompareDto) {
        PriceCompareEntity entity = priceCompareRepository.findById(priceCompareDto.getId()).orElse(null);
        if (entity != null) {
            entity.setDeleted(true);
            PriceCompareEntity saveEntity = priceCompareRepository.save(entity);
            return priceCompareMapper.convertDto(saveEntity);
        } else {
            return null;
        }
    }

    public List<PriceCompareDto> deletePriceCompareByProductId(Long productId) {
        List<PriceCompareEntity> entities = priceCompareRepository.findAllByProductId(productId);
        entities.forEach(item -> item.setDeleted(true));
        List<PriceCompareEntity> deleteEntity = priceCompareRepository.saveAll(entities);
        return deleteEntity.stream()
                .map(item -> priceCompareMapper.convertDto(item))
                .collect(Collectors.toList());
    }

    public List<PriceCompareDto> createPriceCompareList(List<PriceCompareDto> priceCompareDtos) {
        List<PriceCompareEntity> priceCompareEntityList = priceCompareDtos.stream()
                .map(item -> priceCompareMapper.convertEntity(item))
                .collect(Collectors.toList());

        List<PriceCompareEntity> saveEntity = priceCompareRepository.saveAll(priceCompareEntityList);
        return saveEntity.stream()
                .map(item -> priceCompareMapper.convertDto(item))
                .collect(Collectors.toList());
    }

    public ProductCategoryDto createProductCategory(ProductCategoryDto productCategoryDto) {
        ProductCategoryEntity entity = productCategoryRepository.save(productCategoryMapper.convertEntity(productCategoryDto));
        return productCategoryMapper.convertDto(entity);
    }

    public ProductCategoryDto deleteProductCategory(ProductCategoryDto productCategoryDto) {
        ProductCategoryEntity entity = productCategoryRepository.findById(productCategoryDto.getId()).orElse(null);
        if (entity != null) {
            entity.setDeleted(true);
            ProductCategoryEntity saveEntity = productCategoryRepository.save(entity);
            return productCategoryMapper.convertDto(saveEntity);
        } else {
            return null;
        }
    }

    public List<ProductCategoryDto> deleteProductCategoryByProductId(Long productId) {
        List<ProductCategoryEntity> entities = productCategoryRepository.findAllByProductId(productId);
        entities.forEach(item -> item.setDeleted(true));
        List<ProductCategoryEntity> deleteEntity = productCategoryRepository.saveAll(entities);
        return deleteEntity.stream()
                .map(item -> productCategoryMapper.convertDto(item))
                .collect(Collectors.toList());
    }

    public List<ProductCategoryDto> createProductCategoryList(List<ProductCategoryDto> productCategoryDtos) {
        List<ProductCategoryEntity> productCategoryEntityList = productCategoryDtos.stream()
                .map(item -> productCategoryMapper.convertEntity(item))
                .collect(Collectors.toList());

        List<ProductCategoryEntity> saveEntity = productCategoryRepository.saveAll(productCategoryEntityList);
        return saveEntity.stream()
                .map(item -> productCategoryMapper.convertDto(item))
                .collect(Collectors.toList());
    }

    private List<MainProductDto> getMainProductDtoListByProductDtos(List<ProductDto> productDtos) {
        return productDtos.stream()
                .map(
                        item -> {
                            List<PriceCompareDto> priceCompareDtos = item.getPriceCompareDtos()
                                    .stream()
                                    .sorted(Comparator.comparing(PriceCompareDto::getSitePrice))
                                    .collect(Collectors.toList());

                            BreathingType breathingType = null;
                            List<ProductCategoryDto> productCategoryDtos = item.getProductCategoryDtos();

                            if (productCategoryDtos.size() > 0) {
                                breathingType = productCategoryDtos.get(0).getCategoryOne();
                            }
                            return MainProductDto.builder()
                                    .id(item.getId())
                                    .name(item.getName())
                                    .img(item.getImg())
                                    .compareCnt(item.getPriceCompareDtos().size())
                                    .compareMinPrice(priceCompareDtos.size() > 0 ? priceCompareDtos.get(0).getSitePrice() : 0)
                                    .categoryOne(breathingType)
                                    .build();
                        }
                ).collect(Collectors.toList());
    }

    private List<MainProductDto> getMainProductDtoListByProductDtosAndCategory(List<ProductDto> productDtos, BreathingType breathingType) {
        return productDtos.stream()
                .filter(item ->
                        item.getProductCategoryDtos()
                                .stream().filter(cate -> breathingType.equals(cate.getCategoryOne()))
                                .count() > 0
                )
                .map(
                        item -> {
                            List<PriceCompareDto> priceCompareDtos = item.getPriceCompareDtos()
                                    .stream()
                                    .sorted(Comparator.comparing(PriceCompareDto::getSitePrice))
                                    .collect(Collectors.toList());
                            return MainProductDto.builder()
                                    .id(item.getId())
                                    .name(item.getName())
                                    .img(item.getImg())
                                    .compareCnt(item.getPriceCompareDtos().size())
                                    .compareMinPrice(priceCompareDtos.size() > 0 ? priceCompareDtos.get(0).getSitePrice() : 0)
                                    .categoryOne(breathingType)
                                    .build();
                        }
                ).collect(Collectors.toList());
    }

}
