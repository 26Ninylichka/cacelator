
CREATE TABLE   currency
(
    currency_code TEXT PRIMARY KEY,
    name          TEXT NOT NULL,
    symbol        TEXT NOT NULL
);

CREATE TABLE  unit
(
    unit_id        UUID PRIMARY KEY     ,
    code           TEXT UNIQUE,          -- e.g. kg, g, ml, l, pcs, cm
    name           TEXT        NOT NULL,
    unit_type      TEXT        NOT NULL, -- MASS / VOLUME / COUNT / LENGTH
    to_base_factor NUMERIC,
    base_code      TEXT,
    created_at     TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at     TIMESTAMPTZ NOT NULL DEFAULT now()
);

CREATE TABLE  locale
(
    locale_id     UUID PRIMARY KEY    ,
    language      TEXT        NOT NULL,
    country       TEXT,
    metric_system TEXT, -- METRIC / IMPERIAL
    created_at    TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at    TIMESTAMPTZ NOT NULL DEFAULT now()
);



CREATE TABLE  app_user
(
    user_id      UUID PRIMARY KEY     ,
    email        TEXT        NOT NULL UNIQUE,
    display_name TEXT,
    created_at   TIMESTAMPTZ NOT NULL DEFAULT now(),
    password     TEXT        NOT NULL,
    updated_at   TIMESTAMPTZ NOT NULL DEFAULT now(),
    status       TEXT,
    phone_number TEXT        NOT NULL
);


CREATE TABLE   user_settings
(
    user_id             UUID PRIMARY KEY,
    locale_id           UUID        NOT NULL,
    currency_code       TEXT        NOT NULL,
    base_mass_unit_id   UUID        NOT NULL,
    base_volume_unit_id UUID        NOT NULL,
    base_length_unit_id UUID        NOT NULL,
    base_count_unit_id  UUID        NOT NULL,
    created_at          TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at          TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT fk_settings_user
        FOREIGN KEY (user_id) REFERENCES app_user (user_id) ON DELETE CASCADE,
    CONSTRAINT fk_settings_locale
        FOREIGN KEY (locale_id) REFERENCES locale (locale_id),
    CONSTRAINT fk_settings_currency
        FOREIGN KEY (currency_code) REFERENCES currency (currency_code),
    CONSTRAINT fk_settings_mass_unit
        FOREIGN KEY (base_mass_unit_id) REFERENCES unit (unit_id),
    CONSTRAINT fk_settings_volume_unit
        FOREIGN KEY (base_volume_unit_id) REFERENCES unit (unit_id),
    CONSTRAINT fk_settings_length_unit
        FOREIGN KEY (base_length_unit_id) REFERENCES unit (unit_id),
    CONSTRAINT fk_settings_count_unit
        FOREIGN KEY (base_count_unit_id) REFERENCES unit (unit_id)
);



CREATE TABLE   product
(
    product_id      UUID PRIMARY KEY     ,
    user_id         UUID        NOT NULL,
    name            TEXT        NOT NULL UNIQUE,
    default_unit_id UUID        NOT NULL,
    created_at      TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at      TIMESTAMPTZ NOT NULL DEFAULT now(),
    status          TEXT        NOT NULL,
    note            TEXT        NOT NULL,
    is_active       BOOLEAN,

    CONSTRAINT fk_product_user
        FOREIGN KEY (user_id) REFERENCES app_user (user_id) ON DELETE CASCADE,
    CONSTRAINT fk_product_default_unit
        FOREIGN KEY (default_unit_id) REFERENCES unit (unit_id)
);

CREATE TABLE  product_package_price
(
    package_price_id UUID PRIMARY KEY     ,
    product_id       UUID        NOT NULL,
    package_qty      NUMERIC,
    package_unit_id  UUID        NOT NULL,
    package_price    NUMERIC,
    currency_code    TEXT        NOT NULL,
    valid_from       DATE,
    valid_to         DATE,
    created_at       TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT fk_ppp_product
        FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE,
    CONSTRAINT fk_ppp_unit
        FOREIGN KEY (package_unit_id) REFERENCES unit (unit_id),
    CONSTRAINT fk_ppp_currency
        FOREIGN KEY (currency_code) REFERENCES currency (currency_code)
);

CREATE TABLE  component
(
    component_id UUID PRIMARY KEY    ,
    user_id      UUID        NOT NULL,
    name         TEXT        NOT NULL UNIQUE,
    description  TEXT,
    created_at   TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT fk_component_user
        FOREIGN KEY (user_id) REFERENCES app_user (user_id) ON DELETE CASCADE
);

CREATE TABLE  component_item
(
    component_item_id UUID PRIMARY KEY ,
    component_id      UUID NOT NULL,
    product_id        UUID NOT NULL,
    qty               NUMERIC,
    unit_id           UUID NOT NULL,
    sort_order        INT  NOT NULL,

    CONSTRAINT fk_ci_component
        FOREIGN KEY (component_id) REFERENCES component (component_id) ON DELETE CASCADE,
    CONSTRAINT fk_ci_product
        FOREIGN KEY (product_id) REFERENCES product (product_id),
    CONSTRAINT fk_ci_unit
        FOREIGN KEY (unit_id) REFERENCES unit (unit_id)
);


CREATE TABLE  desert
(
    desert_id           UUID PRIMARY KEY     ,
    user_id             UUID        NOT NULL,
    name                TEXT        NOT NULL UNIQUE,
    default_diameter_cm NUMERIC,
    default_weight_g    NUMERIC,
    default_height_cm   NUMERIC,
    servings_default    INT         NOT NULL,
    created_at          TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT fk_desert_user
        FOREIGN KEY (user_id) REFERENCES app_user (user_id) ON DELETE CASCADE
);

CREATE TABLE  desert_component
(
    desert_component_id UUID PRIMARY KEY ,
    desert_id           UUID NOT NULL,
    component_id        UUID NOT NULL,
    sort_order          INT  NOT NULL,
    note                TEXT,

    CONSTRAINT fk_dc_desert
        FOREIGN KEY (desert_id) REFERENCES desert (desert_id) ON DELETE CASCADE,
    CONSTRAINT fk_dc_component
        FOREIGN KEY (component_id) REFERENCES component (component_id)
);



CREATE TABLE   calc_run
(
    calc_run_id        UUID PRIMARY KEY   ,
    user_id            UUID      NOT NULL,
    dessert_id         UUID      NOT NULL,
    input_mode         TEXT, -- BY_DIAMETER / BY_WEIGHT / BY_SERVINGS
    target_diameter_cm NUMERIC,
    target_weight_g    NUMERIC,
    target_height_cm   NUMERIC,
    target_servings    INT       NOT NULL,
    scale_factor       NUMERIC   NOT NULL,
    created_at         TIMESTAMP NOT NULL DEFAULT now(),

    CONSTRAINT fk_calc_run_user
        FOREIGN KEY (user_id) REFERENCES app_user (user_id) ON DELETE CASCADE,
    CONSTRAINT fk_calc_run_desert
        FOREIGN KEY (dessert_id) REFERENCES desert (desert_id) ON DELETE CASCADE
);

CREATE TABLE   calc_item
(
    calc_item_id  UUID PRIMARY KEY ,
    calc_run_id   UUID    NOT NULL,
    product_id    UUID    NOT NULL,
    total_qty     NUMERIC NOT NULL,
    unit_id       UUID    NOT NULL,
    cost_amount   NUMERIC NOT NULL,
    currency_code TEXT    NOT NULL,

    CONSTRAINT fk_calc_item_run
        FOREIGN KEY (calc_run_id) REFERENCES calc_run (calc_run_id) ON DELETE CASCADE,
    CONSTRAINT fk_calc_item_product
        FOREIGN KEY (product_id) REFERENCES product (product_id),
    CONSTRAINT fk_calc_item_unit
        FOREIGN KEY (unit_id) REFERENCES unit (unit_id),
    CONSTRAINT fk_calc_item_currency
        FOREIGN KEY (currency_code) REFERENCES currency (currency_code)
);


