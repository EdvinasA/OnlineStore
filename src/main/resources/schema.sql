-- DROP TABLE IF EXISTS STORE_USERS, PRODUCT_QUANTITY, PRODUCT;
DROP TABLE IF EXISTS PURCHASE_ORDER_LINE;
-- DROP TABLE IF EXISTS CART;
-- DROP TABLE IF EXISTS PURCHASE_ORDER;
DROP TABLE IF EXISTS STORE_USERS;
--
-- CREATE TABLE CART (
--     ID serial PRIMARY KEY ,
--     QUANTITY integer DEFAULT NULL,
--     PRODUCT_ID integer DEFAULT NULL,
--     USER_ID integer DEFAULT NULL
-- );
--
-- CREATE TABLE PRODUCT (
--                       ID serial PRIMARY KEY ,
--                       IMAGE_URL varchar(255) DEFAULT NULL,
--                       TITLE varchar(255) DEFAULT NULL,
--                       DESCRIPTION varchar(255) DEFAULT NULL,
--                       PRICE integer DEFAULT NULL,
--                       TYPE varchar(255) DEFAULT NULL,
--                       CART_ID integer DEFAULT NULL,
--                       PURCHASE_ORDER_LINES_ID integer DEFAULT NULL,
--                       PRODUCT_QUANTITIES integer DEFAULT NULL
-- );
--
CREATE TABLE STORE_USERS (
                         ID serial PRIMARY KEY ,
                         FIRST_NAME varchar(255) DEFAULT NULL,
                         LAST_NAME varchar(255) DEFAULT NULL,
                         EMAIL varchar(255) DEFAULT NULL,
                         AGE integer DEFAULT NULL,
                         USER_NAME varchar(255) DEFAULT NULL,
                         PASSWORD varchar(255) DEFAULT NULL,
                         ROLE varchar(255) DEFAULT NULL,
                         PRODUCT_ORDER_ID integer DEFAULT NULL,
                         CART_ID integer DEFAULT NULL,
                         PRODUCT_ORDER_LINE_ID integer DEFAULT NULL
);
--
-- CREATE TABLE PRODUCT_QUANTITY (
--                          ID serial PRIMARY KEY ,
--                          QUANTITY integer DEFAULT NULL,
--                          DATE date DEFAULT NULL,
--                          PRODUCT_ID integer DEFAULT NULL
-- );
--
CREATE TABLE PURCHASE_ORDER_LINE (
                         ID serial PRIMARY KEY ,
                         QUANTITY integer DEFAULT NULL,
                         PURCHASE_ORDER_ID integer DEFAULT NULL,
                         PRODUCT_ID integer DEFAULT NULL,
                         STORE_USERS_ID integer DEFAULT NULL
);
--
-- CREATE TABLE PURCHASE_ORDER (
--                          ID serial PRIMARY KEY ,
--                          USER_NAME varchar(255) DEFAULT NULL,
--                          USER_SURNAME varchar(255) DEFAULT NULL,
--                          DELIVERY_ADDRESS varchar(255) DEFAULT NULL,
--                          ORDER_DATE date DEFAULT NULL,
--                          PURCHASE_ORDER_LINES_ID integer DEFAULT NULL,
--                          USER_ID integer DEFAULT NULL
-- );
