INSERT INTO matiere (libelle) VALUES 
    ('physique');

-- Insertion des professeurs (si nécessaire pour les exemples)
INSERT INTO prof (nom) VALUES 
    ('Prof1'),
    ('Prof2'),
    ('Prof3');

-- Insertion des candidats (étudiants)
INSERT INTO candidat (nom, prenom) VALUES 
    ('Thomas', 'Thomas'),
    ('Jean', 'Jean'),
    ('Tim', 'Tim'),
    ('Karol', 'Karol');

-- Insertion des paramètres pour la matière physique
-- parametre : physique - 5 - inf - minimum
INSERT INTO parametre (idmatiere, valeur, idcomparateur, idresolution) 
VALUES (
    (SELECT id FROM matiere WHERE libelle = 'physique'), 
    5, 
    (SELECT id FROM comparateur WHERE comparateur = 'inf'), 
    (SELECT id FROM resolution WHERE a_prendre = 'minimum')
);

-- parametre : physique - 10 - inf - moyenne
INSERT INTO parametre (idmatiere, valeur, idcomparateur, idresolution) 
VALUES (
    (SELECT id FROM matiere WHERE libelle = 'physique'), 
    10, 
    (SELECT id FROM comparateur WHERE comparateur = 'inf'), 
    (SELECT id FROM resolution WHERE a_prendre = 'moyenne')
);

-- parametre : physique - 10 - sup - maximum
INSERT INTO parametre (idmatiere, valeur, idcomparateur, idresolution) 
VALUES (
    (SELECT id FROM matiere WHERE libelle = 'physique'), 
    10, 
    (SELECT id FROM comparateur WHERE comparateur = 'sup'), 
    (SELECT id FROM resolution WHERE a_prendre = 'maximum')
);

-- Pour Thomas (physique / note1: 13 / note2: 13 / note3: 13)
-- Création d'une feuille pour Thomas
INSERT INTO feuille (idcandidat, idfeuille) 
VALUES (
    (SELECT id FROM candidat WHERE prenom = 'Thomas'), 
    1
);

-- Insertion des notes de Thomas
INSERT INTO note (idfeuille, idprof, note) 
VALUES 
    ((SELECT id FROM feuille WHERE idcandidat = (SELECT id FROM candidat WHERE prenom = 'Thomas') AND idfeuille = 1), 
     (SELECT id FROM prof WHERE nom = 'Prof1'), 13),
    ((SELECT id FROM feuille WHERE idcandidat = (SELECT id FROM candidat WHERE prenom = 'Thomas') AND idfeuille = 1), 
     (SELECT id FROM prof WHERE nom = 'Prof2'), 13),
    ((SELECT id FROM feuille WHERE idcandidat = (SELECT id FROM candidat WHERE prenom = 'Thomas') AND idfeuille = 1), 
     (SELECT id FROM prof WHERE nom = 'Prof3'), 13);

-- Pour Jean (physique / note1: 14 / note2: 18 / note3: 12)
-- Création d'une feuille pour Jean
INSERT INTO feuille (idcandidat, idfeuille) 
VALUES (
    (SELECT id FROM candidat WHERE prenom = 'Jean'), 
    2
);

-- Insertion des notes de Jean
INSERT INTO note (idfeuille, idprof, note) 
VALUES 
    ((SELECT id FROM feuille WHERE idcandidat = (SELECT id FROM candidat WHERE prenom = 'Jean') AND idfeuille = 2), 
     (SELECT id FROM prof WHERE nom = 'Prof1'), 14),
    ((SELECT id FROM feuille WHERE idcandidat = (SELECT id FROM candidat WHERE prenom = 'Jean') AND idfeuille = 2), 
     (SELECT id FROM prof WHERE nom = 'Prof2'), 18),
    ((SELECT id FROM feuille WHERE idcandidat = (SELECT id FROM candidat WHERE prenom = 'Jean') AND idfeuille = 2), 
     (SELECT id FROM prof WHERE nom = 'Prof3'), 12);

-- Pour Tim (physique / note1: 10 / note2: 12 / note3: 12)
-- Création d'une feuille pour Tim
INSERT INTO feuille (idcandidat, idfeuille) 
VALUES (
    (SELECT id FROM candidat WHERE prenom = 'Tim'), 
    3
);

-- Insertion des notes de Tim
INSERT INTO note (idfeuille, idprof, note) 
VALUES 
    ((SELECT id FROM feuille WHERE idcandidat = (SELECT id FROM candidat WHERE prenom = 'Tim') AND idfeuille = 3), 
     (SELECT id FROM prof WHERE nom = 'Prof1'), 10),
    ((SELECT id FROM feuille WHERE idcandidat = (SELECT id FROM candidat WHERE prenom = 'Tim') AND idfeuille = 3), 
     (SELECT id FROM prof WHERE nom = 'Prof2'), 12),
    ((SELECT id FROM feuille WHERE idcandidat = (SELECT id FROM candidat WHERE prenom = 'Tim') AND idfeuille = 3), 
     (SELECT id FROM prof WHERE nom = 'Prof3'), 12);

-- Pour Karol (physique / note1: 11 / note2: 10 / note3: 14)
-- Création d'une feuille pour Karol
INSERT INTO feuille (idcandidat, idfeuille) 
VALUES (
    (SELECT id FROM candidat WHERE prenom = 'Karol'), 
    4
);

-- Insertion des notes de Karol
INSERT INTO note (idfeuille, idprof, note) 
VALUES 
    ((SELECT id FROM feuille WHERE idcandidat = (SELECT id FROM candidat WHERE prenom = 'Karol') AND idfeuille = 4), 
     (SELECT id FROM prof WHERE nom = 'Prof1'), 11),
    ((SELECT id FROM feuille WHERE idcandidat = (SELECT id FROM candidat WHERE prenom = 'Karol') AND idfeuille = 4), 
     (SELECT id FROM prof WHERE nom = 'Prof2'), 10),
    ((SELECT id FROM feuille WHERE idcandidat = (SELECT id FROM candidat WHERE prenom = 'Karol') AND idfeuille = 4), 
     (SELECT id FROM prof WHERE nom = 'Prof3'), 14);

-- Vérification des données insérées
-- Afficher toutes les notes avec les noms des étudiants
SELECT 
    c.nom || ' ' || c.prenom AS etudiant,
    n.note,
    p.nom AS professeur
FROM note n
JOIN feuille f ON n.idfeuille = f.id
JOIN candidat c ON f.idcandidat = c.id
JOIN prof p ON n.idprof = p.id
ORDER BY c.prenom, n.note;

-- Afficher les paramètres pour la matière physique
SELECT 
    m.libelle AS matiere,
    p.valeur,
    c.comparateur,
    r.a_prendre AS resolution
FROM parametre p
JOIN matiere m ON p.idmatiere = m.id
JOIN comparateur c ON p.idcomparateur = c.id
JOIN resolution r ON p.idresolution = r.id
WHERE m.libelle = 'physique'
ORDER BY p.valeur;