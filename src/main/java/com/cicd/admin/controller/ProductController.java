package com.cicd.admin.controller;

import com.cicd.admin.dto.PriceCompareDto;
import com.cicd.admin.dto.ProductCategoryDto;
import com.cicd.admin.dto.ProductDto;
import com.cicd.admin.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<Page<ProductDto>> getProductLists(Pageable pageable) {
        Page<ProductDto> productDtos = productService.getAllProductDtos(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(productDtos);
    }

    /**
     * {
     *   "img": "",
     *   "name": "스웨거 테스트"
     * }
     * @param productDto
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/create")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(productService.createProduct(productDto));
    }

    /**
     * {
     *   "id": 1
     * }
     * @param productDto
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/delete")
    public ResponseEntity<ProductDto> deleteProduct(@RequestBody ProductDto productDto) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProduct(productDto));
    }

    /**
     * {
     *   "productId": 2,
     *   "siteLink": "http://www.naver.com",
     *   "siteName": "네이버",
     *   "sitePrice": 1000
     * }
     * @param priceCompareDto
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/compare/create")
    public ResponseEntity<PriceCompareDto> createPriceCompare(@RequestBody PriceCompareDto priceCompareDto) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(productService.createPriceCompare(priceCompareDto));
    }

    /**
     * {
     *   "id": 1
     * }
     * @param priceCompareDto
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/compare/delete")
    public ResponseEntity<PriceCompareDto> deletePriceCompare(@RequestBody PriceCompareDto priceCompareDto) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(productService.deletePriceCompare(priceCompareDto));
    }

    @PostMapping(value = "/compare/delete/{prdId}")
    public ResponseEntity<List<PriceCompareDto>> deletePriceCompare(@PathVariable Long prdId) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(productService.deletePriceCompareByProductId(prdId));
    }

    @PostMapping(value = "/compare/update")
    public ResponseEntity<List<PriceCompareDto>> updatePriceCompare(@RequestBody List<PriceCompareDto> priceCompareDto) throws Exception {
        productService.deletePriceCompareByProductId(priceCompareDto.get(0).getProductId());
        return ResponseEntity.status(HttpStatus.OK).body(productService.createPriceCompareList(priceCompareDto));
    }

    /**
     * {
     *   "productId": 2,
     *   "categoryId": 1
     * }
     * @param productCategoryDto
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/category/create")
    public ResponseEntity<ProductCategoryDto> createProductCategory(@RequestBody ProductCategoryDto productCategoryDto) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(productService.createProductCategory(productCategoryDto));
    }

    @PostMapping(value = "/category/delete/{prdId}")
    public ResponseEntity<List<ProductCategoryDto>> deleteProductCategory(@PathVariable Long prdId) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProductCategoryByProductId(prdId));
    }

    @PostMapping(value = "/category/update")
    public ResponseEntity<List<ProductCategoryDto>> updateProductCategory(@RequestBody List<ProductCategoryDto> productCategoryDtos) throws Exception {
        productService.deleteProductCategoryByProductId(productCategoryDtos.get(0).getProductId());
        return ResponseEntity.status(HttpStatus.OK).body(productService.createProductCategoryList(productCategoryDtos));
    }
}