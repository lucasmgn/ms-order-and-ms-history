CREATE TABLE ADDRESS(
 ID BIGINT PRIMARY KEY AUTO_INCREMENT,
 STREET VARCHAR(125) NOT NULL,
 NUMBER VARCHAR(12),
 NEIGHBORHOOD VARCHAR(125),
 CITY VARCHAR(125),
 STATE VARCHAR(125),
 CEP VARCHAR(125)
)engine=InnoDB;