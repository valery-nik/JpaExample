USE eldo;

DROP TABLE IF EXISTS attribute;
CREATE TABLE attribute
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(255)
);

DROP TABLE IF EXISTS attribute_value;
CREATE TABLE attribute_value
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    attribute_id BIGINT(20),
    facet_value_id BIGINT(20),
    value VARCHAR(255)
);

DROP TABLE IF EXISTS facet_value;
CREATE TABLE facet_value
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    tag_id BIGINT(20)
);

DROP TABLE IF EXISTS ranged_attribute_value;
CREATE TABLE ranged_attribute_value
(
    id BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    max_value FLOAT,
    min_value FLOAT,
    attribute_id BIGINT(20),
    facet_value_id BIGINT(20)
);

DROP TABLE IF EXISTS tag;
CREATE TABLE tag
(
    ID BIGINT(20) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    NAME VARCHAR(255),
    ACTIVE BIT(1),
    CATEGORY_ID BIGINT(20)
    --  PRODUCT_IDS TEXT
);