CREATE TABLE component_item (
                                id UUID PRIMARY KEY,
                                component_id UUID NOT NULL,
                                product_id UUID NOT NULL,
                                quantity NUMERIC(10,2) NOT NULL,
                                unit VARCHAR(50) NOT NULL,
                                sort_order INTEGER NOT NULL
);