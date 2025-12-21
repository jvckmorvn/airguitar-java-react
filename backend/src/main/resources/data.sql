-- CLEAR DATA
DELETE FROM guitar;
ALTER TABLE guitar AUTO_INCREMENT = 1;

-- GUITARS
INSERT INTO guitar (model, manufacturer) VALUES ('Stratocaster', 'Fender');
INSERT INTO guitar (model, manufacturer) VALUES ('Les Paul', 'Gibson');
