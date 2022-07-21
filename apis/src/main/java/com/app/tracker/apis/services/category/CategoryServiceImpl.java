package com.app.tracker.apis.services.category;

import com.app.tracker.apis.models.category.Category;
import com.app.tracker.apis.models.category.CategoryRequest;
import com.app.tracker.apis.repositories.CategoryRepository;
import com.app.tracker.core.exceptions.BaseError;
import com.app.tracker.core.exceptions.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Page<Category> list(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public Category create(CategoryRequest categoryRequest) {
        if (categoryRepository.existsCategoryByCode(categoryRequest.getCode())) {
            throw new BaseException(new BaseError(HttpStatus.BAD_REQUEST,
                                                  "Existing category with id: " + categoryRequest.getCode()));
        }

        return categoryRepository.save(Category.builder()
                                               .code(categoryRequest.getCode())
                                               .name(categoryRequest.getName())
                                               .build());
    }

    @Override
    public Category getCategory(Long categoryId) {
        return null;
    }

    @Override
    public long delete(Long categoryId) {
        return 0;
    }

    @Override
    public long update(Long categoryId) {
        return 0;
    }
}
