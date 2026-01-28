# ✅ CHECKLIST: PRODUCT & VARIANTS SPRINT 1

## 📌 Phase 1: Entity & Database (Ưu tiên cao)

### Entity Relationships
- [ ] Thêm `@OneToMany` variants và printAreas vào `BaseProduct`
- [ ] Thêm `@ManyToOne` baseProduct vào `ProductVariant` với `@JoinColumn`
- [ ] Thêm `@ManyToOne` baseProduct vào `PrintArea` với `@JoinColumn`

### Repository Layer
- [ ] Tạo `ProductVariantRepository.java`
- [ ] Tạo `PrintAreaRepository.java`
- [ ] Thêm custom queries vào `BaseProductRepository`:
  - [ ] `findByIdAndActiveTrue(Long id)`
  - [ ] `existsByName(String name)`

### Seed Data
- [ ] Tạo file `src/main/resources/data.sql`
- [ ] Insert 5 sản phẩm mẫu (Áo thun, Cốc, Túi tote, Áo hoodie, Mũ lưỡi trai)
- [ ] Insert PrintArea cho mỗi sản phẩm (Front, Back nếu có)
- [ ] Insert Variants cho mỗi sản phẩm (màu sắc, size, SKU, stock)

---

## 📌 Phase 2: DTO & Mapper

### Request DTOs
- [ ] `CreateProductRequest.java` (với validation)
- [ ] `UpdateProductRequest.java` (với validation)
- [ ] `CreateProductVariantRequest.java`
- [ ] `UpdateProductVariantRequest.java`
- [ ] `CreatePrintAreaRequest.java`
- [ ] `UpdatePrintAreaRequest.java`

### Response DTOs
- [ ] `ProductDTO.java`
- [ ] `ProductVariantDTO.java`
- [ ] `PrintAreaDTO.java`
- [ ] `ProductDetailDTO.java` (chứa product + variants + printAreas)

### Mapper
- [ ] Tạo `ProductMapper.java` với MapStruct
- [ ] Implement tất cả mapping methods

---

## 📌 Phase 3: Service Layer

### Service Interface
- [ ] Tạo `ProductService.java`
- [ ] Định nghĩa tất cả methods:
  - [ ] CRUD Product (getAll, getById, getDetail, create, update, delete)
  - [ ] Activate/Deactivate
  - [ ] Variant management
  - [ ] PrintArea management

### Service Implementation
- [ ] Tạo `ProductServiceImpl.java` với `@RequiredArgsConstructor`
- [ ] Thêm `@Slf4j` cho logging
- [ ] Implement tất cả methods
- [ ] Thêm business validation (duplicate check, existence check)
- [ ] Throw `AppException` với `ErrorCode` phù hợp

---

## 📌 Phase 4: Controller Layer

### Product Endpoints
- [ ] `GET /api/v1/products` - List products (có filter active)
- [ ] `GET /api/v1/products/{id}` - Product detail với variants + printAreas
- [ ] `POST /api/v1/products` - Create product
- [ ] `PUT /api/v1/products/{id}` - Update product
- [ ] `DELETE /api/v1/products/{id}` - Delete product
- [ ] `PATCH /api/v1/products/{id}/activate` - Activate
- [ ] `PATCH /api/v1/products/{id}/deactivate` - Deactivate

### Variant Endpoints
- [ ] `GET /api/v1/products/{productId}/variants` - List variants
- [ ] `POST /api/v1/products/{productId}/variants` - Create variant
- [ ] `PUT /api/v1/products/variants/{variantId}` - Update variant
- [ ] `DELETE /api/v1/products/variants/{variantId}` - Delete variant

### PrintArea Endpoints
- [ ] `GET /api/v1/products/{productId}/print-areas` - List print areas
- [ ] `POST /api/v1/products/{productId}/print-areas` - Create print area
- [ ] `PUT /api/v1/products/print-areas/{printAreaId}` - Update print area
- [ ] `DELETE /api/v1/products/print-areas/{printAreaId}` - Delete print area

### Documentation
- [ ] Thêm Swagger annotations vào Controller
- [ ] Verify response format `ApiResponse<T>`

---

## 📌 Phase 5: Swagger & Testing

### Swagger Setup
- [ ] Thêm `springdoc-openapi` dependency vào `pom.xml`
- [ ] Cấu hình Swagger trong `application.yaml`
- [ ] Verify `/swagger-ui.html` hoạt động

### Testing
- [ ] Test tất cả GET endpoints trên Postman
- [ ] Test tất cả POST endpoints (create)
- [ ] Test tất cả PUT endpoints (update)
- [ ] Test tất cả DELETE endpoints
- [ ] Test error cases (not found, duplicate, validation errors)
- [ ] Verify seed data được load khi start app

### Code Review
- [ ] Kiểm tra không dùng `@Autowired` (dùng Constructor DI)
- [ ] Kiểm tra không dùng `sout` (dùng `log.xxx()`)
- [ ] Kiểm tra exception dùng `AppException` với `ErrorCode`
- [ ] Kiểm tra naming convention (Request/DTO)
- [ ] Kiểm tra REST endpoint naming (`/api/v1/products/`)

---

## 🚀 QUY TRÌNH GIT

- [ ] Tạo branch `feature/product` từ `dev`
- [ ] Commit sau mỗi phase hoàn thành
- [ ] Commit message rõ ràng
- [ ] Tạo Pull Request (Draft nếu chưa xong)
- [ ] PR title: "feat: Product & Variants CRUD API"

---

## 📝 NOTES

- **Ưu tiên:** Phase 1 → Phase 2 → Phase 3 → Phase 4 → Phase 5
- **Tham khảo:** `UserController`, `UserService`, `UserMapper` để hiểu pattern
- **Error Codes:** Đã có sẵn trong `ErrorCode.java` (3002, 3008, 3009, 4004, etc.)
- **Response:** Tất cả API trả về `ApiResponse<T>` format
