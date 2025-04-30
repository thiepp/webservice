-- Création explicite de la table 

CREATE TABLE IF NOT EXISTS livres (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    titre VARCHAR(255) NOT NULL,
    auteur VARCHAR(255) NOT NULL,
    isbn VARCHAR(20) UNIQUE NOT NULL,
    disponible BOOLEAN NOT NULL DEFAULT TRUE,
    date_publication DATE,
    version INTEGER NOT NULL DEFAULT 0
);
CREATE TABLE IF NOT EXISTS utilisateurs (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(100) NOT NULL,
    prenom VARCHAR(100) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS reservations (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    date_reservation TIMESTAMP NOT NULL,
    date_expiration TIMESTAMP,
    statut VARCHAR(20) CHECK (statut IN ('EN_ATTENTE','CONFIRMEE','ANNULEE')),
    livre_id BIGINT NOT NULL,
    utilisateur_id BIGINT NOT NULL,
    FOREIGN KEY (livre_id) REFERENCES livre(id),
    FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id)
);


CREATE TABLE IF NOT EXISTS prets (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    date_pret TIMESTAMP NOT NULL,
    date_retour_prevue TIMESTAMP,
    date_retour_effectif TIMESTAMP,
    version INTEGER NOT NULL,
    livre_id BIGINT NOT NULL,
    utilisateur_id BIGINT NOT NULL,
    FOREIGN KEY (livre_id) REFERENCES livre(id),
    FOREIGN KEY (utilisateur_id) REFERENCES utilisateur(id)
);

-- Insertion des données

INSERT INTO livres (titre, auteur, isbn, disponible, date_publication, version) 
VALUES 
('Clean Code', 'Robert C. Martin', '9780132350884', TRUE, '2008-08-01', 0),
('Design Patterns', 'Erich Gamma', '9780201633610', TRUE, '1994-10-21', 0),
('Spring in Action', 'Craig Walls', '9781617294945', FALSE, '2018-11-08', 0),
('Effective Java', 'Joshua Bloch', '9780134685991', TRUE, '2017-12-27', 0),
('The Pragmatic Programmer', 'David Thomas', '9780135957059', TRUE, '2019-09-13', 0);

INSERT INTO utilisateurs (nom, prenom, email, role) VALUES
('Dupont', 'Jean', 'jean.dupont@email.com', 'ETUDIANT'),
('Martin', 'Sophie', 'sophie.martin@email.com', 'PROFESSEUR'),
('Bernard', 'Pierre', 'pierre.bernard@email.bibliothecaire.com', 'BIBLIOTHECAIRE'),
('Petit', 'Marie', 'marie.petit@email.com', 'ETUDIANT'),
('Durand', 'Luc', 'luc.durand@email.com', 'ETUDIANT');

INSERT INTO reservations (livre_id, utilisateur_id, date_reservation, date_expiration, statut) 
VALUES 
(3, 1, CURRENT_TIMESTAMP, DATEADD('DAY', 7, CURRENT_TIMESTAMP), 'CONFIRMEE'),
(1, 2, CURRENT_TIMESTAMP, DATEADD('DAY', 5, CURRENT_TIMESTAMP), 'EN_ATTENTE');

-- Insertion des données
 
INSERT INTO prets (livre_id, utilisateur_id, date_pret, date_retour_prevue, version) 
VALUES 
(3, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + 14, 0), 
(4, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + 21, 0);