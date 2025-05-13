CREATE TABLE platforms (
    id UUID PRIMARY KEY,
    nick VARCHAR(20) NOT NULL,
    user_id UUID REFERENCES users,
    renew_at TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP
);

CREATE TRIGGER updated_at_trigger
    BEFORE UPDATE ON platforms
    FOR EACH ROW
    EXECUTE FUNCTION set_updated_at();
