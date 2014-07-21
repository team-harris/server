CREATE DATABASE `cisco` /*!40100 DEFAULT CHARACTER SET utf8 */;

CREATE TABLE `devices` (
  `uuid` varchar(45) NOT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `users` (
  `username` varchar(10) NOT NULL,
  `name` varchar(45) NOT NULL,
  `businessGroup` varchar(45) NOT NULL,
  `device_uuid` varchar(45) DEFAULT NULL,
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `device_uuid_fk_idx` (`device_uuid`),
  CONSTRAINT `device_uuid_fk` FOREIGN KEY (`device_uuid`) REFERENCES `devices` (`uuid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
