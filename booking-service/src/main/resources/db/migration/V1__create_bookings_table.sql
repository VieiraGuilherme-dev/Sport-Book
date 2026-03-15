CREATE TABLE bookings (
    id             BIGSERIAL PRIMARY KEY,
    court_id       BIGINT        NOT NULL,
    customer_name  VARCHAR(100)  NOT NULL,
    customer_email VARCHAR(150)  NOT NULL,
    customer_phone VARCHAR(20),
    booking_date   DATE          NOT NULL,
    start_time     TIME          NOT NULL,
    end_time       TIME          NOT NULL,
    total_price    NUMERIC(10,2) NOT NULL,
    status         VARCHAR(20)   NOT NULL DEFAULT 'PENDING',
    notes          TEXT,
    created_at     TIMESTAMP     NOT NULL DEFAULT NOW(),
    updated_at     TIMESTAMP     NOT NULL DEFAULT NOW()
);

CREATE INDEX idx_bookings_court_id ON bookings(court_id);
CREATE INDEX idx_bookings_date     ON bookings(booking_date);
CREATE INDEX idx_bookings_status   ON bookings(status);
CREATE INDEX idx_bookings_email    ON bookings(customer_email);