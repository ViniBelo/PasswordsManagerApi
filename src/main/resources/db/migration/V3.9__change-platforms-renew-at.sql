ALTER TABLE platforms
    DROP COLUMN renew_at,
    ADD COLUMN renew_in INT;