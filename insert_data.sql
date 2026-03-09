-- Insertion des résolutions
INSERT INTO resolution (a_prendre) VALUES 
    ('maximum'),
    ('moyenne'),
    ('minimum');

-- Insertion des comparateurs
INSERT INTO comparateur (comparateur) VALUES 
    ('sup'),
    ('inf'),
    ('supEgal'),
    ('infEgal');

-- Insertion des matières
INSERT INTO matiere (libelle) VALUES 
    ('JAVA'),       -- id: 1
    ('PHP');        -- id: 2

-- Insertion des professeurs
INSERT INTO prof (nom) VALUES 
    ('Correcteur1'), -- id: 1
    ('Correcteur2'), -- id: 2
    ('Correcteur3'); -- id: 3

-- Insertion des candidats (étudiants)
INSERT INTO candidat (nom, prenom) VALUES      -- id: 4
    ('Candidate1', 'Candidate1'), -- id: 1
    ('Candidate2', 'Candidate2'); -- id: 2

-- Insertion des paramètres pour la matière physique (id_matiere = 1)
INSERT INTO parametre (idmatiere, valeur, idcomparateur, idresolution) VALUES 
    (1, 7,  2, 1),  -- physique - 5 - inf (2) - minimum (3)
    (1, 7, 3, 2),  -- physique - 10 - inf (2) - moyenne (2)
    (2, 2, 4, 3);  -- physique - 10 - sup (1) - maximum (1)
INSERT INTO parametre (idmatiere, valeur, idcomparateur, idresolution) VALUES 
    (2, 2, 1, 1);   -- JAVA - 3 - inf - maximum

-- Insertion des feuilles pour les exemples physique
-- Thomas (id_candidat = 1, matière physique = 1)
INSERT INTO feuille (idcandidat, idmatiere) VALUES (1, 1); -- feuille id: 1

-- Jean (id_candidat = 2, matière physique = 1)
INSERT INTO feuille (idcandidat, idmatiere) VALUES (1, 2); -- feuille id: 2

-- Tim (id_candidat = 3, matière physique = 1)
INSERT INTO feuille (idcandidat, idmatiere) VALUES (2, 1); -- feuille id: 3

-- Karol (id_candidat = 4, matière physique = 1)
INSERT INTO feuille (idcandidat, idmatiere) VALUES (2, 2); -- feuille id: 4

-- Insertion des notes pour Thomas (feuille id: 1)
INSERT INTO note (idfeuille, idprof, note) VALUES 
    (1, 1, 15),  -- Prof1
    (1, 2, 10),  -- Prof2
    (1, 3, 12);  -- Prof3

-- Insertion des notes pour Jean (feuille id: 2)
INSERT INTO note (idfeuille, idprof, note) VALUES 
    (3, 1, 9),  -- Prof1
    (3, 2, 8),  -- Prof2
    (3, 3, 11);  -- Prof3

-- Insertion des notes pour Tim (feuille id: 3)
INSERT INTO note (idfeuille, idprof, note) VALUES 
    (2, 1, 10),  -- Prof1
    (2, 2, 10);  -- Prof3

-- Insertion des notes pour Karol (feuille id: 4)
INSERT INTO note (idfeuille, idprof, note) VALUES 
    (4, 1, 13),  -- Prof2
    (4, 2, 11);  -- Prof3
