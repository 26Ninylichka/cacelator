CREATE TABLE IF NOT EXISTS component (
                                         id UUID PRIMARY KEY,
                                         user_id UUID NOT NULL,
                                         name VARCHAR(255) NOT NULL,
                                         description VARCHAR(1000),
                                         created_at TIMESTAMP NOT NULL,
                                         updated_at TIMESTAMP NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS uk_component_user_id_name
    ON component (user_id, name);