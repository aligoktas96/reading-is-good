package com.productservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO implements Serializable {

    @NotEmpty
    private String name;

    @NotEmpty
    private String author;

    @NotEmpty
    private Double price;

    @NotEmpty
    @Min(value = 0)
    private Long stock;
}
