# Error Code Convention - Quy chuẩn mã lỗi

##Quy chuẩn mới (Recommended)

### Cấu trúc Error Code: `XXYY`
- **XX**: Category (2 chữ số) - Nhóm lỗi
- **YY**: Specific Error (2 chữ số) - Lỗi cụ thể trong nhóm

### Categories (XX)

| Code | Category | Mô tả |
|------|----------|-------|
| **10xx** | Authentication | Lỗi xác thực (login, token, password) |
| **11xx** | Authorization | Lỗi phân quyền (access denied, forbidden) |
| **20xx** | Validation | Lỗi validate input (email invalid, required field) |
| **30xx** | Resource Not Found | Không tìm thấy resource (user, product, order) |
| **40xx** | Business Logic | Lỗi nghiệp vụ (email exists, insufficient stock) |
| **50xx** | External Service | Lỗi service bên ngoài (Keycloak, payment gateway) |
| **60xx** | File/Upload | Lỗi upload, file processing |
| **90xx** | System Error | Lỗi hệ thống, unknown error |

### Chi tiết từng category

#### 10xx - Authentication Errors
```java
INVALID_CREDENTIALS(1001, "Invalid email or password", HttpStatus.UNAUTHORIZED),
INVALID_TOKEN(1002, "Token is invalid", HttpStatus.UNAUTHORIZED),
EXPIRED_TOKEN(1003, "Token has expired", HttpStatus.UNAUTHORIZED),
TOKEN_NOT_VERIFIED(1004, "Token is not verified", HttpStatus.UNAUTHORIZED),
INVALID_OTP(1005, "OTP is invalid", HttpStatus.UNAUTHORIZED),
PASSWORD_INCORRECT(1006, "Password is incorrect", HttpStatus.UNAUTHORIZED),
OLD_PASSWORD_NOT_MATCH(1007, "Old password does not match", HttpStatus.UNAUTHORIZED),
```

#### 11xx - Authorization Errors
```java
ACCESS_DENIED(1101, "Access denied", HttpStatus.FORBIDDEN),
INSUFFICIENT_PERMISSION(1102, "Insufficient permissions", HttpStatus.FORBIDDEN),
UNAUTHENTICATED(1103, "User is not authenticated", HttpStatus.UNAUTHORIZED),
```

#### 20xx - Validation Errors
```java
INVALID_EMAIL(2001, "Email format is invalid", HttpStatus.BAD_REQUEST),
INVALID_PHONE(2002, "Phone number format is invalid", HttpStatus.BAD_REQUEST),
REQUIRED_FIELD_MISSING(2003, "Required field {0} is missing", HttpStatus.BAD_REQUEST),
INVALID_INPUT(2004, "Invalid input for field {0}", HttpStatus.BAD_REQUEST),
```

#### 30xx - Resource Not Found
```java
USER_NOT_FOUND(3001, "User not found with {0}: {1}", HttpStatus.NOT_FOUND),
PRODUCT_NOT_FOUND(3002, "Product not found with {0}: {1}", HttpStatus.NOT_FOUND),
ORDER_NOT_FOUND(3003, "Order not found with {0}: {1}", HttpStatus.NOT_FOUND),
CATEGORY_NOT_FOUND(3004, "Category not found with {0}: {1}", HttpStatus.NOT_FOUND),
ROLE_NOT_FOUND(3005, "Role not found", HttpStatus.NOT_FOUND),
PERMISSION_NOT_FOUND(3006, "Permission not found", HttpStatus.NOT_FOUND),
```

#### 40xx - Business Logic Errors
```java
EMAIL_ALREADY_EXISTS(4001, "Email already exists: {0}", HttpStatus.BAD_REQUEST),
USERNAME_ALREADY_EXISTS(4002, "Username already exists: {0}", HttpStatus.BAD_REQUEST),
INSUFFICIENT_STOCK(4003, "Insufficient stock for {0}. Available: {1}, Requested: {2}", HttpStatus.BAD_REQUEST),
ORDER_ALREADY_CANCELLED(4004, "Order {0} has already been cancelled", HttpStatus.BAD_REQUEST),
USER_ALREADY_DELETED(4005, "User has been deleted", HttpStatus.BAD_REQUEST),
DUPLICATE_NAME(4006, "Name {0} already exists", HttpStatus.BAD_REQUEST),
```

#### 50xx - External Service Errors
```java
KEYCLOAK_USER_CREATION_FAILED(5001, "Failed to create user in Keycloak", HttpStatus.INTERNAL_SERVER_ERROR),
KEYCLOAK_TOKEN_ERROR(5002, "Failed to get Keycloak admin token", HttpStatus.INTERNAL_SERVER_ERROR),
PAYMENT_GATEWAY_ERROR(5003, "Payment gateway error: {0}", HttpStatus.INTERNAL_SERVER_ERROR),
EMAIL_SERVICE_ERROR(5004, "Failed to send email", HttpStatus.INTERNAL_SERVER_ERROR),
```

#### 60xx - File/Upload Errors
```java
FILE_UPLOAD_FAILED(6001, "Failed to upload file: {0}", HttpStatus.INTERNAL_SERVER_ERROR),
INVALID_FILE_FORMAT(6002, "Invalid file format. Expected: {0}", HttpStatus.BAD_REQUEST),
FILE_TOO_LARGE(6003, "File size exceeds limit. Max: {0}MB", HttpStatus.BAD_REQUEST),
EXCEL_HEADER_MISSING(6004, "Excel file is missing required headers", HttpStatus.BAD_REQUEST),
```

#### 90xx - System Errors
```java
UNKNOWN_ERROR(9001, "An unknown error occurred", HttpStatus.INTERNAL_SERVER_ERROR),
DATABASE_ERROR(9002, "Database error occurred", HttpStatus.INTERNAL_SERVER_ERROR),
INTERNAL_SERVER_ERROR(9003, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
```

## Ví dụ Response với Error Code mới

```json
{
  "code": 4001,
  "message": "Email already exists: test@example.com",
  "timestamp": 1706112000000
}
```

## Frontend xử lý Error Code

```javascript
// Frontend có thể switch case dựa vào error code
switch (error.code) {
  case 1001: // INVALID_CREDENTIALS
    showLoginError("Email hoặc mật khẩu không đúng");
    break;
    
  case 1003: // EXPIRED_TOKEN
    refreshToken();
    break;
    
  case 3001: // USER_NOT_FOUND
    redirectTo404();
    break;
    
  case 4001: // EMAIL_ALREADY_EXISTS
    highlightEmailField("Email này đã được sử dụng");
    break;
    
  case 9001: // UNKNOWN_ERROR
    showGenericError();
    logToSentry(error);
    break;
}
```

## Tài liệu tham khảo

- HTTP Status Codes: https://developer.mozilla.org/en-US/docs/Web/HTTP/Status
- REST API Error Handling Best Practices
- Google API Design Guide - Errors
