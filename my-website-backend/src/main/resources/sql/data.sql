-- Insert default roles
INSERT INTO roles (id, name, created_at, updated_at) VALUES (1, 'ROLE_ADMIN', NOW(), NOW());
INSERT INTO roles (id, name, created_at, updated_at) VALUES (2, 'ROLE_USER', NOW(), NOW());

-- Insert default admin user
-- Using a placeholder BCrypt hash for 'adminpass'. Replace with a real hash.
-- Example hash for 'adminpass': $2a$10$8.A5zP8z7k3i3j6k8L9mN.O9zQ7X6Y5Z4W3V2U1S0R.P0E.N2Q2S
INSERT INTO users (username, password_hash, email, created_at, updated_at)
VALUES ('admin', '$2a$10$8.A5zP8z7k3i3j6k8L9mN.O9zQ7X6Y5Z4W3V2U1S0R.P0E.N2Q2S', 'admin@example.com', NOW(), NOW());

-- Assign ROLE_ADMIN to the admin user
-- Assuming the admin user gets id=1 (or adjust if your DB assigns differently, though with AUTO_INCREMENT it's likely)
INSERT INTO user_roles (user_id, role_id)
VALUES (
    (SELECT id FROM users WHERE username = 'admin'),
    (SELECT id FROM roles WHERE name = 'ROLE_ADMIN')
);
