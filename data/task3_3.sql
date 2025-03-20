-- TODO Task 3
USE csf_pyp_2024

-- Orders table to store the main order information
CREATE TABLE orders (
    order_id VARCHAR(26) NOT NULL PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    name VARCHAR(128) NOT NULL,
    address VARCHAR(256) NOT NULL,
    priority BOOLEAN NOT NULL DEFAULT FALSE,
    comments TEXT
);

-- Line items table to store the products in each order
CREATE TABLE line_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id VARCHAR(26) NOT NULL,
    product_id VARCHAR(64) NOT NULL,
    name VARCHAR(128) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE
);

-- Index for faster lookups on order_id in line_items
CREATE INDEX idx_line_items_order_id ON line_items(order_id);