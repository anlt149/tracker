package com.app.tracker.apis.services.category;

import com.app.tracker.apis.models.category.Category;
import com.app.tracker.apis.models.category.CategoryRequest;
import com.app.tracker.core.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService extends BaseService<Category> {
    Category create(CategoryRequest categoryRequest);
    long update(Long categoryId);
}
