ALTER TABLE patients add active tinyint;
UPDATE patients SET active = 1;