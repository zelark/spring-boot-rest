INSERT INTO process (description, completed) VALUES
  ('Setting up our application', 1),
  ('Instantiation', 0);

INSERT INTO task (parent_id, description, completed) VALUES
  (1, 'task 01', 0),
  (1, 'task 02', 0),
  (1, 'task 03', 0);
