-- Insert into `users` table
INSERT INTO users (name, age, gender, phone, password, email) VALUES
                                                                  ('John Doe', 30, 1, '1234567890', 'password', 'john.doe@example.com'),
                                                                  ('Jane Doe', 28, 0, '0987654321', 'password', 'jane.doe@example.com');

-- Get IDs of newly inserted users
SET @userId1 = (SELECT id FROM users WHERE email = 'john.doe@example.com');
SET @userId2 = (SELECT id FROM users WHERE email = 'jane.doe@example.com');

-- Insert into `surveys` table
INSERT INTO surveys (title, description, created_by) VALUES
                                                         ('Survey 1', 'This is survey 1', @userId1),
                                                         ('Survey 2', 'This is survey 2', @userId2);

-- Get IDs of newly inserted surveys
SET @surveyId1 = (SELECT id FROM surveys WHERE title = 'Survey 1');
SET @surveyId2 = (SELECT id FROM surveys WHERE title = 'Survey 2');

-- Insert into `questions` table
INSERT INTO questions (text, type, survey_id) VALUES
                                                  ('Question 1', 'single_choice', @surveyId1),
                                                  ('Question 2', 'multiple_choice', @surveyId1),
                                                  ('Question 3', 'single_choice', @surveyId2),
                                                  ('Question 4', 'multiple_choice', @surveyId2);

-- Get IDs of newly inserted questions
SET @questionId1 = (SELECT id FROM questions WHERE text = 'Question 1');
SET @questionId2 = (SELECT id FROM questions WHERE text = 'Question 2');
SET @questionId3 = (SELECT id FROM questions WHERE text = 'Question 3');
SET @questionId4 = (SELECT id FROM questions WHERE text = 'Question 4');

-- Insert into `options` table
INSERT INTO options (option_text, question_id) VALUES
                                                   ('Option 1', @questionId1),
                                                   ('Option 2', @questionId1),
                                                   ('Option 3', @questionId2),
                                                   ('Option 4', @questionId2),
                                                   ('Option 5', @questionId3),
                                                   ('Option 6', @questionId3),
                                                   ('Option 7', @questionId4),
                                                   ('Option 8', @questionId4);

-- Insert into `responses` table
INSERT INTO responses (answer_text, question_id) VALUES
                                                     ('Option', @questionId1),
                                                     ('Option', @questionId2),
                                                     ('Option', @questionId3),
                                                     ('Option', @questionId4);