package com.app.tracker.apis.repositories;

import com.app.tracker.apis.models.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findCategoryByCode(String code);
    boolean existsCategoryByCode(String code);
}
