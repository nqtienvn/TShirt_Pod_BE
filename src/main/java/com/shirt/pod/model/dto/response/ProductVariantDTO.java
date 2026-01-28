package com.shirt.pod.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductVariantDTO implements Serializable {

    Long id;
    Long baseProductId;
    String colorName;
    String colorHex;
    String size;
    String sku;
    Integer stockQuantity;
    String imageUrl;
    BigDecimal priceAdjustment;
    Boolean active;
}
