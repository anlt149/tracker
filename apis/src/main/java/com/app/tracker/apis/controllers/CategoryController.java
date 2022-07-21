package com.app.tracker.apis.controllers;

import com.app.tracker.apis.models.category.Category;
import com.app.tracker.apis.models.category.CategoryRequest;
import com.app.tracker.apis.models.category.CategoryResponse;
import com.app.tracker.apis.services.category.CategoryService;
import com.app.tracker.core.models.PageRequest;
import com.app.tracker.core.models.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseBody
    ResponseEntity<PageResponse<CategoryResponse>> getCategories(PageRequest request) {
        Page<Category> page = categoryService.list(request.toPageable());

        PageResponse<CategoryResponse> pageResponse = new PageResponse<>(
                page.getContent()
                        .stream()
                        .map(CategoryResponse::new)
                        .collect(Collectors.toList()), page.getTotalElements(),
                page.getTotalPages());

        return new ResponseEntity<>(pageResponse, HttpStatus.OK);
    }


    @PostMapping
    @ResponseBody
    ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest categoryRequest) {
        return ResponseEntity.ok(
                new CategoryResponse(categoryService.create(categoryRequest)));
    }

}
