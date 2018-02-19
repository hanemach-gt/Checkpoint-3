.open database.db
.mode csv

DROP TABLE IF EXISTS books;
DROP TABLE IF EXISTS authors;
DROP TABLE IF EXISTS publishers;
DROP TABLE IF EXISTS type_books;

CREATE TABLE books(
  ISBN TEXT NOT NULL,
  author INTEGER NOT NULL,
  title TEXT NOT NULL,
  publisher TEXT NOT NULL,
  publication_year INTEGER NOT NULL,
  price REAL NOT NULL,
  type INTEGER NOT NULL
);
.import ./Books.csv books

CREATE TABLE authors(
  author_id INTEGER PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL,
  surname TEXT NOT NULL,
  birth_year INTEGER NOT NULL,
  city TEXT NOT NULL,
  country TEXT NOT NULL
);
.import ./Authors.csv authors

CREATE TABLE publishers(
  publisher_id TEXT PRIMARY KEY NOT NULL,
  name TEXT NOT NULL,
  city TEXT NOT NULL,
  country TEXT NOT NULL
);
.import ./Publishers.csv publishers

CREATE TABLE type_books(
  type_id INTEGER PRIMARY KEY AUTOINCREMENT,
  type TEXT NOT NULL
);
.import ./TypeBooks.csv type_books

.quit
