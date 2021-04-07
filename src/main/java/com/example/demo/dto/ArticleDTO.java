package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter

public class ArticleDTO {
    private Integer productId;
    private String name;
    private String brand;
    private Integer quantity;
}
