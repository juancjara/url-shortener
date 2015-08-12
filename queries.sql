-- name: create-shortener-table-if-not-exists!
-- create shortener if table does not exists

CREATE TABLE IF NOT EXISTS shortener (
  id serial PRIMARY KEY,
  short VARCHAR NOT NULL,
  original VARCHAR NOT NULL,
  clicks INTEGER NOT NULL);
