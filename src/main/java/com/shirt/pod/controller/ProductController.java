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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
@Tag(name = "Products", description = "Product management APIs")
public class ProductController {

    private final ProductService productService;

    // ========== Product Endpoints ==========

    @GetMapping
    @Operation(summary = "Get all products", description = "Retrieve list of products with optional active filter")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Success"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ApiResponse<List<ProductDTO>> getAllProducts(
            @Parameter(description = "Filter by active status (true/false/null for all)")
            @RequestParam(required = false) Boolean activeOnly) {
        List<ProductDTO> products = productService.getAllProducts(activeOnly);

        return ApiResponse.<List<ProductDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("Get all products successfully")
                .data(products)
                .build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Retrieve product details by ID")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Success"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Product not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ApiResponse<ProductDTO> getProductById(
            @Parameter(description = "Product ID")
            @PathVariable Long id) {
        ProductDTO product = productService.getProductById(id);

        return ApiResponse.<ProductDTO>builder()
                .code(HttpStatus.OK.value())
                .message("Get product successfully")
                .data(product)
                .build();
    }

    @GetMapping("/{id}/detail")
    @Operation(summary = "Get product detail", description = "Retrieve product details with variants and print areas")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Success"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Product not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ApiResponse<ProductDetailDTO> getProductDetail(
            @Parameter(description = "Product ID")
            @PathVariable Long id) {
        ProductDetailDTO productDetail = productService.getProductDetailById(id);

        return ApiResponse.<ProductDetailDTO>builder()
                .code(HttpStatus.OK.value())
                .message("Get product detail successfully")
                .data(productDetail)
                .build();
    }

    @PostMapping
    @Operation(summary = "Create product", description = "Create a new product (Admin only)")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Product created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request (validation error or duplicate name)"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
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
    @Operation(summary = "Update product", description = "Update product information (Admin only)")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Product updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Product not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request (validation error or duplicate name)"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ApiResponse<ProductDTO> updateProduct(
            @Parameter(description = "Product ID")
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
    @Operation(summary = "Delete product", description = "Delete a product (Admin only)")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Product deleted successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Product not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ApiResponse<Void> deleteProduct(
            @Parameter(description = "Product ID")
            @PathVariable Long id) {
        productService.deleteProduct(id);

        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Product deleted successfully")
                .build();
    }

    @PatchMapping("/{id}/activate")
    @Operation(summary = "Activate product", description = "Activate a product (Admin only)")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Product activated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Product not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ApiResponse<Void> activateProduct(
            @Parameter(description = "Product ID")
            @PathVariable Long id) {
        productService.activateProduct(id);

        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Product activated successfully")
                .build();
    }

    @PatchMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate product", description = "Deactivate a product (Admin only)")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Product deactivated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Product not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ApiResponse<Void> deactivateProduct(
            @Parameter(description = "Product ID")
            @PathVariable Long id) {
        productService.deactivateProduct(id);

        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Product deactivated successfully")
                .build();
    }

    // ========== Variant Endpoints ==========

    @GetMapping("/{productId}/variants")
    @Operation(summary = "Get product variants", description = "Retrieve all variants of a product")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Success"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Product not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ApiResponse<List<ProductVariantDTO>> getVariantsByProductId(
            @Parameter(description = "Product ID")
            @PathVariable Long productId) {
        List<ProductVariantDTO> variants = productService.getVariantsByProductId(productId);

        return ApiResponse.<List<ProductVariantDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("Get product variants successfully")
                .data(variants)
                .build();
    }

    @PostMapping("/{productId}/variants")
    @Operation(summary = "Create product variant", description = "Create a new variant for a product (Admin only)")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Variant created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Product not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request (validation error or duplicate SKU)"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ApiResponse<ProductVariantDTO> createVariant(
            @Parameter(description = "Product ID")
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
    @Operation(summary = "Update product variant", description = "Update variant information (Admin only)")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Variant updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Variant not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request (validation error or duplicate SKU)"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ApiResponse<ProductVariantDTO> updateVariant(
            @Parameter(description = "Variant ID")
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
    @Operation(summary = "Delete product variant", description = "Delete a variant (Admin only)")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Variant deleted successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Variant not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ApiResponse<Void> deleteVariant(
            @Parameter(description = "Variant ID")
            @PathVariable Long variantId) {
        productService.deleteVariant(variantId);

        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Product variant deleted successfully")
                .build();
    }

    // ========== PrintArea Endpoints ==========

    @GetMapping("/{productId}/print-areas")
    @Operation(summary = "Get product print areas", description = "Retrieve all print areas of a product")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Success"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Product not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ApiResponse<List<PrintAreaDTO>> getPrintAreasByProductId(
            @Parameter(description = "Product ID")
            @PathVariable Long productId) {
        List<PrintAreaDTO> printAreas = productService.getPrintAreasByProductId(productId);

        return ApiResponse.<List<PrintAreaDTO>>builder()
                .code(HttpStatus.OK.value())
                .message("Get product print areas successfully")
                .data(printAreas)
                .build();
    }

    @PostMapping("/{productId}/print-areas")
    @Operation(summary = "Create print area", description = "Create a new print area for a product (Admin only)")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Print area created successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Product not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request (validation error)"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ApiResponse<PrintAreaDTO> createPrintArea(
            @Parameter(description = "Product ID")
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
    @Operation(summary = "Update print area", description = "Update print area information (Admin only)")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Print area updated successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Print area not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request (validation error)"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ApiResponse<PrintAreaDTO> updatePrintArea(
            @Parameter(description = "PrintArea ID")
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
    @Operation(summary = "Delete print area", description = "Delete a print area (Admin only)")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Print area deleted successfully"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Print area not found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ApiResponse<Void> deletePrintArea(
            @Parameter(description = "PrintArea ID")
            @PathVariable Long printAreaId) {
        productService.deletePrintArea(printAreaId);

        return ApiResponse.<Void>builder()
                .code(HttpStatus.OK.value())
                .message("Print area deleted successfully")
                .build();
    }
}
