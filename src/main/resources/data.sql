
USE shopping_cart;

-- Insertar cupones
INSERT INTO coupon (code, description, discount, discount_type, discount_value, active, valid_from, valid_to)
VALUES
    ('SAVE10', 'Descuento del 10%', 10.00, 'PERCENTAGE', 10.00, TRUE, NOW(), DATE_ADD(NOW(), INTERVAL 30 DAY)),
    ('SAVE50', 'Descuento de $50', 50.00, 'FIXED', 50.00, TRUE, NOW(), DATE_ADD(NOW(), INTERVAL 60 DAY)),
    ('SUMMER2024', 'Promoción de verano', 20.00, 'PERCENTAGE', 20.00, TRUE, NOW(), DATE_ADD(NOW(), INTERVAL 90 DAY));

-- Insertar productos
INSERT INTO product (name, price, description, active)
VALUES
    ('Laptop Dell XPS 13', 999.99, 'Laptop ultradelgada de alta performance', TRUE),
    ('Mouse Logitech MX Master', 99.99, 'Mouse inalámbrico profesional', TRUE),
    ('Teclado Mecánico RGB', 149.99, 'Teclado mecánico con iluminación RGB', TRUE),
    ('Monitor LG 27 4K', 399.99, 'Monitor 4K de 27 pulgadas', TRUE),
    ('Webcam HD 1080p', 79.99, 'Cámara web para conferencias', TRUE);

-- Insertar órdenes
INSERT INTO customer_order (order_number, status, gross_total, discount_total, final_total, coupon_id, payment_method, payment_status, payment_country, billing_name, billing_tax_id, billing_street, billing_city, billing_postal_code, billing_country, shipping_name, shipping_street, shipping_city, shipping_postal_code, shipping_country)
VALUES
    ('ORD-001-2024', 'COMPLETED', 1249.97, 100.00, 1149.97, 1, 'CREDIT_CARD', 'PAID', 'ES', 'Juan García López', 'ES12345678A', 'Calle Principal 123', 'Madrid', '28001', 'España', 'Juan García López', 'Calle Principal 123', 'Madrid', '28001', 'España'),
    ('ORD-002-2024', 'PENDING', 579.97, 50.00, 529.97, 2, 'PAYPAL', 'PENDING', 'ES', 'María Rodríguez Martín', 'ES87654321B', 'Avenida Secundaria 456', 'Barcelona', '08002', 'España', 'María Rodríguez Martín', 'Avenida Secundaria 456', 'Barcelona', '08002', 'España'),
    ('ORD-003-2024', 'SHIPPED', 699.98, 139.99, 559.99, 3, 'DEBIT_CARD', 'PAID', 'ES', 'Carlos Fernández López', 'ES11111111C', 'Plaza Mayor 789', 'Valencia', '46001', 'España', 'Carlos Fernández López', 'Plaza Mayor 789', 'Valencia', '46001', 'España');

-- Insertar items de órdenes
INSERT INTO order_item (product_name, unit_price, quantity, order_id, product_id)
VALUES
    ('Laptop Dell XPS 13', 999.99, 1, 1, 1),
    ('Mouse Logitech MX Master', 99.99, 1, 1, 2),
    ('Teclado Mecánico RGB', 149.99, 2, 2, 3),
    ('Monitor LG 27 4K', 399.99, 1, 3, 4),
    ('Webcam HD 1080p', 79.99, 1, 3, 5);
