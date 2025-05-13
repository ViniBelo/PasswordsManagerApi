ALTER TABLE passwords
    ADD COLUMN platform_id UUID NOT NULL,
    ADD CONSTRAINT passwords_platform_id_fkey FOREIGN KEY (platform_id) REFERENCES platforms(id);