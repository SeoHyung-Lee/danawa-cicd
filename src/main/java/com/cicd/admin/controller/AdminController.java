package com.cicd.admin.controller;

import com.cicd.admin.dto.CategoryDto;
import com.cicd.admin.dto.ProductDto;
import com.cicd.admin.service.CategoryService;
import com.cicd.admin.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final CategoryService categoryService;
    private final ProductService productService;

    public AdminController(CategoryService categoryService, ProductService productService) {
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @GetMapping("/login")
    public String login() { return "./admin/login.html"; }

    @GetMapping("/cate/edit")
    public String cateEdit(Model model, HttpServletRequest request) {
        if (!isLoginUserChk(request)) {
            return "./admin/login.html";
        }
        List<CategoryDto> categoryDtoList = categoryService.getCategoryDtos();
        model.addAttribute("categoryList",categoryDtoList);

        return "./admin/cate_edit.html";
    }

    @GetMapping("/prd/list")
    public String prdList(Model model, Pageable pageable
                          , @RequestParam("cate_one") String cateOne
                          , @RequestParam("cate_two") String cateTwo
                          , @RequestParam("cate_name") String cateName
                          , HttpServletRequest request
    ) {
        if (!isLoginUserChk(request)) {
            return "./admin/login.html";
        }

        Page<ProductDto> productDtoPage = productService.getProductDtos(pageable, cateOne, cateTwo, cateName);

        model.addAttribute("productDtoList", productDtoPage.getContent());
        model.addAttribute("totalPage", productDtoPage.getTotalPages());

        return "./admin/prd_list.html";
    }

    @GetMapping("/prd/add")
    public String prdAdd(HttpServletRequest request) {
        if (!isLoginUserChk(request)) {
            return "./admin/login.html";
        }
        return "/admin/prd_add.html";
    }

    @GetMapping("/prd/edit")
    public String prdEdit(Model model, @RequestParam("prd_id") Long prdId, HttpServletRequest request) {
        if (!isLoginUserChk(request)) {
            return "./admin/login.html";
        }

        ProductDto productDto = productService.getProductDto(prdId);

        model.addAttribute("product", productDto);

        return "./admin/prd_edit.html";
    }

    private Boolean isLoginUserChk(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return false;
        }

        Cookie isLoginUser = Arrays.stream(cookies)
                .filter(item -> item.getName().equals("isLoginUser"))
                .findFirst().orElse(null);
        if (isLoginUser == null || isLoginUser.getValue() == "") {
            return false;
        } else {
            return true;
        }
    }

}
