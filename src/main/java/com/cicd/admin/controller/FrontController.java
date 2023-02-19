package com.cicd.admin.controller;

import com.cicd.admin.dto.ProductDto;
import com.cicd.admin.service.CategoryService;
import com.cicd.admin.service.ProductService;
import com.cicd.admin.service.ShowMainSiteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FrontController {

    private final CategoryService categoryService;
    private final ProductService productService;
    private final ShowMainSiteService showMainSiteService;

    public FrontController(CategoryService categoryService, ProductService productService, ShowMainSiteService showMainSiteService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.showMainSiteService = showMainSiteService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("prdList", productService.getMainProductDtosByMain());
        model.addAttribute("siteList", showMainSiteService.getShowMainSiteDtosByMain());

        return "./front/main.html";
    }

    @GetMapping("/search")
    public String search(@RequestParam("search_text") String searchText, Model model) {
        model.addAttribute("prdList", productService.getProductDtosBySearch(searchText));

        return "./front/search.html";
    }

    @GetMapping("/category")
    public String category(@RequestParam("category") String category, Model model) {
        model.addAttribute("prdList", productService.getProductDtosByCategory(category));
        model.addAttribute("brandList", categoryService.getCategoryNames(category, "BRAND"));
        model.addAttribute("tasteList", categoryService.getCategoryNames(category, "TASTE"));

        return "./front/category.html";
    }

    @GetMapping("/prd/list")
    public String prdList(Model model, @RequestParam("category_id") Long categoryId) {
        model.addAttribute("prdList", productService.getProductDtosByCategoryId(categoryId));

        return "./front/prd-list.html";
    }

    @GetMapping("/prd/detail")
    public String prdDetail(Model model, @RequestParam("prd_id") Long prdId) {
        ProductDto productDto = productService.getProductDto(prdId);

        model.addAttribute("product", productDto);
        return "./front/prd-detail.html";
    }

}
