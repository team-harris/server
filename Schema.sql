CREATE TABLE `users` (
  `username` varchar(10) NOT NULL,
  `name` varchar(45) NOT NULL,
  `businessGroup` varchar(45) NOT NULL,
  `device_uuid` varchar(45) NOT NULL,
  UNIQUE KEY `username_UNIQUE` (`username`),
  KEY `device_uuid_fk_idx` (`device_uuid`),
  CONSTRAINT `device_uuid_fk` FOREIGN KEY (`device_uuid`) REFERENCES `devices` (`uuid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `devices` (
  `uuid` varchar(45) NOT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `power` (
  `watts` int(11) NOT NULL,
  `datetime` datetime NOT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`watts`,`datetime`,`username`),
  KEY `username_fk_idx` (`username`),
  CONSTRAINT `username_fk` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;