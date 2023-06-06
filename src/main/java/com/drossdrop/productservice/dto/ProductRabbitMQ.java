package com.drossdrop.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRabbitMQ implements Serializable {
    private String id;
    private String name;
    private BigDecimal price;
    private Integer stock;
}
