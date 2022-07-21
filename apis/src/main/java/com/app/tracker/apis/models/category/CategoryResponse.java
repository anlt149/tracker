package com.app.tracker.apis.models.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CategoryResponse {
    private Long id;
    private String code;
    private String name;

    public CategoryResponse(Category category) {
        this.id = category.getId();
        this.code = category.getCode();
        this.name = category.getName();
    }
}
