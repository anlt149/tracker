package com.app.tracker.apis.services.category;

import com.app.tracker.apis.models.category.Category;
import com.app.tracker.apis.models.category.CategoryRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    Page<Category> list(Pageable pageable);
    Category create(CategoryRequest categoryRequest);
    Category getCategory(Long categoryId);
    long delete(Long categoryId);
    long update(Long categoryId);
}
