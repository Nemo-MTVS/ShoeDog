CREATE TABLE `stock` (
  `shoe_id` integer PRIMARY KEY AUTO_INCREMENT,
  `model_id` varchar(255) NOT NULL,
  `color` integer NOT NULL,
  `size` integer NOT NULL,
  `stock` integer NOT NULL DEFAULT 0
);

CREATE TABLE `model` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `modelname` varchar(255) NOT NULL,
  `brandname` varchar(255) UNIQUE NOT NULL,
  `listprice` integer NOT NULL,
  `description` varchar(255) NOT NULL
);

CREATE TABLE `color` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `color` varchar(255) UNIQUE NOT NULL
);

CREATE TABLE `sizes` (
  `id` integer PRIMARY KEY AUTO_INCREMENT,
  `size` integer UNIQUE NOT NULL
);

ALTER TABLE `stock` ADD FOREIGN KEY (`model_id`) REFERENCES `model` (`id`);

ALTER TABLE `stock` ADD FOREIGN KEY (`size`) REFERENCES `sizes` (`id`);

ALTER TABLE `stock` ADD FOREIGN KEY (`color`) REFERENCES `color` (`id`);
