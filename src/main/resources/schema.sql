CREATE TABLE `user`
(
  id INTEGER NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  age integer NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE `city` (
  `id` INTEGER NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `postal` varchar(10) NOT NULL,
  `country` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
);