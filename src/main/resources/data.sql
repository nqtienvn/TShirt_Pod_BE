-- ============================================
-- SEED DATA: PRODUCTS, VARIANTS, PRINT AREAS
-- ============================================
-- 5 sản phẩm mẫu: Áo thun, Cốc, Túi tote, Áo hoodie, Mũ lưỡi trai
-- ============================================
-- Lưu ý: Schema thực tế sử dụng created_at, updated_at (không có created_by, modified_by)
-- print_areas và product_variants không có timestamp columns trong schema
-- ============================================

-- ============================================
-- 1. ÁO THUN (T-SHIRT)
-- ============================================
INSERT INTO base_products (name, description, base_price, material, active, created_at, updated_at)
VALUES ('Áo Thun Cổ Tròn', 'Áo thun cotton 100%, thoáng mát, dễ giặt', 250000.00, 'Cotton 100%', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Print Areas cho Áo Thun
INSERT INTO print_areas (base_product_id, name, width_mm, height_mm, top_offset_mm, left_offset_mm, mask_image_url)
VALUES 
    ((SELECT id FROM base_products WHERE name = 'Áo Thun Cổ Tròn'), 'Front', 300.00, 350.00, 50.00, 0.00, NULL),
    ((SELECT id FROM base_products WHERE name = 'Áo Thun Cổ Tròn'), 'Back', 300.00, 350.00, 50.00, 0.00, NULL);

-- Variants cho Áo Thun
INSERT INTO product_variants (base_product_id, color_name, color_hex, size, sku, stock_quantity, image_url, price_adjustment, active)
VALUES 
    ((SELECT id FROM base_products WHERE name = 'Áo Thun Cổ Tròn'), 'Đen', '#000000', 'S', 'TSHIRT-BLACK-S', 100, 'https://example.com/images/tshirt-black-s.jpg', 0.00, true),
    ((SELECT id FROM base_products WHERE name = 'Áo Thun Cổ Tròn'), 'Đen', '#000000', 'M', 'TSHIRT-BLACK-M', 150, 'https://example.com/images/tshirt-black-m.jpg', 0.00, true),
    ((SELECT id FROM base_products WHERE name = 'Áo Thun Cổ Tròn'), 'Đen', '#000000', 'L', 'TSHIRT-BLACK-L', 120, 'https://example.com/images/tshirt-black-l.jpg', 0.00, true),
    ((SELECT id FROM base_products WHERE name = 'Áo Thun Cổ Tròn'), 'Trắng', '#FFFFFF', 'S', 'TSHIRT-WHITE-S', 80, 'https://example.com/images/tshirt-white-s.jpg', 0.00, true),
    ((SELECT id FROM base_products WHERE name = 'Áo Thun Cổ Tròn'), 'Trắng', '#FFFFFF', 'M', 'TSHIRT-WHITE-M', 100, 'https://example.com/images/tshirt-white-m.jpg', 0.00, true),
    ((SELECT id FROM base_products WHERE name = 'Áo Thun Cổ Tròn'), 'Trắng', '#FFFFFF', 'L', 'TSHIRT-WHITE-L', 90, 'https://example.com/images/tshirt-white-l.jpg', 0.00, true),
    ((SELECT id FROM base_products WHERE name = 'Áo Thun Cổ Tròn'), 'Xanh Navy', '#001F3F', 'M', 'TSHIRT-NAVY-M', 60, 'https://example.com/images/tshirt-navy-m.jpg', 20000.00, true),
    ((SELECT id FROM base_products WHERE name = 'Áo Thun Cổ Tròn'), 'Xanh Navy', '#001F3F', 'L', 'TSHIRT-NAVY-L', 70, 'https://example.com/images/tshirt-navy-l.jpg', 20000.00, true);

-- ============================================
-- 2. CỐC (MUG)
-- ============================================
INSERT INTO base_products (name, description, base_price, material, active, created_at, updated_at)
VALUES ('Cốc Sứ In Hình', 'Cốc sứ cao cấp, dung tích 350ml, có thể in hình 360 độ', 150000.00, 'Sứ Ceramic', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Print Areas cho Cốc
INSERT INTO print_areas (base_product_id, name, width_mm, height_mm, top_offset_mm, left_offset_mm, mask_image_url)
VALUES 
    ((SELECT id FROM base_products WHERE name = 'Cốc Sứ In Hình'), 'Full Wrap', 280.00, 100.00, 0.00, 0.00, NULL);

-- Variants cho Cốc (One Size)
INSERT INTO product_variants (base_product_id, color_name, color_hex, size, sku, stock_quantity, image_url, price_adjustment, active)
VALUES 
    ((SELECT id FROM base_products WHERE name = 'Cốc Sứ In Hình'), 'Trắng', '#FFFFFF', 'One Size', 'MUG-WHITE-OS', 200, 'https://example.com/images/mug-white.jpg', 0.00, true),
    ((SELECT id FROM base_products WHERE name = 'Cốc Sứ In Hình'), 'Đen', '#000000', 'One Size', 'MUG-BLACK-OS', 150, 'https://example.com/images/mug-black.jpg', 30000.00, true),
    ((SELECT id FROM base_products WHERE name = 'Cốc Sứ In Hình'), 'Xanh Dương', '#0066CC', 'One Size', 'MUG-BLUE-OS', 100, 'https://example.com/images/mug-blue.jpg', 25000.00, true);

-- ============================================
-- 3. TÚI TOTE (TOTE BAG)
-- ============================================
INSERT INTO base_products (name, description, base_price, material, active, created_at, updated_at)
VALUES ('Túi Tote Canvas', 'Túi tote canvas bền chắc, kích thước 40x40cm, có thể in mặt trước và sau', 180000.00, 'Canvas', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Print Areas cho Túi Tote
INSERT INTO print_areas (base_product_id, name, width_mm, height_mm, top_offset_mm, left_offset_mm, mask_image_url)
VALUES 
    ((SELECT id FROM base_products WHERE name = 'Túi Tote Canvas'), 'Front', 350.00, 400.00, 50.00, 25.00, NULL),
    ((SELECT id FROM base_products WHERE name = 'Túi Tote Canvas'), 'Back', 350.00, 400.00, 50.00, 25.00, NULL);

-- Variants cho Túi Tote (One Size)
INSERT INTO product_variants (base_product_id, color_name, color_hex, size, sku, stock_quantity, image_url, price_adjustment, active)
VALUES 
    ((SELECT id FROM base_products WHERE name = 'Túi Tote Canvas'), 'Trắng', '#FFFFFF', 'One Size', 'TOTE-WHITE-OS', 80, 'https://example.com/images/tote-white.jpg', 0.00, true),
    ((SELECT id FROM base_products WHERE name = 'Túi Tote Canvas'), 'Đen', '#000000', 'One Size', 'TOTE-BLACK-OS', 90, 'https://example.com/images/tote-black.jpg', 0.00, true),
    ((SELECT id FROM base_products WHERE name = 'Túi Tote Canvas'), 'Xám', '#808080', 'One Size', 'TOTE-GRAY-OS', 70, 'https://example.com/images/tote-gray.jpg', 20000.00, true),
    ((SELECT id FROM base_products WHERE name = 'Túi Tote Canvas'), 'Xanh Rêu', '#556B2F', 'One Size', 'TOTE-OLIVE-OS', 50, 'https://example.com/images/tote-olive.jpg', 30000.00, true);

-- ============================================
-- 4. ÁO HOODIE
-- ============================================
INSERT INTO base_products (name, description, base_price, material, active, created_at, updated_at)
VALUES ('Áo Hoodie Có Mũ', 'Áo hoodie dày dặn, có mũ trùm đầu, phù hợp mùa đông', 450000.00, 'Cotton 80% + Polyester 20%', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Print Areas cho Áo Hoodie
INSERT INTO print_areas (base_product_id, name, width_mm, height_mm, top_offset_mm, left_offset_mm, mask_image_url)
VALUES 
    ((SELECT id FROM base_products WHERE name = 'Áo Hoodie Có Mũ'), 'Front', 350.00, 400.00, 60.00, 0.00, NULL),
    ((SELECT id FROM base_products WHERE name = 'Áo Hoodie Có Mũ'), 'Back', 350.00, 450.00, 50.00, 0.00, NULL),
    ((SELECT id FROM base_products WHERE name = 'Áo Hoodie Có Mũ'), 'Sleeve Left', 100.00, 500.00, 0.00, 0.00, NULL),
    ((SELECT id FROM base_products WHERE name = 'Áo Hoodie Có Mũ'), 'Sleeve Right', 100.00, 500.00, 0.00, 0.00, NULL);

-- Variants cho Áo Hoodie
INSERT INTO product_variants (base_product_id, color_name, color_hex, size, sku, stock_quantity, image_url, price_adjustment, active)
VALUES 
    ((SELECT id FROM base_products WHERE name = 'Áo Hoodie Có Mũ'), 'Đen', '#000000', 'M', 'HOODIE-BLACK-M', 40, 'https://example.com/images/hoodie-black-m.jpg', 0.00, true),
    ((SELECT id FROM base_products WHERE name = 'Áo Hoodie Có Mũ'), 'Đen', '#000000', 'L', 'HOODIE-BLACK-L', 50, 'https://example.com/images/hoodie-black-l.jpg', 0.00, true),
    ((SELECT id FROM base_products WHERE name = 'Áo Hoodie Có Mũ'), 'Đen', '#000000', 'XL', 'HOODIE-BLACK-XL', 35, 'https://example.com/images/hoodie-black-xl.jpg', 0.00, true),
    ((SELECT id FROM base_products WHERE name = 'Áo Hoodie Có Mũ'), 'Xám', '#808080', 'M', 'HOODIE-GRAY-M', 30, 'https://example.com/images/hoodie-gray-m.jpg', 50000.00, true),
    ((SELECT id FROM base_products WHERE name = 'Áo Hoodie Có Mũ'), 'Xám', '#808080', 'L', 'HOODIE-GRAY-L', 40, 'https://example.com/images/hoodie-gray-l.jpg', 50000.00, true),
    ((SELECT id FROM base_products WHERE name = 'Áo Hoodie Có Mũ'), 'Xanh Navy', '#001F3F', 'L', 'HOODIE-NAVY-L', 25, 'https://example.com/images/hoodie-navy-l.jpg', 70000.00, true);

-- ============================================
-- 5. MŨ LƯỠI TRAI (CAP)
-- ============================================
INSERT INTO base_products (name, description, base_price, material, active, created_at, updated_at)
VALUES ('Mũ Lưỡi Trai', 'Mũ lưỡi trai thể thao, có thể điều chỉnh size, in được mặt trước và mặt sau', 120000.00, 'Polyester + Cotton', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Print Areas cho Mũ Lưỡi Trai
INSERT INTO print_areas (base_product_id, name, width_mm, height_mm, top_offset_mm, left_offset_mm, mask_image_url)
VALUES 
    ((SELECT id FROM base_products WHERE name = 'Mũ Lưỡi Trai'), 'Front Panel', 200.00, 80.00, 20.00, 0.00, NULL),
    ((SELECT id FROM base_products WHERE name = 'Mũ Lưỡi Trai'), 'Back Panel', 200.00, 80.00, 20.00, 0.00, NULL);

-- Variants cho Mũ Lưỡi Trai (One Size với điều chỉnh)
INSERT INTO product_variants (base_product_id, color_name, color_hex, size, sku, stock_quantity, image_url, price_adjustment, active)
VALUES 
    ((SELECT id FROM base_products WHERE name = 'Mũ Lưỡi Trai'), 'Đen', '#000000', 'One Size', 'CAP-BLACK-OS', 120, 'https://example.com/images/cap-black.jpg', 0.00, true),
    ((SELECT id FROM base_products WHERE name = 'Mũ Lưỡi Trai'), 'Trắng', '#FFFFFF', 'One Size', 'CAP-WHITE-OS', 100, 'https://example.com/images/cap-white.jpg', 0.00, true),
    ((SELECT id FROM base_products WHERE name = 'Mũ Lưỡi Trai'), 'Xanh Navy', '#001F3F', 'One Size', 'CAP-NAVY-OS', 80, 'https://example.com/images/cap-navy.jpg', 20000.00, true),
    ((SELECT id FROM base_products WHERE name = 'Mũ Lưỡi Trai'), 'Đỏ', '#DC143C', 'One Size', 'CAP-RED-OS', 60, 'https://example.com/images/cap-red.jpg', 25000.00, true),
    ((SELECT id FROM base_products WHERE name = 'Mũ Lưỡi Trai'), 'Xanh Lá', '#228B22', 'One Size', 'CAP-GREEN-OS', 50, 'https://example.com/images/cap-green.jpg', 25000.00, true);

-- ============================================
-- SUMMARY
-- ============================================
-- Tổng cộng:
-- - 5 Base Products
-- - 15 Print Areas (2-4 areas mỗi sản phẩm)
-- - 30 Product Variants (6-8 variants mỗi sản phẩm)
-- ============================================
