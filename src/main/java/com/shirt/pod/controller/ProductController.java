package com.shirt.pod.controller;

import com.shirt.pod.model.dto.request.CreatePrintAreaRequest;
import com.shirt.pod.model.dto.request.CreateProductRequest;
import com.shirt.pod.model.dto.request.CreateProductVariantRequest;
import com.shirt.pod.model.dto.request.UpdatePrintAreaRequest;
import com.shirt.pod.model.dto.request.UpdateProductRequest;
import com.shirt.pod.model.dto.request.UpdateProductVariantRequest;
import com.shirt.pod.model.dto.response.ApiResponse;
import com.shirt.pod.model.dto.response.PrintAreaDTO;
import com.shirt.pod.model.dto.response.ProductDetailDTO;
import com.shirt.pod.model.dto.response.ProductDTO;
import com.shirt.pod.model.dto.response.ProductVariantDTO;
import com.shirt.pod.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    // ========== Product Endpoints ==========

    @GetMapping
    public ApiResponse<List<ProductDTO>> getAllProducts(
            @RequestParam(required = false) Boolean activeOnly) {
        List<ProductDTO> products = productService.getAllProducts(activeOnly);

        return ApiResponse.<List<ProductDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("Get all products successfully")
                .data(products)
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductDTO> getProductById(
            @PathVariable Long id) {
        ProductDTO product = productService.getProductById(id);

        return ApiResponse.<ProductDTO>builder()
                .code(HttpStatus.OK.value())
                .message("Get product successfully")
                .data(product)
                .build();
    }

    @GetMapping("/{id}/detail")
    public ApiResponse<ProductDetailDTO> getProductDetail(
            @PathVariable Long id) {
        ProductDetailDTO productDetail = productService.getProductDetailById(id);

        return ApiResponse.<ProductDetailDTO>builder()
                .code(HttpStatus.OK.value())
                .message("Get product detail successfully")
                .data(productDetail)
                .build();
    }

    @PostMapping
    public ApiResponse<ProductDTO> createProduct(
            @Valid @RequestBody CreateProductRequest request) {
        ProductDTO product = productService.createProduct(request);

        return ApiResponse.<ProductDTO>builder()
                .code(HttpStatus.CREATED.value())
                .message("Product created successfully")
                .data(product)
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductDTO> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductRequest request) {
        ProductDTO product = productService.updateProduct(id, request);

        return ApiResponse.<ProductDTO>builder()
                .code(HttpStatus.OK.value())
                .message("Product updated successfully")
                .data(product)
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteProduct(
            @PathVariable Long id) {
        productService.deleteProduct(id);

        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Product deleted successfully")
                .build();
    }

    @PatchMapping("/{id}/activate")
    public ApiResponse<Void> activateProduct(
            @PathVariable Long id) {
        productService.activateProduct(id);

        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Product activated successfully")
                .build();
    }

    @PatchMapping("/{id}/deactivate")
    public ApiResponse<Void> deactivateProduct(
            @PathVariable Long id) {
        productService.deactivateProduct(id);

        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Product deactivated successfully")
                .build();
    }

    // ========== Variant Endpoints ==========

    @GetMapping("/{productId}/variants")
    public ApiResponse<List<ProductVariantDTO>> getVariantsByProductId(
            @PathVariable Long productId) {
        List<ProductVariantDTO> variants = productService.getVariantsByProductId(productId);

        return ApiResponse.<List<ProductVariantDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("Get product variants successfully")
                .data(variants)
                .build();
    }

    @PostMapping("/{productId}/variants")
    public ApiResponse<ProductVariantDTO> createVariant(
            @PathVariable Long productId,
            @Valid @RequestBody CreateProductVariantRequest request) {
        ProductVariantDTO variant = productService.createVariant(productId, request);

        return ApiResponse.<ProductVariantDTO>builder()
                .code(HttpStatus.CREATED.value())
                .message("Product variant created successfully")
                .data(variant)
                .build();
    }

    @PutMapping("/variants/{variantId}")
    public ApiResponse<ProductVariantDTO> updateVariant(
            @PathVariable Long variantId,
            @Valid @RequestBody UpdateProductVariantRequest request) {
        ProductVariantDTO variant = productService.updateVariant(variantId, request);

        return ApiResponse.<ProductVariantDTO>builder()
                .code(HttpStatus.OK.value())
                .message("Product variant updated successfully")
                .data(variant)
                .build();
    }

    @DeleteMapping("/variants/{variantId}")
    public ApiResponse<Void> deleteVariant(
            @PathVariable Long variantId) {
        productService.deleteVariant(variantId);

        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Product variant deleted successfully")
                .build();
    }

    // ========== PrintArea Endpoints ==========

    @GetMapping("/{productId}/print-areas")
    public ApiResponse<List<PrintAreaDTO>> getPrintAreasByProductId(
            @PathVariable Long productId) {
        List<PrintAreaDTO> printAreas = productService.getPrintAreasByProductId(productId);

        return ApiResponse.<List<PrintAreaDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("Get product print areas successfully")
                .data(printAreas)
                .build();
    }

    @PostMapping("/{productId}/print-areas")
    public ApiResponse<PrintAreaDTO> createPrintArea(
            @PathVariable Long productId,
            @Valid @RequestBody CreatePrintAreaRequest request) {
        PrintAreaDTO printArea = productService.createPrintArea(productId, request);

        return ApiResponse.<PrintAreaDTO>builder()
                .code(HttpStatus.CREATED.value())
                .message("Print area created successfully")
                .data(printArea)
                .build();
    }

    @PutMapping("/print-areas/{printAreaId}")
    public ApiResponse<PrintAreaDTO> updatePrintArea(
            @PathVariable Long printAreaId,
            @Valid @RequestBody UpdatePrintAreaRequest request) {
        PrintAreaDTO printArea = productService.updatePrintArea(printAreaId, request);

        return ApiResponse.<PrintAreaDTO>builder()
                .code(HttpStatus.OK.value())
                .message("Print area updated successfully")
                .data(printArea)
                .build();
    }

    @DeleteMapping("/print-areas/{printAreaId}")
    public ApiResponse<Void> deletePrintArea(
            @PathVariable Long printAreaId) {
        productService.deletePrintArea(printAreaId);

        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Print area deleted successfully")
                .build();
    }
}
