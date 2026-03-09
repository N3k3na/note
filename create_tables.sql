
-- Table candidat
CREATE TABLE candidat (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL
);

-- Table matiere
CREATE TABLE matiere (
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(200) NOT NULL
);

-- Table feuille
CREATE TABLE feuille (
    id SERIAL PRIMARY KEY,
    idcandidat INTEGER NOT NULL,
    idfeuille INTEGER NOT NULL,
    FOREIGN KEY (idcandidat) REFERENCES candidat(id)
);

-- Table prof
CREATE TABLE prof (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL
);

-- Table note
CREATE TABLE note (
    id SERIAL PRIMARY KEY,
    idfeuille INTEGER NOT NULL,
    idprof INTEGER NOT NULL,
    note DECIMAL(4,2) NOT NULL,
    FOREIGN KEY (idfeuille) REFERENCES feuille(id),
    FOREIGN KEY (idprof) REFERENCES prof(id)
);

-- Table resolution
CREATE TABLE resolution (
    id SERIAL PRIMARY KEY,
    a_prendre VARCHAR(20) NOT NULL
);

-- Insertion des valeurs pour resolution
INSERT INTO resolution (a_prendre) VALUES 
    ('maximum'),
    ('moyenne'),
    ('minimum');

-- Table comparateur
CREATE TABLE comparateur (
    id SERIAL PRIMARY KEY,
    comparateur VARCHAR(10) NOT NULL
);

-- Insertion des valeurs pour comparateur
INSERT INTO comparateur (comparateur) VALUES 
    ('sup'),
    ('inf'),
    ('supEgal'),
    ('infEgal');

-- Table parametre
CREATE TABLE parametre (
    id SERIAL PRIMARY KEY,
    idmatiere INTEGER NOT NULL,
    valeur DECIMAL(10,2) NOT NULL,
    idcomparateur INTEGER NOT NULL,
    idresolution INTEGER NOT NULL,
    FOREIGN KEY (idmatiere) REFERENCES matiere(id),
    FOREIGN KEY (idcomparateur) REFERENCES comparateur(id),
    FOREIGN KEY (idresolution) REFERENCES resolution(id)
);