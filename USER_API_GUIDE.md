# User API Documentation

## Cấu trúc dự án

```
com.shirt.pod
├── controller
│   └── UserController.java          # REST Controller - Xử lý HTTP requests
├── service
│   └── UserService.java              # Business Logic Layer
├── repository
│   └── UserRepository.java           # Data Access Layer
├── mapper
│   └── UserMapper.java               # MapStruct Mapper - Chuyển đổi Entity <-> DTO
└── model
    ├── entity
    │   └── User.java                 # Entity - Mapping với database
    └── dto
        ├── request
        │   └── CreateUserRequest.java # Request DTO
        └── response
            ├── UserDTO.java          # Response DTO
            └── ApiResponse.java      # Wrapper cho tất cả API responses
```

## API Endpoints

### 1. Lấy danh sách tất cả users
```http
GET /api/v1/users
```

**Response:**
```json
{
  "data": [
    {
      "id": 1,
      "email": "user@example.com",
      "fullName": "Nguyen Van A",
      "phoneNumber": "0123456789",
      "avatarUrl": "https://example.com/avatar.jpg",
      "status": "ACTIVE",
      "roles": ["CUSTOMER"],
      "createdAt": "2026-01-24T22:00:00",
      "updatedAt": "2026-01-24T22:00:00"
    }
  ],
  "code": 200,
  "message": "Get all users successfully",
  "timestamp": 1706112000000
}
```
