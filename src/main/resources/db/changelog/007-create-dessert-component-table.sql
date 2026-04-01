CREATE TABLE dessert_component (
                                   id UUID PRIMARY KEY,
                                   dessert_id UUID NOT NULL,
                                   component_id UUID NOT NULL,
                                   sort_order INTEGER,
                                   note VARCHAR(1000)
);