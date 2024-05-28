-- feature-data.sql
-- 插入特定功能测试的数据
INSERT INTO surveys (title, description) VALUES ('Feature Survey', 'Feature survey description');
SET @last_survey_id = LAST_INSERT_ID();

INSERT INTO questions (text, type, survey_id) VALUES ('What is your favorite color?', 'single-choice', @last_survey_id);
SET @last_question_id = LAST_INSERT_ID();

INSERT INTO options (option_text, question_id) VALUES ('Red', @last_question_id);
INSERT INTO options (option_text, question_id) VALUES ('Blue', @last_question_id);

-- 确保每次测试前清除数据
TRUNCATE TABLE responses;
