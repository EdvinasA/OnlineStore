insert into PRODUCT (ID, TITLE, DESCRIPTION, PRICE, TYPE)
VALUES (1, 'Dell XP 15266', 'Computer', 966.66, 'COMPUTER'),
       (2, 'Slipers', 'cosy and warm', 20.00, 'OTHERS'),
       (3, 'Slipers', 'cosy and warm', 20.00, 'OTHERS'),
       (4, 'Slipers', 'cosy and warm', 20.00, 'OTHERS'),
       (5, 'Slipers', 'cosy and warm', 20.00, 'OTHERS'),
       (6, 'Slipers', 'cosy and warm', 20.00, 'OTHERS'),
       (7, 'Slipers', 'cosy and warm', 20.00, 'OTHERS'),
       (8, 'Slipers', 'cosy and warm', 20.00, 'OTHERS'),
       (9, 'Slipers', 'cosy and warm', 20.00, 'OTHERS');


insert into product_quantity (ID, PRODUCT_ID, QUANTITY, DATE)
values (1, 1, 20.00, '2021-05-31');

insert into cart (ID, PRODUCT_ID, QUANTITY)
values (1, 1, 3),
       (2, 2, 5);

insert into purchase_order (ID, USER_NAME, USER_SURNAME, DELIVERY_ADDRESS, ORDER_DATE)
values (1, 'Jonas', 'Jonaitis', 'Aguonų g. 15, Kaunas', '2021-06-02'),
       (2, 'Jonas', 'Jonaitis', 'Aguonų g. 15, Kaunas', '2021-06-10'),
       (3, 'Jonas', 'Jonaitis', 'Aguonų g. 15, Kaunas', '2021-06-13');

insert into purchase_order_line(ID, PURCHASE_ORDER_ID, PRODUCT_ID, QUANTITY)
values (1, 1, 1, 1.0),
       (2, 1, 2, 2.0),
       (3, 1, 3, 6.0),
       (4, 2, 4, 1.0),
       (5, 2, 5, 2.0),
       (6, 2, 6, 1.0),
       (7, 3, 1, 5.0),
       (8, 3, 6, 1.0);

