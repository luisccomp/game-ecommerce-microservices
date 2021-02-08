CREATE TABLE users (
    id         UUID           NOT NULL,
    username   VARCHAR(255)   NOT NULL UNIQUE,
    password   VARCHAR(255)   NOT NULL,
    active     BOOLEAN        NOT NULL DEFAULT TRUE,
    created_at TIMESTAMPTZ(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    updated_at TIMESTAMPTZ(6)     NULL DEFAULT CURRENT_TIMESTAMP(6),
    CONSTRAINT users_pk PRIMARY KEY (id)
);

CREATE TABLE roles(
    id         UUID           NOT NULL,
    role       VARCHAR(255)   NOT NULL,
    created_at TIMESTAMPTZ(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    updated_at TIMESTAMPTZ(6)     NULL DEFAULT CURRENT_TIMESTAMP(6),
    CONSTRAINT roles_pk PRIMARY KEY (id)
);

CREATE TABLE users_roles(
    id      UUID NOT NULL,
    user_id UUID NOT NULL,
    role_id UUID NOT NULL,
    CONSTRAINT roles_users_pk PRIMARY KEY (id),
    CONSTRAINT roles_users_fk_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT roles_users_fk_role FOREIGN KEY (role_id) REFERENCES roles(id)
);

INSERT INTO roles(id, role) VALUES ('ad700e86-710e-4f70-ac9d-2821307585d3', 'ADMIN');
INSERT INTO roles(id, role) VALUES ('dccfcfd0-eeb3-4a45-8194-a21982249d0b', 'SELLER');
INSERT INTO roles(id, role) VALUES ('26298244-1fa8-4e1e-9b85-875250460897', 'USER');