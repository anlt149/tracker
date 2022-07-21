package com.app.tracker.apis.repositories;

import com.app.tracker.apis.models.category.Category;
import com.app.tracker.core.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends BaseRepository<Category> {
    Category findCategoryByCode(String code);
    boolean existsCategoryByCode(String code);
}
