DROP DATABASE IF EXISTS annexe;
CREATE DATABASE annexe;
USE annexe;

DROP TABLE IF exists personne;
CREATE TABLE IF NOT EXISTS personne (
    idpers int(3) NOT NULL AUTO_INCREMENT,
    nom varchar(50),
    prenom varchar(50),
    PRIMARY KEY (idpers)
);

DROP TABLE IF exists user;
CREATE TABLE IF NOT EXISTS user (
    iduser int(3) NOT NULL AUTO_INCREMENT,
    nom varchar(50) NOT NULL,
    prenom varchar(50) NOT NULL,
    email varchar(50) NOT NULL,
    mdp varchar(255) NOT NULL,
    role enum("admin","user"),
    PRIMARY KEY (iduser)
);

DROP TABLE IF exists usager;
CREATE TABLE IF NOT EXISTS usager  (
    idpers int(11) NOT NULL,
    nom varchar(30) NOT NULL,
    prenom varchar(30) NOT NULL,
    dateNaiss DATE NOT NULL,
    adresse varchar(100) NOT NULL,
    tel varchar(12) NOT NULL,
    classe varchar(50) NOT NULL,
    ville varchar(50) NOT NULL,
    cp varchar(5) NOT NULL,
    sex enum("Feminin", "Masculin"),
    pays varchar(50) NOT NULL,
    etat enum("En attente","Valide","Refusee"),
    PRIMARY KEY (idpers),
    FOREIGN KEY (idpers) REFERENCES personne (idpers)
    ON UPDATE CASCADE
    ON DELETE CASCADE
);



DROP TABLE IF exists carte;
CREATE TABLE IF NOT EXISTS  carte (
idCarte int(11) NOT NULL AUTO_INCREMENT,
idUtilisateur int(11)NOT NULL,
numero_carte int(5),
duree_validitee DATE,
idUsager int(11) NOT NULL,
PRIMARY KEY (idCarte),
FOREIGN KEY (idUsager) REFERENCES usager (idUsager)
ON UPDATE CASCADE
ON DELETE CASCADE
);

DROP TABLE IF exists reservation;
CREATE TABLE IF NOT EXISTS reservation (
idReservation int(11) NOT NULL AUTO_INCREMENT,
dateHeureDeboccup DATE NOT NULL,
heure_deb_occup TIME NOT NULL,
nbPersonne int(11) NOT NULL,
heure_fin_occup TIME NOT NULL,
etat enum("Reservez","Pas reserver") NOT NULL DEFAULT 'Pas reserver',
PRIMARY KEY (idReservation)
);

DROP TABLE IF exists salle;
CREATE TABLE IF NOT EXISTS salle (
idSalle int(11) NOT NULL AUTO_INCREMENT,
libelle varchar(100) NOT NULL,
capacite int(12) NOT NULL,
etat enum("Disponible","Occupee")NOT NULL,
PRIMARY KEY (idSalle)
);

DROP TABLE IF exists avis;
CREATE TABLE IF NOT EXISTS avis (
idAvis int(11) NOT NULL AUTO_INCREMENT,
commentaire text NOT NULL,
idUsager int(11) NOT NULL,
idSalle int(11) NOT NULL,
PRIMARY KEY (idAvis),
FOREIGN KEY (idUsager) REFERENCES usager (idUsager)
ON UPDATE CASCADE
ON DELETE CASCADE,
FOREIGN KEY (idSalle) REFERENCES salle (idSalle)
ON UPDATE CASCADE
ON DELETE CASCADE
);

DROP TABLE IF exists box;
CREATE TABLE IF NOT EXISTS box (
idSalle int(11) NOT NULL AUTO_INCREMENT,
libelle varchar(100) NOT NULL,
capacite int(12) NOT NULL,
etat enum("Disponible","Occupee")NOT NULL,
PRIMARY KEY (idSalle),
FOREIGN KEY(idSalle) REFERENCES salle (idSalle)
);

DROP TABLE IF exists salle_groupe;
CREATE TABLE IF NOT EXISTS salle_groupe (
idSalle int(11) NOT NULL AUTO_INCREMENT,
libelle varchar(100) NOT NULL,
capacite int(12) NOT NULL,
etat enum("Disponible","Occupee")NOT NULL,
PRIMARY KEY (idSalle),
FOREIGN KEY(idSalle) REFERENCES salle (idSalle)
ON UPDATE CASCADE
ON DELETE CASCADE
);

DROP TABLE IF exists salle_commune;
CREATE TABLE IF NOT EXISTS salle_commune (
idSalle int(11) NOT NULL AUTO_INCREMENT,
libelle varchar(100) NOT NULL,
capacite int(12) NOT NULL,
etat enum("Disponible","Occupee")NOT NULL,
PRIMARY KEY (idSalle),
FOREIGN KEY(idSalle) REFERENCES salle (idSalle)
ON UPDATE CASCADE
ON DELETE CASCADE
);

Insert into user values
(null, "Desert", "Anais", "a@gmail.com", "123", "admin"),
(null, "Diene", "Abass", "b@gmail.com", "456", "user");


create view TB as (
select p.nom, p.prenom, s.libelle as salle, numero_carte as carte, v.commentaire as avis 
from personne p, salle s, carte c, avis v 
where p.idpers = c.idusager 
and s.idsalle = v.idsalle
and p.idpers = v.idusager);

CREATE VIEW tableau AS(
SELECT
    r.idReservation,
    u.nom AS usager_nom,
    u.prenom AS usager_prenom,
    s.libelle AS salle_libelle,
    r.dateHeureDeboccup AS date_occup,
    r.heure_deb_occup AS heure_deb_occup,
    r.heure_fin_occup AS heure_fin_occup,
    r.etat AS reservation_etat
FROM
    reservation r
    JOIN usager u ON r.idUsager = u.idpers
    JOIN salle s ON r.idSalle = s.idSalle);

----------------------------------------------------------------------------------------------

DROP TRIGGER IF EXISTS insertpersonne;
CREATE TRIGGER insertpersonne
BEFORE INSERT ON usager
FOR EACH ROW
BEGIN
    INSERT INTO personne (nom, prenom)
    VALUES (
        NEW.nom,
        NEW.prenom
    );

    SET NEW.idpers = LAST_INSERT_ID();
END;

//

DELIMITER ;


INSERT INTO usager (nom, prenom, dateNaiss, adresse, tel, classe, ville, cp, sex, pays, etat) 
VALUES ('Dupont', 'Jean', '1990-12-25', '123 Rue des Exemples', '0612345678', 'Classe 1', 'Paris', '75001', 'Masculin', 'France', 'Valide');
------------------------------------------------------------------------------------------------------------------------------------------------










