# 🧪 HƯỚNG DẪN TEST LUỒNG NGHIỆP VỤ - PRODUCT & VARIANTS

## 📋 TỔNG QUAN

Hướng dẫn test theo luồng nghiệp vụ thực tế từ cơ bản đến nâng cao.

**Base URL:** `http://localhost:8080`  
**Swagger UI:** `http://localhost:8080/swagger-ui.html`

---

## 🎯 LUỒNG TEST CƠ BẢN

### Scenario 1: Khách hàng xem danh sách sản phẩm

#### Bước 1: Xem tất cả sản phẩm
**Request:**
```
GET http://localhost:8080/api/v1/products
```

**Expected:**
- Status: 200 OK
- Response có 5 sản phẩm từ seed data
- Mỗi sản phẩm có: id, name, description, basePrice, material, active

**Kiểm tra:**
- ✅ Có đủ 5 sản phẩm
- ✅ Tất cả đều `active: true`
- ✅ Thông tin đầy đủ

---

#### Bước 2: Xem chỉ sản phẩm đang active
**Request:**
```
GET http://localhost:8080/api/v1/products?activeOnly=true
```

**Expected:**
- Status: 200 OK
- Chỉ trả về sản phẩm có `active: true`

**Kiểm tra:**
- ✅ Không có sản phẩm inactive trong response

---

#### Bước 3: Xem chi tiết một sản phẩm
**Request:**
```
GET http://localhost:8080/api/v1/products/1
```

**Expected:**
- Status: 200 OK
- Thông tin chi tiết sản phẩm

**Kiểm tra:**
- ✅ Có đầy đủ thông tin: name, description, basePrice, material, active, createdAt, updatedAt

---

#### Bước 4: Xem chi tiết sản phẩm kèm variants và print areas
**Request:**
```
GET http://localhost:8080/api/v1/products/1/detail
```

**Expected:**
- Status: 200 OK
- Product info + variants list + printAreas list

**Kiểm tra:**
- ✅ Có variants (ví dụ: Áo Thun có 8 variants)
- ✅ Có printAreas (ví dụ: Áo Thun có Front và Back)
- ✅ Mỗi variant có: colorName, size, sku, stockQuantity, priceAdjustment
- ✅ Mỗi printArea có: name, widthMm, heightMm, offsets

---

### Scenario 2: Admin quản lý sản phẩm

#### Bước 1: Tạo sản phẩm mới
**Request:**
```
POST http://localhost:8080/api/v1/products
Content-Type: application/json

{
  "name": "Áo Polo Premium",
  "description": "Áo polo cao cấp, chất liệu tốt",
  "basePrice": 350000.00,
  "material": "Cotton 65% + Polyester 35%",
  "active": true
}
```

**Expected:**
- Status: 201 Created
- Response có product mới với id được generate

**Kiểm tra:**
- ✅ Product được tạo thành công
- ✅ ID được tự động generate
- ✅ createdAt và updatedAt được set

**Lưu ID product mới:** `productId = 6` (hoặc id mới được tạo)

---

#### Bước 2: Tạo variants cho sản phẩm mới
**Request:**
```
POST http://localhost:8080/api/v1/products/6/variants
Content-Type: application/json

{
  "colorName": "Trắng",
  "colorHex": "#FFFFFF",
  "size": "M",
  "sku": "POLO-WHITE-M-001",
  "stockQuantity": 50,
  "imageUrl": "https://example.com/images/polo-white-m.jpg",
  "priceAdjustment": 0.00,
  "active": true
}
```

**Expected:**
- Status: 201 Created
- Variant được tạo với baseProductId = 6

**Kiểm tra:**
- ✅ Variant được tạo thành công
- ✅ baseProductId đúng với product vừa tạo

**Tạo thêm variants:**
- Màu Đen size L
- Màu Xanh size M

---

#### Bước 3: Tạo print areas cho sản phẩm mới
**Request:**
```
POST http://localhost:8080/api/v1/products/6/print-areas
Content-Type: application/json

{
  "name": "Front",
  "widthMm": 320.00,
  "heightMm": 380.00,
  "topOffsetMm": 60.00,
  "leftOffsetMm": 0.00,
  "maskImageUrl": null
}
```

**Expected:**
- Status: 201 Created
- PrintArea được tạo với baseProductId = 6

**Kiểm tra:**
- ✅ PrintArea được tạo thành công
- ✅ Dimensions đúng

**Tạo thêm print area:**
- Back với dimensions tương tự

---

#### Bước 4: Xem lại sản phẩm đã tạo với variants và print areas
**Request:**
```
GET http://localhost:8080/api/v1/products/6/detail
```

**Expected:**
- Status: 200 OK
- Product có đầy đủ variants và printAreas vừa tạo

**Kiểm tra:**
- ✅ Có đủ variants đã tạo
- ✅ Có đủ printAreas đã tạo
- ✅ Relationships đúng

---

#### Bước 5: Cập nhật sản phẩm
**Request:**
```
PUT http://localhost:8080/api/v1/products/6
Content-Type: application/json

{
  "name": "Áo Polo Premium Updated",
  "basePrice": 380000.00
}
```

**Expected:**
- Status: 200 OK
- Product được update (chỉ update các fields được gửi)

**Kiểm tra:**
- ✅ Name được update
- ✅ BasePrice được update
- ✅ Các fields khác không thay đổi
- ✅ updatedAt được cập nhật

---

#### Bước 6: Cập nhật variant
**Request:**
```
PUT http://localhost:8080/api/v1/products/variants/{variantId}
Content-Type: application/json

{
  "stockQuantity": 75,
  "priceAdjustment": 20000.00
}
```

**Expected:**
- Status: 200 OK
- Variant được update

**Kiểm tra:**
- ✅ StockQuantity được update
- ✅ PriceAdjustment được update
- ✅ Các fields khác không thay đổi

---

#### Bước 7: Vô hiệu hóa sản phẩm
**Request:**
```
PATCH http://localhost:8080/api/v1/products/6/deactivate
```

**Expected:**
- Status: 200 OK
- Product `active` = false

**Kiểm tra:**
- ✅ GET `/api/v1/products?activeOnly=true` không còn product này
- ✅ GET `/api/v1/products` (không filter) vẫn thấy nhưng `active: false`

---

#### Bước 8: Kích hoạt lại sản phẩm
**Request:**
```
PATCH http://localhost:8080/api/v1/products/6/activate
```

**Expected:**
- Status: 200 OK
- Product `active` = true

**Kiểm tra:**
- ✅ GET `/api/v1/products?activeOnly=true` lại thấy product này

---

#### Bước 9: Xóa variant
**Request:**
```
DELETE http://localhost:8080/api/v1/products/variants/{variantId}
```

**Expected:**
- Status: 200 OK
- Variant bị xóa

**Kiểm tra:**
- ✅ GET `/api/v1/products/6/variants` không còn variant này

---

#### Bước 10: Xóa print area
**Request:**
```
DELETE http://localhost:8080/api/v1/products/print-areas/{printAreaId}
```

**Expected:**
- Status: 200 OK
- PrintArea bị xóa

**Kiểm tra:**
- ✅ GET `/api/v1/products/6/print-areas` không còn printArea này

---

#### Bước 11: Xóa sản phẩm (cascade)
**Request:**
```
DELETE http://localhost:8080/api/v1/products/6
```

**Expected:**
- Status: 200 OK
- Product và tất cả variants/printAreas bị xóa (cascade)

**Kiểm tra:**
- ✅ GET `/api/v1/products/6` → 404 Not Found
- ✅ GET `/api/v1/products/6/variants` → 404 Not Found
- ✅ GET `/api/v1/products/6/print-areas` → 404 Not Found

---

## 🎯 LUỒNG TEST NÂNG CAO

### Scenario 3: Test validation và error handling

#### Test 1: Tạo sản phẩm với tên trùng
**Request:**
```
POST http://localhost:8080/api/v1/products
Content-Type: application/json

{
  "name": "Áo Thun Cổ Tròn",  // Tên đã tồn tại
  "basePrice": 250000.00
}
```

**Expected:**
- Status: 400 Bad Request
- Error Code: 4050 (DUPLICATE_NAME)
- Message: "Name Áo Thun Cổ Tròn already exists"

---

#### Test 2: Tạo variant với SKU trùng
**Request:**
```
POST http://localhost:8080/api/v1/products/1/variants
Content-Type: application/json

{
  "sku": "TSHIRT-BLACK-S",  // SKU đã tồn tại
  "size": "M",
  "stockQuantity": 100
}
```

**Expected:**
- Status: 400 Bad Request
- Error Code: 4004 (SKU_ALREADY_EXISTS)
- Message: "SKU already exists: TSHIRT-BLACK-S"

---

#### Test 3: Validation errors
**Request:**
```
POST http://localhost:8080/api/v1/products
Content-Type: application/json

{
  "name": "",  // Empty name
  "basePrice": -100  // Negative price
}
```

**Expected:**
- Status: 400 Bad Request
- Validation errors cho từng field

---

#### Test 4: Không tìm thấy sản phẩm
**Request:**
```
GET http://localhost:8080/api/v1/products/999
```

**Expected:**
- Status: 404 Not Found
- Error Code: 3002 (PRODUCT_NOT_FOUND)
- Message: "Product not found with id: 999"

---

#### Test 5: Tạo variant cho product không tồn tại
**Request:**
```
POST http://localhost:8080/api/v1/products/999/variants
Content-Type: application/json

{
  "size": "M",
  "sku": "TEST-SKU",
  "stockQuantity": 10
}
```

**Expected:**
- Status: 404 Not Found
- Error Code: 3002 (PRODUCT_NOT_FOUND)

---

## 🎯 LUỒNG TEST TỔNG HỢP

### Scenario 4: Quản lý sản phẩm hoàn chỉnh

#### Bước 1: Tạo sản phẩm mới
```
POST /api/v1/products
→ Lưu productId
```

#### Bước 2: Tạo nhiều variants
```
POST /api/v1/products/{productId}/variants (màu Đen, size S)
POST /api/v1/products/{productId}/variants (màu Đen, size M)
POST /api/v1/products/{productId}/variants (màu Đen, size L)
POST /api/v1/products/{productId}/variants (màu Trắng, size S)
→ Lưu các variantIds
```

#### Bước 3: Tạo print areas
```
POST /api/v1/products/{productId}/print-areas (Front)
POST /api/v1/products/{productId}/print-areas (Back)
→ Lưu các printAreaIds
```

#### Bước 4: Verify tất cả đã được tạo
```
GET /api/v1/products/{productId}/detail
→ Kiểm tra có đủ variants và printAreas
```

#### Bước 5: Update stock cho variants
```
PUT /api/v1/products/variants/{variantId1} (stockQuantity: 100)
PUT /api/v1/products/variants/{variantId2} (stockQuantity: 150)
```

#### Bước 6: Update print area dimensions
```
PUT /api/v1/products/print-areas/{printAreaId1} (widthMm: 350)
```

#### Bước 7: Deactivate một variant
```
PUT /api/v1/products/variants/{variantId1}
{
  "active": false
}
```

#### Bước 8: Verify variant inactive không hiển thị
```
GET /api/v1/products/{productId}/variants
→ Kiểm tra variant inactive vẫn hiển thị (có thể filter sau)
```

#### Bước 9: Xóa một variant
```
DELETE /api/v1/products/variants/{variantId1}
```

#### Bước 10: Xóa một print area
```
DELETE /api/v1/products/print-areas/{printAreaId1}
```

#### Bước 11: Deactivate product
```
PATCH /api/v1/products/{productId}/deactivate
```

#### Bước 12: Verify product không hiển thị trong active list
```
GET /api/v1/products?activeOnly=true
→ Không thấy product này
```

#### Bước 13: Activate lại product
```
PATCH /api/v1/products/{productId}/activate
```

#### Bước 14: Verify product lại hiển thị
```
GET /api/v1/products?activeOnly=true
→ Thấy product này
```

#### Bước 15: Xóa product (cascade)
```
DELETE /api/v1/products/{productId}
```

#### Bước 16: Verify tất cả đã bị xóa
```
GET /api/v1/products/{productId} → 404
GET /api/v1/products/{productId}/variants → 404
GET /api/v1/products/{productId}/print-areas → 404
```

---

## 📝 CHECKLIST TEST THEO LUỒNG

### Luồng cơ bản (Khách hàng):
- [ ] GET all products (không filter)
- [ ] GET all products (activeOnly=true)
- [ ] GET product by ID
- [ ] GET product detail (với variants và printAreas)

### Luồng Admin - Tạo mới:
- [ ] POST create product
- [ ] POST create variant cho product
- [ ] POST create print area cho product
- [ ] GET product detail để verify

### Luồng Admin - Cập nhật:
- [ ] PUT update product (partial update)
- [ ] PUT update variant
- [ ] PUT update print area
- [ ] Verify updates thành công

### Luồng Admin - Quản lý trạng thái:
- [ ] PATCH deactivate product
- [ ] Verify product không hiển thị trong active list
- [ ] PATCH activate product
- [ ] Verify product lại hiển thị

### Luồng Admin - Xóa:
- [ ] DELETE variant
- [ ] DELETE print area
- [ ] DELETE product (cascade)
- [ ] Verify tất cả đã bị xóa

### Luồng Error Handling:
- [ ] Test duplicate name
- [ ] Test duplicate SKU
- [ ] Test validation errors
- [ ] Test not found errors

---

## 🔍 VERIFY DATABASE

Sau khi test, kiểm tra database:

```sql
-- Kết nối PostgreSQL
psql -U postgres -d pod_db

-- Kiểm tra số lượng
SELECT COUNT(*) FROM base_products;
SELECT COUNT(*) FROM product_variants;
SELECT COUNT(*) FROM print_areas;

-- Kiểm tra relationships
SELECT 
    bp.id,
    bp.name,
    COUNT(DISTINCT pv.id) AS variant_count,
    COUNT(DISTINCT pa.id) AS print_area_count
FROM base_products bp
LEFT JOIN product_variants pv ON bp.id = pv.base_product_id
LEFT JOIN print_areas pa ON bp.id = pa.base_product_id
GROUP BY bp.id, bp.name;

-- Kiểm tra data mới tạo
SELECT * FROM base_products ORDER BY id DESC LIMIT 5;
```

---

## 📊 TEST RESULTS TEMPLATE

### Test Case: [Tên test case]
- **Endpoint:** [URL]
- **Method:** [GET/POST/PUT/DELETE/PATCH]
- **Request Body:** [Nếu có]
- **Expected Status:** [200/201/400/404]
- **Expected Response:** [Mô tả]
- **Actual Result:** [Điền sau khi test]
- **Pass/Fail:** [✅/❌]

---

## 🎯 QUICK TEST COMMANDS

### Sử dụng cURL (nếu không có Postman):

```bash
# GET all products
curl http://localhost:8080/api/v1/products

# GET product detail
curl http://localhost:8080/api/v1/products/1/detail

# POST create product
curl -X POST http://localhost:8080/api/v1/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Test Product","basePrice":100000.00}'

# PUT update product
curl -X PUT http://localhost:8080/api/v1/products/1 \
  -H "Content-Type: application/json" \
  -d '{"name":"Updated Name"}'

# DELETE product
curl -X DELETE http://localhost:8080/api/v1/products/1
```

---

## ✅ KẾT QUẢ MONG ĐỢI

Sau khi test đầy đủ:
- ✅ Tất cả CRUD operations hoạt động đúng
- ✅ Validation errors được handle đúng
- ✅ Error codes đúng theo convention
- ✅ Relationships được maintain đúng
- ✅ Cascade delete hoạt động đúng
- ✅ Active/Inactive filter hoạt động đúng
