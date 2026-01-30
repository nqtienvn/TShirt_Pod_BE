package com.shirt.pod.service;

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

import java.util.List;

public interface ProductService {

    // ========== Product CRUD ==========

    /**
     * Lấy danh sách tất cả sản phẩm
     * @param activeOnly Nếu true, chỉ lấy sản phẩm đang active
     * @return Danh sách ProductDTO
     */
    List<ProductDTO> getAllProducts(Boolean activeOnly);

    /**
     * Lấy chi tiết sản phẩm theo ID
     * @param id Product ID
     * @return ProductDTO
     */
    ProductDTO getProductById(Long id);

    /**
     * Lấy chi tiết sản phẩm kèm variants và print areas
     * @param id Product ID
     * @return ProductDetailDTO
     */
    ProductDetailDTO getProductDetailById(Long id);

    /**
     * Tạo sản phẩm mới
     * @param request CreateProductRequest
     * @return ProductDTO
     */
    ProductDTO createProduct(CreateProductRequest request);

    /**
     * Cập nhật sản phẩm
     * @param id Product ID
     * @param request UpdateProductRequest
     * @return ProductDTO
     */
    ProductDTO updateProduct(Long id, UpdateProductRequest request);

    /**
     * Xóa sản phẩm (soft delete hoặc hard delete)
     * @param id Product ID
     */
    void deleteProduct(Long id);

    /**
     * Kích hoạt sản phẩm
     * @param id Product ID
     */
    void activateProduct(Long id);

    /**
     * Vô hiệu hóa sản phẩm
     * @param id Product ID
     */
    void deactivateProduct(Long id);

    // ========== Variant Management ==========

    /**
     * Lấy danh sách variants của một sản phẩm
     * @param productId Product ID
     * @return Danh sách ProductVariantDTO
     */
    List<ProductVariantDTO> getVariantsByProductId(Long productId);

    /**
     * Tạo variant mới cho sản phẩm
     * @param productId Product ID
     * @param request CreateProductVariantRequest
     * @return ProductVariantDTO
     */
    ProductVariantDTO createVariant(Long productId, CreateProductVariantRequest request);

    /**
     * Cập nhật variant
     * @param variantId Variant ID
     * @param request UpdateProductVariantRequest
     * @return ProductVariantDTO
     */
    ProductVariantDTO updateVariant(Long variantId, UpdateProductVariantRequest request);

    /**
     * Xóa variant
     * @param variantId Variant ID
     */
    void deleteVariant(Long variantId);

    // ========== PrintArea Management ==========

    /**
     * Lấy danh sách print areas của một sản phẩm
     * @param productId Product ID
     * @return Danh sách PrintAreaDTO
     */
    List<PrintAreaDTO> getPrintAreasByProductId(Long productId);

    /**
     * Tạo print area mới cho sản phẩm
     * @param productId Product ID
     * @param request CreatePrintAreaRequest
     * @return PrintAreaDTO
     */
    PrintAreaDTO createPrintArea(Long productId, CreatePrintAreaRequest request);

    /**
     * Cập nhật print area
     * @param printAreaId PrintArea ID
     * @param request UpdatePrintAreaRequest
     * @return PrintAreaDTO
     */
    PrintAreaDTO updatePrintArea(Long printAreaId, UpdatePrintAreaRequest request);

    /**
     * Xóa print area
     * @param printAreaId PrintArea ID
     */
    void deletePrintArea(Long printAreaId);
}
