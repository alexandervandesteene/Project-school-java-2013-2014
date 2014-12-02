-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Machine: localhost
-- Genereertijd: 19 dec 2013 om 17:37
-- Serverversie: 5.6.12-log
-- PHP-versie: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Databank: `projectjava`
--
CREATE DATABASE IF NOT EXISTS `projectjava` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `projectjava`;

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `competentie`
--

CREATE TABLE IF NOT EXISTS `competentie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(255) NOT NULL,
  `Omschrijving` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=9 ;

--
-- Gegevens worden uitgevoerd voor tabel `competentie`
--

INSERT INTO `competentie` (`id`, `naam`, `Omschrijving`) VALUES
(1, 'Gegevens Behandelen', 'Hij/zij kan gegevens verzamelen, opslaan en ter beschikking stellen, zodanig dat deze op een correcte en gebruiksvriendelijke manier kunnen worden opgevraagd.'),
(2, 'Analyseren', 'Hij/zij kan, zelfstandig of onder begeleiding en eventueel in teamverband, de informatiebehoeften van een organisatie gestructureerd en overzichtelijk weergeven.'),
(3, 'Oplossingen uitwerken', 'Hij/zij kan voor de organisatie nieuwe IT-oplossingen uitwerken, zodanig dat de opdrachtgever tevreden blijft. '),
(4, 'beheren', 'Hij/zij kan op een adequate wijze IT-oplossingen configureren, beveiligen en aanpassen, zodanig dat ze blijven beantwoorden aan de veranderende behoeften van de organisatie. '),
(5, 'projectmatig werken', 'Hij/zij is in staat om een opdracht op een projectmatige wijze aan te pakken, zodanig dat de planningen gerespecteerd worden. '),
(6, 'Communiceren', 'Hij/zij kan mondeling en schriftelijk communiceren met alle betrokkenen, zodanig dat hij/zij zijn/haar gesprekspartners correct begrijpt en zijn/haar boodschappen helder overkomen.  '),
(7, 'Zijn eigen gedrag aanpassen', 'Hij/zij is in staat om over het eigen gedrag te reflecteren en op basis daarvan het aan te passen zodanig dat hij/zij in wisselende omstandigheden optimaal kan functioneren'),
(8, 'Kwaliteitsvol handelen\r\n', 'Hij/zij streeft naar kwaliteitsvolle taakuitvoering zodanig dat het resultaat voldoet aan de eisen van een steeds wisselende economische en maatschappelijke omgeving.');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `deelcompetentie`
--

CREATE TABLE IF NOT EXISTS `deelcompetentie` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(255) NOT NULL,
  `comp_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comp` (`comp_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=39 ;

--
-- Gegevens worden uitgevoerd voor tabel `deelcompetentie`
--

INSERT INTO `deelcompetentie` (`id`, `naam`, `comp_id`) VALUES
(1, 'Gegevens verzamelen', 1),
(2, 'Gegevens Modelleren', 1),
(3, 'Verbanden leggen', 1),
(4, 'Gegevens opslaan', 1),
(5, 'Gegevens ter beschiking stellen', 1),
(6, 'de informatiebehoeften van een organisatie identificeren', 2),
(7, 'deze informatiebehoeften vertalen in concepten,schema''s en relaties', 2),
(8, 'De mogelijke oplossing documenteren', 2),
(9, 'De mogelijke oplossingen evalueren', 2),
(10, 'Testprocedures opstellen', 2),
(11, 'Programmeren(gestructureed, object georiënteerd, gedistribueerd)', 3),
(12, 'Implementeren', 3),
(13, 'Documenteren', 3),
(14, 'Testen(op het niveau van de deelaspecten en van de integratie)', 3),
(15, 'Systemen configureren,beveiligen en aanpassen', 4),
(16, 'Toepassingen configureren,beveiligen en aanpassen', 4),
(17, 'Databanken configureren,beveiligen en aanpassen', 4),
(18, 'Het project splitsen in taken en deeltaken', 5),
(19, 'Planningen opstellen(tijdsplanning, budgetplanning, middelenplanning)', 5),
(20, 'Een project opvolgen(tijd, budget,middelen)', 5),
(21, 'In een multidisciplinair en/of multicultureel team werken, als teamlid of als projectleider', 5),
(22, 'Mondeling en schriftelijk communiceren in het Nederlands en Frans en/of Engels', 6),
(23, 'De gepaste communicatietechnieken aanwenden', 6),
(24, 'Deelnemen aan vergaderingen als lid of als voorzitter', 6),
(25, 'Mensen opleiden en begeleiden', 6),
(26, 'Het eigen functioneren kritisch te evalueren en bij te sturen', 7),
(27, 'Maatschappelijk verantwoord te handelen (ethisch, deontologisch en gewetensvol)', 7),
(28, 'Creatief, vernieuwend en met zin voor initiatief te handelen', 7),
(29, 'Assertief te handelen rekening houdend met de belangen van alle betrokken partijen ', 7),
(30, 'Zijn/haar opdrachten uit te voeren met doorzettingsvermogen (verantwoordelijkheidszin en stressbestendigheid)', 7),
(31, 'Tijdig externe deskundigheid in te roepen', 7),
(32, 'Levenslang te leren', 7),
(33, 'Een kwaliteitsvolle redenering opbouwen, waarbij hij/zij rekening houdt met beroepsspecifieke inzichten en ervaringen ', 8),
(34, 'Performant en functioneel handelen', 8),
(35, 'Oplossingen leveren die gebruiksvriendelijk en flexibel (aanpasbaar, herbruikbaar en  onderhoudbaar) zijn', 8),
(36, 'Handelen conform de regelgeving', 8),
(37, 'Rrekening houden met de bedrijfseconomische context', 8),
(38, 'Een kwaliteitszorgsysteem toepassen', 8);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `indicatoren`
--

CREATE TABLE IF NOT EXISTS `indicatoren` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(255) CHARACTER SET utf8 NOT NULL,
  `deelcomp_id` int(11) DEFAULT NULL,
  `part_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_deelcomp_id` (`deelcomp_id`),
  KEY `part_id` (`part_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=16 ;

--
-- Gegevens worden uitgevoerd voor tabel `indicatoren`
--

INSERT INTO `indicatoren` (`id`, `naam`, `deelcomp_id`, `part_id`) VALUES
(1, 'kan programmeren', 16, 2),
(2, 'kan connectie met databank maken', 17, 3),
(3, 'kan een databank opstellen', 17, 6),
(4, 'kan een webserver installeren', 15, 8),
(5, 'kan een webserver beveiligen', 15, 9),
(6, 'kan object georienteerd programmeren', 11, 3),
(7, 'kan unit tests schrijven', 14, 2),
(8, 'kan een databank opstellen uit een ORM', 12, 6),
(9, 'kan een SQL-injectie voorkomen', 16, 9),
(10, 'gemiddeldes en de mediaan berekenen', 19, 4),
(11, 'kan facturen opstellen', 19, 5),
(12, 'kan informatie uit wetteksten halen', 6, 7),
(13, 'kan een DTD opstellen', 7, 6),
(14, 'kan een RSS feed aanmaken', 1, 1),
(15, 'kan observer gebruiken', 5, 2);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `opleidingen`
--

CREATE TABLE IF NOT EXISTS `opleidingen` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(255) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Gegevens worden uitgevoerd voor tabel `opleidingen`
--

INSERT INTO `opleidingen` (`id`, `naam`) VALUES
(1, 'Toegepaste informatica'),
(2, 'Toegepaste architectuur'),
(3, 'Multimedia en communicatie-technologie');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `opleidingsonderdeel`
--

CREATE TABLE IF NOT EXISTS `opleidingsonderdeel` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(255) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Gegevens worden uitgevoerd voor tabel `opleidingsonderdeel`
--

INSERT INTO `opleidingsonderdeel` (`id`, `naam`) VALUES
(1, 'ICT-management 1'),
(2, 'Softwareontwikkeling 3'),
(3, 'Webbeveiliging 2');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `partims`
--

CREATE TABLE IF NOT EXISTS `partims` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(255) CHARACTER SET utf8 NOT NULL,
  `opleidingsonderdeel` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fkOpleidingsonderdeel` (`opleidingsonderdeel`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Gegevens worden uitgevoerd voor tabel `partims`
--

INSERT INTO `partims` (`id`, `naam`, `opleidingsonderdeel`) VALUES
(1, 'c#', 2),
(2, 'java', 2),
(3, 'programmeerproject', 2),
(4, 'Statistiek en data mining', 1),
(5, 'ICT-georiënteerd bedrijfsbeleid', 1),
(6, 'Integratieproject', 1),
(7, 'Informaticarecht', 3),
(8, 'XML en webservices', 3),
(9, 'Honeypot', 3),
(10, 'Databanken SQL', NULL);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `partim_indicator`
--

CREATE TABLE IF NOT EXISTS `partim_indicator` (
  `part_id` int(11) NOT NULL,
  `ind_id` int(11) NOT NULL,
  PRIMARY KEY (`part_id`,`ind_id`),
  KEY `fk_ind2` (`ind_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Gegevens worden uitgevoerd voor tabel `partim_indicator`
--

INSERT INTO `partim_indicator` (`part_id`, `ind_id`) VALUES
(1, 1),
(2, 1),
(3, 1),
(6, 1),
(1, 2),
(6, 2),
(9, 2),
(9, 4),
(9, 5),
(1, 6),
(2, 6),
(3, 6),
(6, 6),
(1, 7),
(2, 7),
(3, 7),
(6, 7),
(9, 9),
(4, 10),
(5, 11),
(7, 12),
(8, 13),
(8, 14);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `naam` varchar(255) CHARACTER SET utf8 NOT NULL,
  `adress` varchar(255) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Gegevens worden uitgevoerd voor tabel `student`
--

INSERT INTO `student` (`id`, `naam`, `adress`) VALUES
(1, 'Maxim Vanhockerhout', 'Oostende'),
(2, 'Kevin De Coster', 'Oostende'),
(3, 'Steven Hiltrop', 'Oostende'),
(4, 'Alexander Van de Steene', 'Oostrozebeke');

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `student_indicator`
--

CREATE TABLE IF NOT EXISTS `student_indicator` (
  `stud_id` int(11) NOT NULL,
  `ind_id` int(11) NOT NULL,
  `score` int(2) DEFAULT NULL,
  PRIMARY KEY (`stud_id`,`ind_id`),
  KEY `ind_id` (`ind_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Gegevens worden uitgevoerd voor tabel `student_indicator`
--

INSERT INTO `student_indicator` (`stud_id`, `ind_id`, `score`) VALUES
(1, 1, 15),
(1, 2, 12),
(1, 4, NULL),
(1, 5, NULL),
(1, 6, 16),
(1, 7, 5),
(1, 9, NULL),
(1, 10, 14),
(1, 11, NULL),
(1, 12, NULL),
(1, 13, NULL),
(1, 14, NULL),
(2, 1, 16),
(2, 2, NULL),
(2, 4, NULL),
(2, 5, NULL),
(2, 6, NULL),
(2, 7, 15),
(2, 9, NULL),
(2, 10, NULL),
(2, 11, NULL),
(2, 12, NULL),
(2, 13, NULL),
(2, 14, NULL),
(3, 1, NULL),
(3, 2, NULL),
(3, 6, NULL),
(3, 7, NULL),
(3, 10, NULL),
(3, 11, NULL),
(4, 1, 11),
(4, 2, 15),
(4, 6, 5),
(4, 7, 12);

-- --------------------------------------------------------

--
-- Tabelstructuur voor tabel `student_partim`
--

CREATE TABLE IF NOT EXISTS `student_partim` (
  `stud_id` int(11) NOT NULL,
  `part_id` int(11) NOT NULL,
  PRIMARY KEY (`stud_id`,`part_id`),
  KEY `fk_part` (`part_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Gegevens worden uitgevoerd voor tabel `student_partim`
--

INSERT INTO `student_partim` (`stud_id`, `part_id`) VALUES
(1, 1),
(2, 1),
(3, 1),
(4, 1),
(1, 2),
(2, 2),
(3, 2),
(4, 2),
(1, 3),
(2, 3),
(3, 3),
(4, 3),
(1, 4),
(2, 4),
(3, 4),
(1, 5),
(2, 5),
(3, 5),
(1, 6),
(2, 6),
(3, 6),
(1, 7),
(2, 7),
(1, 8),
(2, 8),
(1, 9),
(2, 9);

--
-- Beperkingen voor gedumpte tabellen
--

--
-- Beperkingen voor tabel `deelcompetentie`
--
ALTER TABLE `deelcompetentie`
  ADD CONSTRAINT `fk_comp` FOREIGN KEY (`comp_id`) REFERENCES `competentie` (`id`);

--
-- Beperkingen voor tabel `indicatoren`
--
ALTER TABLE `indicatoren`
  ADD CONSTRAINT `fk_deelcomp_id` FOREIGN KEY (`deelcomp_id`) REFERENCES `deelcompetentie` (`id`),
  ADD CONSTRAINT `fk_partims_id` FOREIGN KEY (`part_id`) REFERENCES `partims` (`id`);

--
-- Beperkingen voor tabel `partims`
--
ALTER TABLE `partims`
  ADD CONSTRAINT `fkOpleidingsonderdeel` FOREIGN KEY (`opleidingsonderdeel`) REFERENCES `opleidingsonderdeel` (`id`);

--
-- Beperkingen voor tabel `partim_indicator`
--
ALTER TABLE `partim_indicator`
  ADD CONSTRAINT `fk_ind2` FOREIGN KEY (`ind_id`) REFERENCES `indicatoren` (`id`),
  ADD CONSTRAINT `fk_part2` FOREIGN KEY (`part_id`) REFERENCES `partims` (`id`);

--
-- Beperkingen voor tabel `student_indicator`
--
ALTER TABLE `student_indicator`
  ADD CONSTRAINT `student_indicator_ibfk_1` FOREIGN KEY (`stud_id`) REFERENCES `student` (`id`),
  ADD CONSTRAINT `student_indicator_ibfk_2` FOREIGN KEY (`ind_id`) REFERENCES `indicatoren` (`id`);

--
-- Beperkingen voor tabel `student_partim`
--
ALTER TABLE `student_partim`
  ADD CONSTRAINT `fk_part` FOREIGN KEY (`part_id`) REFERENCES `partims` (`id`),
  ADD CONSTRAINT `fk_stud2` FOREIGN KEY (`stud_id`) REFERENCES `student` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
