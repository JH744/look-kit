package com.example.lookkit.product;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CategoryVO {
    private int categoryId;
    private String categoryName;
    private String categoryType;
}
