CREATE TABLE app_users (
                           id BIGSERIAL PRIMARY KEY,
                           email VARCHAR(255),
                           password VARCHAR(255),
                           first_name VARCHAR(255),
                           last_name VARCHAR(255),
                           role VARCHAR(50),
                           created_at TIMESTAMP,
                           updated_at TIMESTAMP
);

CREATE TABLE products (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255),
                          description TEXT,
                          price NUMERIC(19, 2),
                          stock INTEGER,
                          category VARCHAR(255),
                          created_at TIMESTAMP,
                          updated_at TIMESTAMP
);

CREATE TABLE carts (
                       id BIGSERIAL PRIMARY KEY,
                       user_id BIGINT NOT NULL,
                       total_price NUMERIC(19, 2),
                       updated_at TIMESTAMP,
                       CONSTRAINT fk_carts_user
                           FOREIGN KEY (user_id)
                               REFERENCES app_users(id)
                               ON DELETE CASCADE
);

CREATE TABLE cart_items (
                            id BIGSERIAL PRIMARY KEY,
                            cart_id BIGINT NOT NULL,
                            product_id BIGINT NOT NULL,
                            quantity INTEGER NOT NULL,
                            unit_price NUMERIC(19, 2) NOT NULL,
                            total_price NUMERIC(19, 2) NOT NULL,
                            CONSTRAINT fk_cart_items_cart
                                FOREIGN KEY (cart_id)
                                    REFERENCES carts(id)
                                    ON DELETE CASCADE,
                            CONSTRAINT fk_cart_items_product
                                FOREIGN KEY (product_id)
                                    REFERENCES products(id)
                                    ON DELETE RESTRICT
);

CREATE TABLE orders (
                        id BIGSERIAL PRIMARY KEY,
                        user_id BIGINT NOT NULL,
                        status VARCHAR(50) NOT NULL,
                        total_price NUMERIC(19, 2) NOT NULL,
                        created_at TIMESTAMP NOT NULL,
                        CONSTRAINT fk_orders_user
                            FOREIGN KEY (user_id)
                                REFERENCES app_users(id)
                                ON DELETE RESTRICT
);

CREATE TABLE order_items (
                             id BIGSERIAL PRIMARY KEY,
                             order_id BIGINT NOT NULL,
                             product_id BIGINT NOT NULL,
                             quantity INTEGER NOT NULL,
                             unit_price NUMERIC(19, 2) NOT NULL,
                             total_price NUMERIC(19, 2) NOT NULL,
                             CONSTRAINT fk_order_items_order
                                 FOREIGN KEY (order_id)
                                     REFERENCES orders(id)
                                     ON DELETE CASCADE,
                             CONSTRAINT fk_order_items_product
                                 FOREIGN KEY (product_id)
                                     REFERENCES products(id)
                                     ON DELETE RESTRICT
);