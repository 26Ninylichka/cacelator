CREATE TABLE dessert (
                         id UUID PRIMARY KEY,
                         user_id UUID NOT NULL,
                         name VARCHAR(255) NOT NULL,
                         description VARCHAR(1000),
                         created_at TIMESTAMP NOT NULL,
                         updated_at TIMESTAMP NOT NULL
);

CREATE UNIQUE INDEX uk_dessert_user_id_name
    ON dessert (user_id, name);