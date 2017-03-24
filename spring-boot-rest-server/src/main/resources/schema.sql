CREATE TABLE process (
  id INTEGER IDENTITY PRIMARY KEY,
  description VARCHAR(64) NOT NULL,
  completed BIT NOT NULL
);

CREATE TABLE task (
  id INTEGER IDENTITY PRIMARY KEY,
  parent_id INTEGER,
  description VARCHAR(64) NOT NULL,
  completed BIT NOT NULL,
  FOREIGN KEY (parent_id) REFERENCES process(id)
);
