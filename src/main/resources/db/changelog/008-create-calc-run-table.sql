CREATE TABLE calc_run (
                          id UUID PRIMARY KEY,
                          user_id UUID NOT NULL,
                          dessert_id UUID NOT NULL,
                          input_mode VARCHAR(50) NOT NULL,
                          target_weight NUMERIC(10,2),
                          target_diameter NUMERIC(10,2),
                          target_servings INTEGER,
                          scale_factor NUMERIC(10,4) NOT NULL,
                          created_at TIMESTAMP NOT NULL,
                          updated_at TIMESTAMP NOT NULL
);