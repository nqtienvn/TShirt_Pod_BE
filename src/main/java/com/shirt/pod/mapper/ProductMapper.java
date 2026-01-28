package com.shirt.pod.mapper;

import com.shirt.pod.model.dto.request.CreatePrintAreaRequest;
import com.shirt.pod.model.dto.request.CreateProductRequest;
import com.shirt.pod.model.dto.request.CreateProductVariantRequest;
import com.shirt.pod.model.dto.request.UpdatePrintAreaRequest;
import com.shirt.pod.model.dto.request.UpdateProductRequest;
import com.shirt.pod.model.dto.request.UpdateProductVariantRequest;
import com.shirt.pod.model.dto.response.PrintAreaDTO;
import com.shirt.pod.model.dto.response.ProductDetailDTO;
import com.shirt.pod.model.dto.response.ProductDTO;
import com.shirt.pod.model.dto.response.ProductVariantDTO;
import com.shirt.pod.model.entity.BaseProduct;
import com.shirt.pod.model.entity.PrintArea;
import com.shirt.pod.model.entity.ProductVariant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {

    // ========== BaseProduct Mappings ==========

    /**
     * Map BaseProduct to ProductDTO
     */
    ProductDTO toDTO(BaseProduct product);

    /**
     * Map BaseProduct list to ProductDTO list
     */
    List<ProductDTO> toDTOList(List<BaseProduct> products);

    /**
     * Map CreateProductRequest to BaseProduct entity
     */
    BaseProduct toEntity(CreateProductRequest request);

    /**
     * Update BaseProduct from UpdateProductRequest
     */
    void updateEntity(UpdateProductRequest request, @MappingTarget BaseProduct product);

    /**
     * Map BaseProduct to ProductDetailDTO (with variants and printAreas)
     */
    @Mapping(target = "variants", source = "variants")
    @Mapping(target = "printAreas", source = "printAreas")
    ProductDetailDTO toDetailDTO(BaseProduct product);

    // ========== ProductVariant Mappings ==========

    /**
     * Map ProductVariant to ProductVariantDTO
     */
    @Mapping(target = "baseProductId", source = "baseProduct.id")
    ProductVariantDTO toVariantDTO(ProductVariant variant);

    /**
     * Map ProductVariant list to ProductVariantDTO list
     */
    List<ProductVariantDTO> toVariantDTOList(List<ProductVariant> variants);

    /**
     * Map CreateProductVariantRequest to ProductVariant entity
     */
    @Mapping(target = "baseProduct", ignore = true)
    ProductVariant toVariantEntity(CreateProductVariantRequest request);

    /**
     * Update ProductVariant from UpdateProductVariantRequest
     */
    @Mapping(target = "baseProduct", ignore = true)
    void updateVariantEntity(UpdateProductVariantRequest request, @MappingTarget ProductVariant variant);

    // ========== PrintArea Mappings ==========

    /**
     * Map PrintArea to PrintAreaDTO
     */
    @Mapping(target = "baseProductId", source = "baseProduct.id")
    PrintAreaDTO toPrintAreaDTO(PrintArea printArea);

    /**
     * Map PrintArea list to PrintAreaDTO list
     */
    List<PrintAreaDTO> toPrintAreaDTOList(List<PrintArea> printAreas);

    /**
     * Map CreatePrintAreaRequest to PrintArea entity
     */
    @Mapping(target = "baseProduct", ignore = true)
    PrintArea toPrintAreaEntity(CreatePrintAreaRequest request);

    /**
     * Update PrintArea from UpdatePrintAreaRequest
     */
    @Mapping(target = "baseProduct", ignore = true)
    void updatePrintAreaEntity(UpdatePrintAreaRequest request, @MappingTarget PrintArea printArea);
}
