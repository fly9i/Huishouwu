CREATE TABLE `collector` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `corpname` varchar(64) DEFAULT NULL,
  `corpsize` int DEFAULT NULL,
  `corpowner` varchar(64) DEFAULT NULL,
  `email` varchar(256) DEFAULT NULL,
  `corpphone` varchar(32) DEFAULT NULL,
  `addr` varchar(64) DEFAULT NULL,
  `corplicense` varchar(128) DEFAULT NULL,
  `corpshow` varchar(128) DEFAULT NULL,
  `showsite` varchar(512) DEFAULT NULL,
  `create_at` date DEFAULT NULL,
  `update_at` date DEFAULT NULL,
  `last_login` date DEFAULT NULL,
  `userid` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;