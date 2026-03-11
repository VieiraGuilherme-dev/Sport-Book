CREATE TABLE courts (
    id             BIGSERIAL PRIMARY KEY,
    name           VARCHAR(100)   NOT NULL,
    sport_type     VARCHAR(50)    NOT NULL,
    location       VARCHAR(255)   NOT NULL,
    description    TEXT,
    price_per_hour NUMERIC(10,2)  NOT NULL,
    status         VARCHAR(20)    NOT NULL DEFAULT 'AVAILABLE',
    created_at     TIMESTAMP      NOT NULL DEFAULT NOW(),
    updated_at     TIMESTAMP      NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_courts_sport_type ON courts(sport_type);
CREATE INDEX idx_courts_status ON courts(status);