# 🧪 API TESTING GUIDE - PRODUCT & VARIANTS

## 📋 TỔNG QUAN

Hướng dẫn test tất cả API endpoints của Product & Variants module trên Postman.

**Base URL:** `http://localhost:8080`  
**Swagger UI:** `http://localhost:8080/swagger-ui.html`  
**API Docs:** `http://localhost:8080/api-docs`

---

## 🔧 SETUP

### 1. Khởi động ứng dụng
```bash
# Sử dụng Maven wrapper
.\mvnw.cmd spring-boot:run

# Hoặc từ IDE
# Run PodBeApplication.java
```

### 2. Kiểm tra ứng dụng đã chạy
- Mở browser: `http://localhost:8080/swagger-ui.html`
- Xem API documentation

### 3. Import Postman Collection
- Tạo collection mới trong Postman
- Import các requests từ guide này

---

## 📝 PRODUCT ENDPOINTS

### 1. GET All Products

**Request:**
```
GET http://localhost:8080/api/v1/products
```

**Query Parameters (Optional):**
- `activeOnly=true` - Chỉ lấy sản phẩm active
- `activeOnly=false` - Lấy tất cả sản phẩm

**Expected Response (200 OK):**
```json
{
  "code": 200,
  "message": "Get all products successfully",
  "data": [
    {
      "id": 1,
      "name": "Áo Thun Cổ Tròn",
      "description": "Áo thun cotton 100%, thoáng mát, dễ giặt",
      "basePrice": 250000.00,
      "material": "Cotton 100%",
      "active": true,
      "createdAt": "2026-01-28T10:00:00Z",
      "updatedAt": "2026-01-28T10:00:00Z"
    }
  ],
  "timestamp": 1706436000000
}
```

**Test Cases:**
- ✅ GET without query param → Should return all products
- ✅ GET with `activeOnly=true` → Should return only active products
- ✅ GET with `activeOnly=false` → Should return all products

---

### 2. GET Product By ID

**Request:**
```
GET http://localhost:8080/api/v1/products/1
```

**Expected Response (200 OK):**
```json
{
  "code": 200,
  "message": "Get product successfully",
  "data": {
    "id": 1,
    "name": "Áo Thun Cổ Tròn",
    "description": "Áo thun cotton 100%, thoáng mát, dễ giặt",
    "basePrice": 250000.00,
    "material": "Cotton 100%",
    "active": true,
    "createdAt": "2026-01-28T10:00:00Z",
    "updatedAt": "2026-01-28T10:00:00Z"
  },
  "timestamp": 1706436000000
}
```

**Error Response (404 Not Found):**
```json
{
  "code": 3002,
  "message": "Product not found with id: 999",
  "timestamp": 1706436000000
}
```

**Test Cases:**
- ✅ GET with valid ID → Should return product
- ✅ GET with invalid ID → Should return 404

---

### 3. GET Product Detail (with Variants & Print Areas)

**Request:**
```
GET http://localhost:8080/api/v1/products/1/detail
```

**Expected Response (200 OK):**
```json
{
  "code": 200,
  "message": "Get product detail successfully",
  "data": {
    "id": 1,
    "name": "Áo Thun Cổ Tròn",
    "description": "Áo thun cotton 100%, thoáng mát, dễ giặt",
    "basePrice": 250000.00,
    "material": "Cotton 100%",
    "active": true,
    "createdAt": "2026-01-28T10:00:00Z",
    "updatedAt": "2026-01-28T10:00:00Z",
    "variants": [
      {
        "id": 1,
        "baseProductId": 1,
        "colorName": "Đen",
        "colorHex": "#000000",
        "size": "S",
        "sku": "TSHIRT-BLACK-S",
        "stockQuantity": 100,
        "imageUrl": "https://example.com/images/tshirt-black-s.jpg",
        "priceAdjustment": 0.00,
        "active": true
      }
    ],
    "printAreas": [
      {
        "id": 1,
        "baseProductId": 1,
        "name": "Front",
        "widthMm": 300.00,
        "heightMm": 350.00,
        "topOffsetMm": 50.00,
        "leftOffsetMm": 0.00,
        "maskImageUrl": null
      }
    ]
  },
  "timestamp": 1706436000000
}
```

**Test Cases:**
- ✅ GET with valid ID → Should return product with variants and printAreas
- ✅ GET with invalid ID → Should return 404

---

### 4. POST Create Product

**Request:**
```
POST http://localhost:8080/api/v1/products
Content-Type: application/json
```

**Request Body:**
```json
{
  "name": "Áo Polo",
  "description": "Áo polo cao cấp",
  "basePrice": 300000.00,
  "material": "Cotton 65% + Polyester 35%",
  "active": true
}
```

**Expected Response (201 Created):**
```json
{
  "code": 201,
  "message": "Product created successfully",
  "data": {
    "id": 6,
    "name": "Áo Polo",
    "description": "Áo polo cao cấp",
    "basePrice": 300000.00,
    "material": "Cotton 65% + Polyester 35%",
    "active": true,
    "createdAt": "2026-01-28T10:00:00Z",
    "updatedAt": "2026-01-28T10:00:00Z"
  },
  "timestamp": 1706436000000
}
```

**Error Response (400 Bad Request - Duplicate Name):**
```json
{
  "code": 4050,
  "message": "Name Áo Thun Cổ Tròn already exists",
  "timestamp": 1706436000000
}
```

**Error Response (400 Bad Request - Validation Error):**
```json
{
  "code": 2003,
  "message": "Required field name is missing",
  "timestamp": 1706436000000
}
```

**Test Cases:**
- ✅ POST with valid data → Should create product
- ✅ POST with duplicate name → Should return 400
- ✅ POST with missing required fields → Should return 400
- ✅ POST with invalid price (negative) → Should return 400
- ✅ POST with name too long (>255 chars) → Should return 400

---

### 5. PUT Update Product

**Request:**
```
PUT http://localhost:8080/api/v1/products/1
Content-Type: application/json
```

**Request Body (Partial Update):**
```json
{
  "name": "Áo Thun Cổ Tròn Updated",
  "basePrice": 280000.00
}
```

**Expected Response (200 OK):**
```json
{
  "code": 200,
  "message": "Product updated successfully",
  "data": {
    "id": 1,
    "name": "Áo Thun Cổ Tròn Updated",
    "description": "Áo thun cotton 100%, thoáng mát, dễ giặt",
    "basePrice": 280000.00,
    "material": "Cotton 100%",
    "active": true,
    "createdAt": "2026-01-28T10:00:00Z",
    "updatedAt": "2026-01-28T10:05:00Z"
  },
  "timestamp": 1706436000000
}
```

**Test Cases:**
- ✅ PUT with valid data → Should update product
- ✅ PUT with duplicate name → Should return 400
- ✅ PUT with invalid ID → Should return 404
- ✅ PUT with partial update → Should update only provided fields

---

### 6. DELETE Product

**Request:**
```
DELETE http://localhost:8080/api/v1/products/1
```

**Expected Response (200 OK):**
```json
{
  "code": 200,
  "message": "Product deleted successfully",
  "timestamp": 1706436000000
}
```

**Test Cases:**
- ✅ DELETE with valid ID → Should delete product (and cascade variants/printAreas)
- ✅ DELETE with invalid ID → Should return 404

---

### 7. PATCH Activate Product

**Request:**
```
PATCH http://localhost:8080/api/v1/products/1/activate
```

**Expected Response (200 OK):**
```json
{
  "code": 200,
  "message": "Product activated successfully",
  "timestamp": 1706436000000
}
```

**Test Cases:**
- ✅ PATCH activate active product → Should return 200 (no error)
- ✅ PATCH activate inactive product → Should activate it
- ✅ PATCH activate invalid ID → Should return 404

---

### 8. PATCH Deactivate Product

**Request:**
```
PATCH http://localhost:8080/api/v1/products/1/deactivate
```

**Expected Response (200 OK):**
```json
{
  "code": 200,
  "message": "Product deactivated successfully",
  "timestamp": 1706436000000
}
```

**Test Cases:**
- ✅ PATCH deactivate inactive product → Should return 200 (no error)
- ✅ PATCH deactivate active product → Should deactivate it
- ✅ PATCH deactivate invalid ID → Should return 404

---

## 📝 VARIANT ENDPOINTS

### 9. GET Variants By Product ID

**Request:**
```
GET http://localhost:8080/api/v1/products/1/variants
```

**Expected Response (200 OK):**
```json
{
  "code": 200,
  "message": "Get product variants successfully",
  "data": [
    {
      "id": 1,
      "baseProductId": 1,
      "colorName": "Đen",
      "colorHex": "#000000",
      "size": "S",
      "sku": "TSHIRT-BLACK-S",
      "stockQuantity": 100,
      "imageUrl": "https://example.com/images/tshirt-black-s.jpg",
      "priceAdjustment": 0.00,
      "active": true
    }
  ],
  "timestamp": 1706436000000
}
```

**Test Cases:**
- ✅ GET with valid productId → Should return variants
- ✅ GET with invalid productId → Should return 404

---

### 10. POST Create Variant

**Request:**
```
POST http://localhost:8080/api/v1/products/1/variants
Content-Type: application/json
```

**Request Body:**
```json
{
  "colorName": "Xanh Dương",
  "colorHex": "#0066CC",
  "size": "XL",
  "sku": "TSHIRT-BLUE-XL",
  "stockQuantity": 50,
  "imageUrl": "https://example.com/images/tshirt-blue-xl.jpg",
  "priceAdjustment": 30000.00,
  "active": true
}
```

**Expected Response (201 Created):**
```json
{
  "code": 201,
  "message": "Product variant created successfully",
  "data": {
    "id": 9,
    "baseProductId": 1,
    "colorName": "Xanh Dương",
    "colorHex": "#0066CC",
    "size": "XL",
    "sku": "TSHIRT-BLUE-XL",
    "stockQuantity": 50,
    "imageUrl": "https://example.com/images/tshirt-blue-xl.jpg",
    "priceAdjustment": 30000.00,
    "active": true
  },
  "timestamp": 1706436000000
}
```

**Error Response (400 Bad Request - Duplicate SKU):**
```json
{
  "code": 4004,
  "message": "SKU already exists: TSHIRT-BLACK-S",
  "timestamp": 1706436000000
}
```

**Test Cases:**
- ✅ POST with valid data → Should create variant
- ✅ POST with duplicate SKU → Should return 400
- ✅ POST with invalid productId → Should return 404
- ✅ POST with invalid colorHex format → Should return 400
- ✅ POST with negative stockQuantity → Should return 400

---

### 11. PUT Update Variant

**Request:**
```
PUT http://localhost:8080/api/v1/products/variants/1
Content-Type: application/json
```

**Request Body:**
```json
{
  "stockQuantity": 150,
  "priceAdjustment": 5000.00
}
```

**Expected Response (200 OK):**
```json
{
  "code": 200,
  "message": "Product variant updated successfully",
  "data": {
    "id": 1,
    "baseProductId": 1,
    "colorName": "Đen",
    "colorHex": "#000000",
    "size": "S",
    "sku": "TSHIRT-BLACK-S",
    "stockQuantity": 150,
    "imageUrl": "https://example.com/images/tshirt-black-s.jpg",
    "priceAdjustment": 5000.00,
    "active": true
  },
  "timestamp": 1706436000000
}
```

**Test Cases:**
- ✅ PUT with valid data → Should update variant
- ✅ PUT with duplicate SKU → Should return 400
- ✅ PUT with invalid variantId → Should return 404

---

### 12. DELETE Variant

**Request:**
```
DELETE http://localhost:8080/api/v1/products/variants/1
```

**Expected Response (200 OK):**
```json
{
  "code": 200,
  "message": "Product variant deleted successfully",
  "timestamp": 1706436000000
}
```

**Test Cases:**
- ✅ DELETE with valid variantId → Should delete variant
- ✅ DELETE with invalid variantId → Should return 404

---

## 📝 PRINT AREA ENDPOINTS

### 13. GET Print Areas By Product ID

**Request:**
```
GET http://localhost:8080/api/v1/products/1/print-areas
```

**Expected Response (200 OK):**
```json
{
  "code": 200,
  "message": "Get product print areas successfully",
  "data": [
    {
      "id": 1,
      "baseProductId": 1,
      "name": "Front",
      "widthMm": 300.00,
      "heightMm": 350.00,
      "topOffsetMm": 50.00,
      "leftOffsetMm": 0.00,
      "maskImageUrl": null
    }
  ],
  "timestamp": 1706436000000
}
```

**Test Cases:**
- ✅ GET with valid productId → Should return print areas
- ✅ GET with invalid productId → Should return 404

---

### 14. POST Create Print Area

**Request:**
```
POST http://localhost:8080/api/v1/products/1/print-areas
Content-Type: application/json
```

**Request Body:**
```json
{
  "name": "Sleeve",
  "widthMm": 100.00,
  "heightMm": 500.00,
  "topOffsetMm": 0.00,
  "leftOffsetMm": 0.00,
  "maskImageUrl": null
}
```

**Expected Response (201 Created):**
```json
{
  "code": 201,
  "message": "Print area created successfully",
  "data": {
    "id": 16,
    "baseProductId": 1,
    "name": "Sleeve",
    "widthMm": 100.00,
    "heightMm": 500.00,
    "topOffsetMm": 0.00,
    "leftOffsetMm": 0.00,
    "maskImageUrl": null
  },
  "timestamp": 1706436000000
}
```

**Test Cases:**
- ✅ POST with valid data → Should create print area
- ✅ POST with invalid productId → Should return 404
- ✅ POST with invalid dimensions (negative) → Should return 400
- ✅ POST with missing required fields → Should return 400

---

### 15. PUT Update Print Area

**Request:**
```
PUT http://localhost:8080/api/v1/products/print-areas/1
Content-Type: application/json
```

**Request Body:**
```json
{
  "widthMm": 350.00,
  "heightMm": 400.00
}
```

**Expected Response (200 OK):**
```json
{
  "code": 200,
  "message": "Print area updated successfully",
  "data": {
    "id": 1,
    "baseProductId": 1,
    "name": "Front",
    "widthMm": 350.00,
    "heightMm": 400.00,
    "topOffsetMm": 50.00,
    "leftOffsetMm": 0.00,
    "maskImageUrl": null
  },
  "timestamp": 1706436000000
}
```

**Test Cases:**
- ✅ PUT with valid data → Should update print area
- ✅ PUT with invalid printAreaId → Should return 404

---

### 16. DELETE Print Area

**Request:**
```
DELETE http://localhost:8080/api/v1/products/print-areas/1
```

**Expected Response (200 OK):**
```json
{
  "code": 200,
  "message": "Print area deleted successfully",
  "timestamp": 1706436000000
}
```

**Test Cases:**
- ✅ DELETE with valid printAreaId → Should delete print area
- ✅ DELETE with invalid printAreaId → Should return 404

---

## ✅ TESTING CHECKLIST

### Product APIs
- [ ] GET all products (with/without filter)
- [ ] GET product by ID (valid/invalid)
- [ ] GET product detail (with variants & printAreas)
- [ ] POST create product (valid/duplicate name/validation errors)
- [ ] PUT update product (valid/duplicate name/invalid ID)
- [ ] DELETE product (valid/invalid ID)
- [ ] PATCH activate product
- [ ] PATCH deactivate product

### Variant APIs
- [ ] GET variants by product ID (valid/invalid productId)
- [ ] POST create variant (valid/duplicate SKU/validation errors)
- [ ] PUT update variant (valid/duplicate SKU/invalid ID)
- [ ] DELETE variant (valid/invalid ID)

### PrintArea APIs
- [ ] GET print areas by product ID (valid/invalid productId)
- [ ] POST create print area (valid/validation errors)
- [ ] PUT update print area (valid/invalid ID)
- [ ] DELETE print area (valid/invalid ID)

---

## 🔍 VERIFY SEED DATA

Sau khi start ứng dụng, verify seed data:

```sql
-- Kết nối PostgreSQL
psql -U postgres -d pod_db

-- Kiểm tra số lượng
SELECT COUNT(*) FROM base_products;        -- Phải = 5
SELECT COUNT(*) FROM product_variants;      -- Phải = 30
SELECT COUNT(*) FROM print_areas;           -- Phải = 15

-- Kiểm tra chi tiết
SELECT * FROM base_products;
SELECT * FROM product_variants LIMIT 5;
SELECT * FROM print_areas LIMIT 5;
```

---

## 📝 NOTES

- Tất cả timestamps trong response là UTC format
- Error codes theo convention: 30xx (Not Found), 40xx (Business Logic), 20xx (Validation)
- Swagger UI có thể test trực tiếp tại `/swagger-ui.html`
- Postman có thể import OpenAPI spec từ `/api-docs`
