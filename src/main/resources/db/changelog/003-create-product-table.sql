CREATE TABLE IF NOT EXISTS product (
                                       id UUID PRIMARY KEY,
                                       user_id UUID NOT NULL,
                                       name VARCHAR(255) NOT NULL,
                                       default_unit_id UUID NOT NULL,
                                       status VARCHAR(100) NOT NULL,
                                       note VARCHAR(1000),
                                       is_active BOOLEAN NOT NULL,
                                       created_at TIMESTAMP NOT NULL,
                                       updated_at TIMESTAMP NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS uk_product_user_id_name
    ON product (user_id, name);