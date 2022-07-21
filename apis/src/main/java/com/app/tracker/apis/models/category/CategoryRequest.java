package com.app.tracker.apis.models.category;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CategoryRequest {
    private String name;
    private String code;
}
