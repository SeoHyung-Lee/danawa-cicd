package com.cicd.admin.controller;

import com.cicd.admin.dto.CategoryDto;
import com.cicd.admin.service.CategoryService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<CategoryDto>> getCategoryDtos(Pageable pageable) {
        List<CategoryDto> categoryDtos = categoryService.getCategoryDtos(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(categoryDtos);
    }

    @GetMapping(value = "/find")
    public ResponseEntity<List<CategoryDto>> getCategoryNames(@RequestParam("cate_one") String cateOne, @RequestParam("cate_two") String cateTwo) {
        List<CategoryDto> categoryNames = categoryService.getCategoryNames(cateOne, cateTwo);
        return ResponseEntity.status(HttpStatus.OK).body(categoryNames);
    }

    /**
     * {
     *   "categoryName": "스웨거 테스트_입",
     *   "categoryOne": "MOUTH",
     *   "categoryTwo": "BRAND"
     * }
     * @param categoryDto
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/create")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.createCategory(categoryDto));
    }

    /**
     * {
     *   "id": 6
     * }
     * @param categoryDto
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/delete")
    public ResponseEntity<CategoryDto> deleteCategory(@RequestBody CategoryDto categoryDto) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(categoryService.deleteCategory(categoryDto));
    }

}
