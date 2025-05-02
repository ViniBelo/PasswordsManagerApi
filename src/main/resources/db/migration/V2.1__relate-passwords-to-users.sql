ALTER TABLE passwords
    ADD COLUMN user_id UUID NOT NULL,
    ADD CONSTRAINT passwords_user_id_fkey FOREIGN KEY (user_id) REFERENCES users(id);