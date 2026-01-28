# PHÂN TÍCH SPRINT 1: PRODUCT & VARIANTS

## 📋 TỔNG QUAN YÊU CẦU

**Mục tiêu:** Xây dựng hệ thống quản lý Sản phẩm và Biến thể sản phẩm với đầy đủ CRUD API và Seed Data.

**Người phụ trách:** Dev 4 (Long)

**Thời gian:** Tuần 1-2 của Sprint 1

---

## 🔍 PHÂN TÍCH CẤU TRÚC HIỆN TẠI

### ✅ ĐÃ CÓ SẴN

#### 1. **Entity Classes** (Đã có nhưng chưa hoàn chỉnh)
- ✅ `BaseProduct.java` - Entity sản phẩm gốc
- ✅ `ProductVariant.java` - Entity biến thể sản phẩm  
- ✅ `PrintArea.java` - Entity cấu hình vùng in

#### 2. **Repository**
- ✅ `BaseProductRepository.java` - Repository cơ bản với method `findByActiveTrue()`

#### 3. **Controller**
- ⚠️ `ProductController.java` - **RỖNG**, cần implement

#### 4. **Infrastructure**
- ✅ `BaseEntity.java` - Base class với auditing (createdDate, modifiedDate, createdBy, modifiedBy)
- ✅ `BaseEntityCreatedOnly.java` - Base class chỉ có created fields
- ✅ `ErrorCode.java` - Đã có đầy đủ error codes cho Product (3002, 3008, 3009, 4004, 4040, 4041, 4042)
- ✅ `ApiResponse.java` - Response wrapper chuẩn
- ✅ `GlobalExceptionHandler.java` - Exception handler

#### 5. **Pattern đã có**
- ✅ Service pattern với `@RequiredArgsConstructor` (Constructor DI)
- ✅ Mapper pattern với MapStruct
- ✅ DTO pattern (Request/Response)

---

## ❌ THIẾU - CẦN LÀM

### 1. **ENTITY RELATIONSHIPS** (Quan hệ giữa các entity)

#### 🔴 Vấn đề hiện tại:
- `BaseProduct` có `@OneToMany` nhưng **KHÔNG THẤY** trong code đã đọc
- `ProductVariant` và `PrintArea` **THIẾU** `@ManyToOne` relationship với `BaseProduct`
- Thiếu `@JoinColumn` để map foreign key

#### ✅ Cần sửa:

**BaseProduct.java:**
```java
@OneToMany(mappedBy = "baseProduct", cascade = CascadeType.ALL, orphanRemoval = true)
private List<ProductVariant> variants;

@OneToMany(mappedBy = "baseProduct", cascade = CascadeType.ALL, orphanRemoval = true)
private List<PrintArea> printAreas;
```

**ProductVariant.java:**
```java
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "base_product_id", nullable = false)
private BaseProduct baseProduct;
```

**PrintArea.java:**
```java
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "base_product_id", nullable = false)
private BaseProduct baseProduct;
```

---

### 2. **REPOSITORY LAYER**

#### ❌ Thiếu:
- `ProductVariantRepository.java`
- `PrintAreaRepository.java`
- Custom query methods trong `BaseProductRepository`:
  - `findByIdAndActiveTrue(Long id)`
  - `existsByName(String name)` (để check duplicate)

#### ✅ Cần tạo:

**ProductVariantRepository.java:**
```java
@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Long> {
    List<ProductVariant> findByBaseProductId(Long baseProductId);
    List<ProductVariant> findByBaseProductIdAndActiveTrue(Long baseProductId);
    Optional<ProductVariant> findBySku(String sku);
    boolean existsBySku(String sku);
}
```

**PrintAreaRepository.java:**
```java
@Repository
public interface PrintAreaRepository extends JpaRepository<PrintArea, Long> {
    List<PrintArea> findByBaseProductId(Long baseProductId);
}
```

---

### 3. **DTO LAYER**

#### ❌ Thiếu hoàn toàn:
- `ProductRequest.java` (Create/Update)
- `ProductDTO.java` (Response)
- `ProductVariantRequest.java`
- `ProductVariantDTO.java`
- `PrintAreaRequest.java`
- `PrintAreaDTO.java`
- `ProductDetailDTO.java` (DTO cho API detail với variants và printAreas)

#### ✅ Cần tạo trong `model/dto/request/`:
- `CreateProductRequest.java`
- `UpdateProductRequest.java`
- `CreateProductVariantRequest.java`
- `UpdateProductVariantRequest.java`
- `CreatePrintAreaRequest.java`
- `UpdatePrintAreaRequest.java`

#### ✅ Cần tạo trong `model/dto/response/`:
- `ProductDTO.java`
- `ProductVariantDTO.java`
- `PrintAreaDTO.java`
- `ProductDetailDTO.java` (chứa product + variants + printAreas)

---

### 4. **MAPPER LAYER**

#### ❌ Thiếu:
- `ProductMapper.java` - Map giữa Entity ↔ DTO

#### ✅ Cần tạo:
```java
@Mapper(componentModel = "spring")
public interface ProductMapper {
    // BaseProduct mappings
    ProductDTO toDTO(BaseProduct product);
    BaseProduct toEntity(CreateProductRequest request);
    void updateEntity(UpdateProductRequest request, @MappingTarget BaseProduct product);
    List<ProductDTO> toDTOList(List<BaseProduct> products);
    
    // ProductVariant mappings
    ProductVariantDTO toVariantDTO(ProductVariant variant);
    ProductVariant toVariantEntity(CreateProductVariantRequest request);
    List<ProductVariantDTO> toVariantDTOList(List<ProductVariant> variants);
    
    // PrintArea mappings
    PrintAreaDTO toPrintAreaDTO(PrintArea printArea);
    PrintArea toPrintAreaEntity(CreatePrintAreaRequest request);
    List<PrintAreaDTO> toPrintAreaDTOList(List<PrintArea> printAreas);
    
    // Detail mapping
    ProductDetailDTO toDetailDTO(BaseProduct product);
}
```

---

### 5. **SERVICE LAYER**

#### ❌ Thiếu hoàn toàn:
- `ProductService.java` (Interface)
- `ProductServiceImpl.java` (Implementation)

#### ✅ Cần tạo với các methods:

**ProductService.java:**
```java
public interface ProductService {
    // CRUD Product
    List<ProductDTO> getAllProducts(Boolean activeOnly);
    ProductDTO getProductById(Long id);
    ProductDetailDTO getProductDetailById(Long id); // Với variants và printAreas
    ProductDTO createProduct(CreateProductRequest request);
    ProductDTO updateProduct(Long id, UpdateProductRequest request);
    void deleteProduct(Long id);
    void activateProduct(Long id);
    void deactivateProduct(Long id);
    
    // Variant Management
    List<ProductVariantDTO> getVariantsByProductId(Long productId);
    ProductVariantDTO createVariant(Long productId, CreateProductVariantRequest request);
    ProductVariantDTO updateVariant(Long variantId, UpdateProductVariantRequest request);
    void deleteVariant(Long variantId);
    
    // PrintArea Management
    List<PrintAreaDTO> getPrintAreasByProductId(Long productId);
    PrintAreaDTO createPrintArea(Long productId, CreatePrintAreaRequest request);
    PrintAreaDTO updatePrintArea(Long printAreaId, UpdatePrintAreaRequest request);
    void deletePrintArea(Long printAreaId);
}
```

**ProductServiceImpl.java:**
- Sử dụng Constructor DI (`@RequiredArgsConstructor`)
- Sử dụng `@Slf4j` cho logging (KHÔNG dùng `sout`)
- Validate business logic:
  - Check duplicate name/SKU
  - Check product exists trước khi tạo variant/printArea
  - Check active status
- Throw `AppException` với `ErrorCode` phù hợp

---

### 6. **CONTROLLER LAYER**

#### ⚠️ `ProductController.java` hiện tại RỖNG

#### ✅ Cần implement:

**API Endpoints theo REST convention:**

```
GET    /api/v1/products              - Lấy danh sách sản phẩm (có filter active)
GET    /api/v1/products/{id}         - Lấy chi tiết sản phẩm (với variants và printAreas)
POST   /api/v1/products              - Tạo sản phẩm mới (Admin only - TODO: thêm security sau)
PUT    /api/v1/products/{id}         - Cập nhật sản phẩm (Admin only)
DELETE /api/v1/products/{id}         - Xóa sản phẩm (Admin only)
PATCH  /api/v1/products/{id}/activate   - Kích hoạt sản phẩm
PATCH  /api/v1/products/{id}/deactivate - Vô hiệu hóa sản phẩm

GET    /api/v1/products/{productId}/variants     - Lấy danh sách variants của sản phẩm
POST   /api/v1/products/{productId}/variants     - Tạo variant mới
PUT    /api/v1/products/variants/{variantId}     - Cập nhật variant
DELETE /api/v1/products/variants/{variantId}    - Xóa variant

GET    /api/v1/products/{productId}/print-areas  - Lấy danh sách print areas
POST   /api/v1/products/{productId}/print-areas  - Tạo print area mới
PUT    /api/v1/products/print-areas/{printAreaId} - Cập nhật print area
DELETE /api/v1/products/print-areas/{printAreaId} - Xóa print area
```

**Response format:**
- Tất cả API trả về `ApiResponse<T>`
- Success: `code = 200/201`, `message` rõ ràng
- Error: Được handle bởi `GlobalExceptionHandler`

---

### 7. **VALIDATION**

#### ❌ Thiếu:
- Validation annotations trong Request DTOs
- Custom validators nếu cần

#### ✅ Cần thêm vào Request DTOs:
- `@NotNull`, `@NotBlank` cho required fields
- `@Min`, `@Max` cho số lượng
- `@DecimalMin` cho giá tiền
- `@Pattern` cho SKU format (nếu có rule)
- `@Email` cho email (nếu có)
- `@Size` cho string length

**Ví dụ:**
```java
@NotBlank(message = "Product name is required")
@Size(max = 255, message = "Product name must not exceed 255 characters")
private String name;

@NotNull(message = "Base price is required")
@DecimalMin(value = "0.0", inclusive = false, message = "Base price must be greater than 0")
private BigDecimal basePrice;
```

---

### 8. **SEED DATA (data.sql)**

#### ❌ Thiếu hoàn toàn:
- File `data.sql` trong `src/main/resources/`

#### ✅ Cần tạo với:
- **5 sản phẩm mẫu:**
  1. Áo thun (T-Shirt)
  2. Cốc (Mug)
  3. Túi tote (Tote Bag)
  4. Áo hoodie (Hoodie)
  5. Mũ lưỡi trai (Cap)

- **PrintArea cho mỗi sản phẩm:**
  - Front (mặt trước)
  - Back (mặt sau) - nếu có
  - Cấu hình width_mm, height_mm, offsets

- **Variants cho mỗi sản phẩm:**
  - Màu sắc: Đen, Trắng, Xanh, Đỏ...
  - Size: S, M, L, XL (hoặc One Size cho cốc)
  - SKU unique
  - Stock quantity
  - Image URLs (có thể để local hoặc S3 URLs mẫu)

**Lưu ý:**
- Sử dụng `INSERT` statements
- Đảm bảo foreign key relationships đúng
- Set `active = true` mặc định
- Set `created_date` với `CURRENT_TIMESTAMP`

---

### 9. **SWAGGER/OPENAPI DOCUMENTATION**

#### ❌ Thiếu:
- Swagger dependency trong `pom.xml`
- Swagger configuration
- API annotations (`@Operation`, `@ApiResponse`, etc.)

#### ✅ Cần thêm:

**pom.xml:**
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.3.0</version>
</dependency>
```

**application.yaml:**
```yaml
springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /swagger-ui.html
```

**Controller annotations:**
```java
@Operation(summary = "Get all products", description = "Retrieve list of products")
@ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Success"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
})
```

---

## 📝 CHECKLIST CÔNG VIỆC

### Phase 1: Entity & Database Setup
- [ ] Sửa relationships trong `BaseProduct`, `ProductVariant`, `PrintArea`
- [ ] Tạo `ProductVariantRepository`
- [ ] Tạo `PrintAreaRepository`
- [ ] Cải thiện `BaseProductRepository` với custom queries
- [ ] Tạo file `data.sql` với seed data (5 products + variants + printAreas)
- [ ] Test database schema và relationships

### Phase 2: DTO & Mapper
- [ ] Tạo `CreateProductRequest.java`
- [ ] Tạo `UpdateProductRequest.java`
- [ ] Tạo `ProductDTO.java`
- [ ] Tạo `ProductDetailDTO.java`
- [ ] Tạo `CreateProductVariantRequest.java`
- [ ] Tạo `ProductVariantDTO.java`
- [ ] Tạo `CreatePrintAreaRequest.java`
- [ ] Tạo `PrintAreaDTO.java`
- [ ] Tạo `ProductMapper.java` với MapStruct
- [ ] Thêm validation annotations vào Request DTOs

### Phase 3: Service Layer
- [ ] Tạo `ProductService.java` interface
- [ ] Tạo `ProductServiceImpl.java` implementation
- [ ] Implement CRUD methods cho Product
- [ ] Implement methods cho Variant management
- [ ] Implement methods cho PrintArea management
- [ ] Thêm business logic validation
- [ ] Thêm logging với `@Slf4j`
- [ ] Test service layer với unit tests (optional)

### Phase 4: Controller Layer
- [ ] Implement `ProductController.java`
- [ ] Implement GET `/api/v1/products` (list)
- [ ] Implement GET `/api/v1/products/{id}` (detail với variants + printAreas)
- [ ] Implement POST `/api/v1/products` (create)
- [ ] Implement PUT `/api/v1/products/{id}` (update)
- [ ] Implement DELETE `/api/v1/products/{id}` (delete)
- [ ] Implement PATCH activate/deactivate
- [ ] Implement Variant endpoints
- [ ] Implement PrintArea endpoints
- [ ] Thêm Swagger annotations
- [ ] Test tất cả endpoints với Postman

### Phase 5: Documentation & Testing
- [ ] Thêm Swagger dependency và config
- [ ] Verify Swagger UI hoạt động (`/swagger-ui.html`)
- [ ] Test tất cả API trên Postman
- [ ] Verify seed data được load đúng khi start app
- [ ] Kiểm tra error handling với các edge cases
- [ ] Review code theo coding conventions

---

## 🎯 QUY TẮC CẦN TUÂN THỦ

### 1. **REST API Naming Convention**
- ✅ Endpoint: `/api/v1/products/` (lowercase, plural, kebab-case cho multi-word)
- ✅ Method: GET (read), POST (create), PUT (update), DELETE (delete), PATCH (partial update)

### 2. **DTO Naming**
- ✅ Request: `ProductRequest`, `CreateProductRequest`, `UpdateProductRequest`
- ✅ Response: `ProductDTO`, `ProductDetailDTO`

### 3. **Dependency Injection**
- ✅ **KHÔNG dùng** `@Autowired`
- ✅ **Dùng** Constructor DI với `@RequiredArgsConstructor`

### 4. **Logging**
- ✅ **KHÔNG dùng** `System.out.println()`
- ✅ **Dùng** `log.info()`, `log.error()`, `log.debug()` từ `@Slf4j`

### 5. **Exception Handling**
- ✅ **KHÔNG throw** `RuntimeException` trực tiếp
- ✅ **Dùng** `AppException` với `ErrorCode` cụ thể
- ✅ Error codes đã có sẵn: `PRODUCT_NOT_FOUND(3002)`, `VARIANT_NOT_FOUND(3008)`, `SKU_ALREADY_EXISTS(4004)`, etc.

### 6. **Git Workflow**
- ✅ Branch: `feature/product` (tạo từ `dev`)
- ✅ Commit message: Rõ ràng, mô tả feature đã hoàn thành
- ✅ Pull Request: Tên theo feature, có thể là Draft nếu chưa xong

---

## 🔗 THAM KHẢO

### File mẫu để tham khảo pattern:
- `UserController.java` - Controller pattern
- `UserService.java` & `UserServiceImpl.java` - Service pattern
- `UserMapper.java` - Mapper pattern
- `CreateUserRequest.java` - Request DTO pattern
- `UserDTO.java` - Response DTO pattern

### Database Schema Reference:
```sql
-- BaseProduct fields: id, name, description, base_price, material, active, created_date, modified_date, created_by, modified_by
-- ProductVariant fields: id, base_product_id (FK), color_name, color_hex, size, sku, stock_quantity, image_url, price_adjustment, active, created_date, modified_date, created_by, modified_by
-- PrintArea fields: id, base_product_id (FK), name, width_mm, height_mm, top_offset_mm, left_offset_mm, mask_image_url, created_date, created_by
```

---

## 📊 ESTIMATE

- **Entity & Repository:** 2-3 giờ
- **DTO & Mapper:** 2-3 giờ
- **Service Layer:** 4-5 giờ
- **Controller Layer:** 3-4 giờ
- **Seed Data:** 1-2 giờ
- **Swagger & Testing:** 2-3 giờ
- **Review & Fix:** 2-3 giờ

**Tổng:** ~16-23 giờ (2-3 ngày làm việc)

---

## ✅ KẾT QUẢ MONG ĐỢI

Sau khi hoàn thành:
1. ✅ Database có đầy đủ 3 bảng với relationships đúng
2. ✅ Seed data có 5 sản phẩm mẫu với variants và printAreas
3. ✅ API CRUD đầy đủ cho Product, Variant, PrintArea
4. ✅ Swagger UI hiển thị đầy đủ API documentation
5. ✅ Test được trên Postman với tất cả endpoints
6. ✅ Code tuân thủ coding conventions
7. ✅ Error handling đầy đủ với ErrorCode
