CREATE TABLE IF NOT EXISTS candy (
  id int NOT NULL AUTO_INCREMENT,
  name VARCHAR(255),
  price DECIMAL(12,4),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS candy_delivery (
 id bigint NOT NULL AUTO_INCREMENT,
 candy_id bigint NOT NULL,
 delivery_id bigint NOT NULL,
 PRIMARY KEY (id),
 FOREIGN KEY (candy_id) REFERENCES candy(id)
 FOREIGN KEY (delivery_id) REFERENCES delivery(id) ON DELETE CASCADE
);

