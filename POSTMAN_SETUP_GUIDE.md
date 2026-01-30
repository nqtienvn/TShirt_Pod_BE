# 📋 HƯỚNG DẪN SETUP VÀ TEST VỚI POSTMAN

## ✅ BƯỚC 1: SET ENVIRONMENT VARIABLE

### Cách 1: Tạo Environment mới (Khuyến nghị)

1. **Click vào "Environments" ở sidebar bên trái** (hoặc icon góc trên bên phải)

2. **Click "+" để tạo Environment mới**

3. **Đặt tên:** `Local Development` (hoặc tên bạn muốn)

4. **Thêm variable:**
   - **Variable:** `baseUrl`
   - **Initial Value:** `http://localhost:8080`
   - **Current Value:** `http://localhost:8080`

5. **Click "Save"**

6. **Chọn Environment này** từ dropdown ở góc trên bên phải (bên cạnh "No Environment")

---

### Cách 2: Set trực tiếp trong Collection

1. **Click vào collection "Product & Variants API"** ở sidebar trái

2. **Click tab "Variables"** (bên cạnh Overview)

3. **Thêm variable:**
   - **Variable:** `baseUrl`
   - **Current Value:** `http://localhost:8080`

4. **Click "Save"**

---

## ✅ BƯỚC 2: TEST REQUEST ĐẦU TIÊN

### Test GET All Products:

1. **Mở folder "Products"** ở sidebar trái (click vào mũi tên để expand)

2. **Click vào request "Get All Products"**

3. **Kiểm tra URL:**
   - Phải hiển thị: `{{baseUrl}}/api/v1/products`
   - Nếu thấy `{{baseUrl}}` màu đỏ → Chưa set environment, quay lại Bước 1

4. **Click nút "Send"** (màu xanh, góc trên bên phải)

5. **Xem Response:**
   - Status: `200 OK`
   - Body: JSON với danh sách 5 sản phẩm từ seed data

**Kết quả mong đợi:**
```json
{
  "code": 200,
  "message": "Get all products successfully",
  "data": [
    {
      "id": 1,
      "name": "Áo Thun Cổ Tròn",
      ...
    },
    ...
  ]
}
```

---

## ✅ BƯỚC 3: TEST CÁC REQUEST KHÁC

### Luồng test cơ bản (theo thứ tự):

#### 1. Products Folder:
- ✅ **Get All Products** → Xem danh sách
- ✅ **Get Product By ID** → Đổi ID = 1 trong URL
- ✅ **Get Product Detail** → Xem với variants và printAreas
- ✅ **Create Product** → Tạo sản phẩm mới (lưu ID từ response)
- ✅ **Update Product** → Update sản phẩm
- ✅ **Activate/Deactivate Product** → Test activate/deactivate

#### 2. Variants Folder:
- ✅ **Get Variants By Product ID** → Xem variants của product ID=1
- ✅ **Create Variant** → Tạo variant mới (đổi productId trong URL)
- ✅ **Update Variant** → Update variant (đổi variantId trong URL)
- ✅ **Delete Variant** → Xóa variant

#### 3. Print Areas Folder:
- ✅ **Get Print Areas By Product ID** → Xem print areas của product ID=1
- ✅ **Create Print Area** → Tạo print area mới
- ✅ **Update Print Area** → Update print area
- ✅ **Delete Print Area** → Xóa print area

#### 4. Error Cases Folder:
- ✅ **Get Product Not Found** → Test 404 error
- ✅ **Create Product Duplicate Name** → Test duplicate error
- ✅ **Create Variant Duplicate SKU** → Test duplicate SKU error
- ✅ **Create Product Validation Error** → Test validation errors

---

## ✅ BƯỚC 4: CÁCH SỬ DỤNG POSTMAN

### Thay đổi ID trong URL:
1. Click vào request
2. Trong URL bar, thay `{id}` hoặc `1` bằng ID bạn muốn
3. Ví dụ: `/api/v1/products/1` → `/api/v1/products/6`

### Thay đổi Request Body:
1. Click vào request
2. Click tab "Body"
3. Chọn "raw" và "JSON"
4. Sửa nội dung JSON
5. Click "Send"

### Xem Response:
- **Status:** Màu xanh = thành công, màu đỏ = lỗi
- **Body:** JSON response từ server
- **Time:** Thời gian request
- **Size:** Kích thước response

### Lưu Response:
1. Click "Save Response"
2. Chọn "Save as example"
3. Response sẽ được lưu để xem lại sau

---

## ✅ BƯỚC 5: TEST THEO LUỒNG NGHIỆP VỤ

### Luồng 1: Xem sản phẩm (Khách hàng)

```
1. Products → Get All Products
   → Kiểm tra: Có 5 sản phẩm

2. Products → Get Product By ID
   → Đổi ID = 1
   → Kiểm tra: Có đầy đủ thông tin

3. Products → Get Product Detail
   → Đổi ID = 1
   → Kiểm tra: Có variants và printAreas
```

**Thời gian:** ~2 phút

---

### Luồng 2: Tạo sản phẩm mới (Admin)

```
1. Products → Create Product
   Body: {
     "name": "Áo Polo Test",
     "basePrice": 300000.00,
     "material": "Cotton",
     "active": true
   }
   → Lưu ID từ response (ví dụ: id = 6)

2. Variants → Create Variant
   → Đổi URL: /api/v1/products/6/variants
   Body: {
     "colorName": "Đen",
     "size": "M",
     "sku": "POLO-BLACK-M-TEST",
     "stockQuantity": 50
   }
   → Tạo variant

3. Print Areas → Create Print Area
   → Đổi URL: /api/v1/products/6/print-areas
   Body: {
     "name": "Front",
     "widthMm": 300.00,
     "heightMm": 350.00
   }
   → Tạo print area

4. Products → Get Product Detail
   → Đổi ID = 6
   → Verify: Có variant và printArea vừa tạo
```

**Thời gian:** ~5 phút

---

### Luồng 3: Test Error Cases

```
1. Error Cases → Get Product Not Found
   → Kiểm tra: 404 Not Found

2. Error Cases → Create Product Duplicate Name
   → Kiểm tra: 400 Bad Request, Error Code 4050

3. Error Cases → Create Variant Duplicate SKU
   → Kiểm tra: 400 Bad Request, Error Code 4004

4. Error Cases → Create Product Validation Error
   → Kiểm tra: 400 Bad Request, Validation errors
```

**Thời gian:** ~3 phút

---

## 🎯 QUICK REFERENCE

### Các vị trí quan trọng trong Postman:

1. **Sidebar trái:** 
   - Collections → Product & Variants API → Folders → Requests

2. **URL Bar:** 
   - Góc trên giữa, hiển thị URL của request
   - Có thể edit trực tiếp

3. **Send Button:** 
   - Góc trên bên phải, màu xanh
   - Click để gửi request

4. **Response Panel:** 
   - Phần dưới, hiển thị kết quả
   - Có tabs: Body, Headers, Cookies, etc.

5. **Environment Dropdown:** 
   - Góc trên bên phải
   - Chọn environment đã tạo

---

## ✅ CHECKLIST TEST NHANH

### Setup (1 phút):
- [ ] Set environment variable `baseUrl = http://localhost:8080`
- [ ] Chọn environment đã tạo
- [ ] Verify URL hiển thị đúng (không có màu đỏ)

### Test GET (2 phút):
- [ ] Get All Products → 200 OK, có 5 products
- [ ] Get Product By ID (id=1) → 200 OK
- [ ] Get Product Detail (id=1) → 200 OK, có variants và printAreas

### Test POST (3 phút):
- [ ] Create Product → 201 Created
- [ ] Create Variant → 201 Created
- [ ] Create Print Area → 201 Created

### Test Error (2 phút):
- [ ] Get Product Not Found → 404
- [ ] Create Duplicate Name → 400
- [ ] Create Duplicate SKU → 400

**Tổng thời gian:** ~8 phút để test cơ bản

---

## 🚀 BẮT ĐẦU TEST NGAY

### Bước đầu tiên:

1. **Set Environment:**
   - Click "Environments" ở sidebar trái
   - Tạo mới: `baseUrl = http://localhost:8080`
   - Chọn environment này

2. **Test request đầu tiên:**
   - Mở: **Products → Get All Products**
   - Click **"Send"**
   - Xem response có 5 sản phẩm không

3. **Nếu thành công:**
   - ✅ API đang hoạt động
   - ✅ Có thể tiếp tục test các requests khác

4. **Nếu lỗi:**
   - Kiểm tra ứng dụng đang chạy: `http://localhost:8080`
   - Kiểm tra environment variable đã set đúng chưa
   - Kiểm tra URL trong request có đúng không

---

## 📝 LƯU Ý

- **Environment variable:** Phải set trước khi test, nếu không URL sẽ không resolve
- **ID trong URL:** Có thể thay đổi trực tiếp trong URL bar
- **Request Body:** Có thể copy từ examples trong Swagger UI
- **Save responses:** Có thể save để so sánh sau này

---

## 🎯 NEXT STEPS

Sau khi test xong:
1. ✅ Verify tất cả endpoints hoạt động
2. ✅ Verify error handling đúng
3. ✅ Document các issues (nếu có)
4. ✅ Sẵn sàng để demo hoặc deploy
