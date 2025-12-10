CREATE DATABASE shopping_cart;

USE shopping_cart;
-- =====================================================
-- TABLA: coupon
-- =====================================================
-- Descripción: Almacena los cupones de descuento disponibles
-- Propósito: Gestionar códigos promocionales y descuentos aplicables a órdenes
-- Relaciones: Referenciada por customer_order (1:N)
-- =====================================================
CREATE TABLE coupon (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,                    -- Identificador único del cupón
                        code VARCHAR(50) NOT NULL UNIQUE,                        -- Código promocional único (ej: SAVE10)
                        description VARCHAR(255),                                -- Descripción del cupón (ej: Descuento del 10%)
                        discount_type VARCHAR(50) NOT NULL,                      -- Tipo de descuento (PERCENTAGE o FIXED)
                        discount_value DECIMAL(10, 2) NOT NULL,                  -- Porcentaje o cantidad fija del descuento
                        active BOOLEAN DEFAULT TRUE,                             -- Indica si el cupón está activo
                        valid_from TIMESTAMP,                                    -- Fecha de inicio de validez del cupón
                        valid_to TIMESTAMP                                       -- Fecha de fin de validez del cupón
);

-- =====================================================
-- TABLA: product
-- =====================================================
-- Descripción: Almacena el catálogo de productos
-- Propósito: Mantener información de artículos disponibles para compra
-- Relaciones: Referenciada por order_item (1:N)
-- =====================================================
CREATE TABLE product (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,                    -- Identificador único del producto
                         name VARCHAR(255) NOT NULL,                              -- Nombre del producto
                         price DECIMAL(10, 2) NOT NULL,                           -- Precio unitario del producto
                         description TEXT,                                        -- Descripción detallada del producto
                         active BOOLEAN NOT NULL DEFAULT TRUE                     -- Indica si el producto está disponible
);

-- =====================================================
-- TABLA: customer_order
-- =====================================================
-- Descripción: Almacena las órdenes de compra de clientes
-- Propósito: Registrar todas las transacciones y detalles de facturación/envío
-- Relaciones:
--   - Referencia a coupon (N:1, opcional)
--   - Referenciada por order_item (1:N)
-- =====================================================
CREATE TABLE customer_order (
                                id BIGINT PRIMARY KEY AUTO_INCREMENT,                    -- Identificador único de la orden
                                order_number VARCHAR(50) NOT NULL UNIQUE,                -- Número de orden único para referencia
                                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,          -- Fecha y hora de creación de la orden
                                status VARCHAR(50) NOT NULL,                             -- Estado de la orden (PENDING, SHIPPED, COMPLETED, CANCELLED)
                                gross_total DECIMAL(10, 2) NOT NULL,                     -- Total antes de aplicar descuentos
                                discount_total DECIMAL(10, 2) NOT NULL,                  -- Monto total de descuentos aplicados
                                final_total DECIMAL(10, 2) NOT NULL,                     -- Total final a pagar (gross_total - discount_total)
                                coupon_id BIGINT,                                        -- Referencia al cupón aplicado (nullable)
                                payment_method VARCHAR(50) NOT NULL,                     -- Método de pago (CREDIT_CARD, PAYPAL, DEBIT_CARD)
                                payment_status VARCHAR(50) NOT NULL,                     -- Estado del pago (PENDING, PAID, FAILED, REFUNDED)
                                payment_country VARCHAR(100),                            -- País donde se procesó el pago
                                billing_name VARCHAR(255) NOT NULL,                      -- Nombre completo para facturación
                                billing_tax_id VARCHAR(50),                              -- ID fiscal/RUC para facturación
                                billing_street VARCHAR(255),                             -- Calle de la dirección de facturación
                                billing_city VARCHAR(100),                               -- Ciudad de facturación
                                billing_postal_code VARCHAR(20),                         -- Código postal de facturación
                                billing_country VARCHAR(100),                            -- País de facturación
                                shipping_name VARCHAR(255) NOT NULL,                     -- Nombre del destinatario del envío
                                shipping_street VARCHAR(255),                            -- Calle de la dirección de envío
                                shipping_city VARCHAR(100),                              -- Ciudad de envío
                                shipping_postal_code VARCHAR(20),                        -- Código postal de envío
                                shipping_country VARCHAR(100),                           -- País de envío
                                FOREIGN KEY (coupon_id) REFERENCES coupon(id)            -- Relación con tabla coupon
);

-- =====================================================
-- TABLA: order_item
-- =====================================================
-- Descripción: Almacena los artículos individuales dentro de cada orden
-- Propósito: Detallar qué productos se incluyeron en cada orden y sus cantidades
-- Relaciones:
--   - Referencia a customer_order (N:1, obligatoria)
--   - Referencia a product (N:1, opcional)
-- =====================================================
CREATE TABLE order_item (
                            id BIGINT PRIMARY KEY AUTO_INCREMENT,                    -- Identificador único del item de orden
                            product_name VARCHAR(255) NOT NULL,                      -- Nombre del producto en el momento de la compra
                            unit_price DECIMAL(10, 2) NOT NULL CHECK (unit_price > 0), -- Precio unitario al momento de compra
                            quantity INT NOT NULL CHECK (quantity > 0),              -- Cantidad de unidades compradas
                            line_total DECIMAL(10, 2) GENERATED ALWAYS AS (unit_price * quantity) STORED, -- Total de la línea (calculado)
                            order_id BIGINT NOT NULL,                                -- Referencia a la orden principal
                            product_id BIGINT,                                       -- Referencia al producto (nullable)
                            FOREIGN KEY (order_id) REFERENCES customer_order(id),    -- Relación obligatoria con customer_order
                            FOREIGN KEY (product_id) REFERENCES product(id)          -- Relación opcional con product
);

