insert into PRODUCT (ID, TITLE, DESCRIPTION, PRICE, product_Type)
VALUES (1, 'Dell XP 15266', 'Computer', 966.66, 1),
       (2, 'Slipers', 'cosy and warm', 20.00, 2),
       (3, 'Slipers', 'cosy and warm', 20.00, 2),
       (4, 'Slipers', 'cosy and warm', 20.00, 2),
       (5, 'Slipers', 'cosy and warm', 20.00, 2),
       (6, 'Slipers', 'cosy and warm', 20.00, 2),
       (7, 'Slipers', 'cosy and warm', 20.00, 2),
       (8, 'Slipers', 'cosy and warm', 20.00, 2),
       (9, 'Slipers', 'cosy and warm', 20.00, 2);


insert into product_quantity (ID, PRODUCT_ID, QUANTITY, DATE)
values (1, 1, 20.00, '2021-05-31');

insert into cart (ID, PRODUCT_ID, QUANTITY)
values (1, 1, 3);
