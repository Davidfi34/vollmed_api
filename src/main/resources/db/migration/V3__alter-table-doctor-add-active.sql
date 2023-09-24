ALTER TABLE doctors add active tinyint;
UPDATE doctors SET active = 1;