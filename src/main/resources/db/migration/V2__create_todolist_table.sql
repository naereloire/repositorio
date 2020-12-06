CREATE TABLE IF NOT EXISTS migrations.to_do (
    id INT2,
    title TEXT NOT NULL,
    description TEXT NOT NULL,
    status TEXT NOT NULL,
    tag TEXT NOT NULL,
    PRIMARY KEY (id)
);