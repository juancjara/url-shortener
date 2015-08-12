
-- name: find-url
-- find real url
SELECT id,
       short,
       original,
       clicks
  FROM shortener
  WHERE short = :short
  LIMIT 1;

-- name: count-urls
-- count urls
SELECT count(*)
  FROM shortener;

-- name: inc-clicks<!
-- Increment clicks on a specific url
UPDATE shortener SET clicks = clicks + 1
  WHERE id = :id;

-- name: insert-url<!
-- insert new url data
INSERT INTO shortener (short, original, clicks)
   VALUES (:short, :original, 0)

-- name: all-urls
-- Select all urls
SELECT id,
       short,
       original,
       clicks
  FROM shortener;
-- name: create-shortener-table-if-not-exists!
-- create shortener if table does not exists

CREATE TABLE IF NOT EXISTS shortener (
  id serial PRIMARY KEY,
  short VARCHAR NOT NULL,
  original VARCHAR NOT NULL,
  clicks INTEGER NOT NULL);
