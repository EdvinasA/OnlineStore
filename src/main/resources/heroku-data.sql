PGDMP     !                     y           postgres    13.3    13.3 1    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    13442    postgres    DATABASE     l   CREATE DATABASE postgres WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_United States.1252';
    DROP DATABASE postgres;
                postgres    false            �           0    0    DATABASE postgres    COMMENT     N   COMMENT ON DATABASE postgres IS 'default administrative connection database';
                   postgres    false    3062                        3079    16384 	   adminpack 	   EXTENSION     A   CREATE EXTENSION IF NOT EXISTS adminpack WITH SCHEMA pg_catalog;
    DROP EXTENSION adminpack;
                   false            �           0    0    EXTENSION adminpack    COMMENT     M   COMMENT ON EXTENSION adminpack IS 'administrative functions for PostgreSQL';
                        false    2            �            1259    16685    cart    TABLE     y   CREATE TABLE public.cart (
    id integer NOT NULL,
    quantity integer,
    product_id integer,
    user_id integer
);
    DROP TABLE public.cart;
       public         heap    postgres    false            �            1259    16683    cart_id_seq    SEQUENCE     �   CREATE SEQUENCE public.cart_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.cart_id_seq;
       public          postgres    false    202            �           0    0    cart_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.cart_id_seq OWNED BY public.cart.id;
          public          postgres    false    201            �            1259    16693    product    TABLE     �  CREATE TABLE public.product (
    id integer NOT NULL,
    image_url character varying(255) DEFAULT NULL::character varying,
    title character varying(255) DEFAULT NULL::character varying,
    description character varying(255) DEFAULT NULL::character varying,
    price integer,
    type character varying(255) DEFAULT NULL::character varying,
    cart_id integer,
    purchase_order_lines_id integer,
    product_quantities integer
);
    DROP TABLE public.product;
       public         heap    postgres    false            �            1259    16691    product_id_seq    SEQUENCE     �   CREATE SEQUENCE public.product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.product_id_seq;
       public          postgres    false    204            �           0    0    product_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;
          public          postgres    false    203            �            1259    16725    product_quantity    TABLE        CREATE TABLE public.product_quantity (
    id integer NOT NULL,
    quantity integer,
    date date,
    product_id integer
);
 $   DROP TABLE public.product_quantity;
       public         heap    postgres    false            �            1259    16723    product_quantity_id_seq    SEQUENCE     �   CREATE SEQUENCE public.product_quantity_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public.product_quantity_id_seq;
       public          postgres    false    208            �           0    0    product_quantity_id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public.product_quantity_id_seq OWNED BY public.product_quantity.id;
          public          postgres    false    207            �            1259    16741    purchase_order    TABLE     k  CREATE TABLE public.purchase_order (
    id integer NOT NULL,
    user_name character varying(255) DEFAULT NULL::character varying,
    user_surname character varying(255) DEFAULT NULL::character varying,
    delivery_address character varying(255) DEFAULT NULL::character varying,
    order_date date,
    purchase_order_lines_id integer,
    user_id integer
);
 "   DROP TABLE public.purchase_order;
       public         heap    postgres    false            �            1259    16739    purchase_order_id_seq    SEQUENCE     �   CREATE SEQUENCE public.purchase_order_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.purchase_order_id_seq;
       public          postgres    false    212            �           0    0    purchase_order_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.purchase_order_id_seq OWNED BY public.purchase_order.id;
          public          postgres    false    211            �            1259    16733    purchase_order_line    TABLE     �   CREATE TABLE public.purchase_order_line (
    id integer NOT NULL,
    quantity integer,
    purchase_order_id integer,
    product_id integer
);
 '   DROP TABLE public.purchase_order_line;
       public         heap    postgres    false            �            1259    16731    purchase_order_line_id_seq    SEQUENCE     �   CREATE SEQUENCE public.purchase_order_line_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.purchase_order_line_id_seq;
       public          postgres    false    210            �           0    0    purchase_order_line_id_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.purchase_order_line_id_seq OWNED BY public.purchase_order_line.id;
          public          postgres    false    209            �            1259    16708    store_users    TABLE       CREATE TABLE public.store_users (
    id integer NOT NULL,
    first_name character varying(255) DEFAULT NULL::character varying,
    last_name character varying(255) DEFAULT NULL::character varying,
    email character varying(255) DEFAULT NULL::character varying,
    age integer,
    user_name character varying(255) DEFAULT NULL::character varying,
    password character varying(255) DEFAULT NULL::character varying,
    role character varying(255) DEFAULT NULL::character varying,
    product_order_id integer,
    cart_id integer
);
    DROP TABLE public.store_users;
       public         heap    postgres    false            �            1259    16706    store_users_id_seq    SEQUENCE     �   CREATE SEQUENCE public.store_users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 3
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 )   DROP SEQUENCE public.store_users_id_seq;
       public          postgres    false    206            �           0    0    store_users_id_seq    SEQUENCE OWNED BY     I   ALTER SEQUENCE public.store_users_id_seq OWNED BY public.store_users.id;
          public          postgres    false    205            D           2604    16688    cart id    DEFAULT     b   ALTER TABLE ONLY public.cart ALTER COLUMN id SET DEFAULT nextval('public.cart_id_seq'::regclass);
 6   ALTER TABLE public.cart ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    202    201    202            E           2604    16696 
   product id    DEFAULT     h   ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);
 9   ALTER TABLE public.product ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    204    203    204            Q           2604    16728    product_quantity id    DEFAULT     z   ALTER TABLE ONLY public.product_quantity ALTER COLUMN id SET DEFAULT nextval('public.product_quantity_id_seq'::regclass);
 B   ALTER TABLE public.product_quantity ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    208    207    208            S           2604    16744    purchase_order id    DEFAULT     v   ALTER TABLE ONLY public.purchase_order ALTER COLUMN id SET DEFAULT nextval('public.purchase_order_id_seq'::regclass);
 @   ALTER TABLE public.purchase_order ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    211    212    212            R           2604    16736    purchase_order_line id    DEFAULT     �   ALTER TABLE ONLY public.purchase_order_line ALTER COLUMN id SET DEFAULT nextval('public.purchase_order_line_id_seq'::regclass);
 E   ALTER TABLE public.purchase_order_line ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    209    210    210            J           2604    16711    store_users id    DEFAULT     p   ALTER TABLE ONLY public.store_users ALTER COLUMN id SET DEFAULT nextval('public.store_users_id_seq'::regclass);
 =   ALTER TABLE public.store_users ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    205    206    206            �          0    16685    cart 
   TABLE DATA           A   COPY public.cart (id, quantity, product_id, user_id) FROM stdin;
    public          postgres    false    202   8       �          0    16693    product 
   TABLE DATA           �   COPY public.product (id, image_url, title, description, price, type, cart_id, purchase_order_lines_id, product_quantities) FROM stdin;
    public          postgres    false    204   18       �          0    16725    product_quantity 
   TABLE DATA           J   COPY public.product_quantity (id, quantity, date, product_id) FROM stdin;
    public          postgres    false    208   e>       �          0    16741    purchase_order 
   TABLE DATA           �   COPY public.purchase_order (id, user_name, user_surname, delivery_address, order_date, purchase_order_lines_id, user_id) FROM stdin;
    public          postgres    false    212   �>       �          0    16733    purchase_order_line 
   TABLE DATA           Z   COPY public.purchase_order_line (id, quantity, purchase_order_id, product_id) FROM stdin;
    public          postgres    false    210   ?       �          0    16708    store_users 
   TABLE DATA           �   COPY public.store_users (id, first_name, last_name, email, age, user_name, password, role, product_order_id, cart_id) FROM stdin;
    public          postgres    false    206   f?       �           0    0    cart_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.cart_id_seq', 6, true);
          public          postgres    false    201                        0    0    product_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.product_id_seq', 31, true);
          public          postgres    false    203                       0    0    product_quantity_id_seq    SEQUENCE SET     F   SELECT pg_catalog.setval('public.product_quantity_id_seq', 1, false);
          public          postgres    false    207                       0    0    purchase_order_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.purchase_order_id_seq', 1, false);
          public          postgres    false    211                       0    0    purchase_order_line_id_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.purchase_order_line_id_seq', 1, false);
          public          postgres    false    209                       0    0    store_users_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.store_users_id_seq', 3, true);
          public          postgres    false    205            X           2606    16690    cart cart_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.cart
    ADD CONSTRAINT cart_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.cart DROP CONSTRAINT cart_pkey;
       public            postgres    false    202            Z           2606    16705    product product_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.product DROP CONSTRAINT product_pkey;
       public            postgres    false    204            ^           2606    16730 &   product_quantity product_quantity_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.product_quantity
    ADD CONSTRAINT product_quantity_pkey PRIMARY KEY (id);
 P   ALTER TABLE ONLY public.product_quantity DROP CONSTRAINT product_quantity_pkey;
       public            postgres    false    208            `           2606    16738 ,   purchase_order_line purchase_order_line_pkey 
   CONSTRAINT     j   ALTER TABLE ONLY public.purchase_order_line
    ADD CONSTRAINT purchase_order_line_pkey PRIMARY KEY (id);
 V   ALTER TABLE ONLY public.purchase_order_line DROP CONSTRAINT purchase_order_line_pkey;
       public            postgres    false    210            b           2606    16752 "   purchase_order purchase_order_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.purchase_order
    ADD CONSTRAINT purchase_order_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.purchase_order DROP CONSTRAINT purchase_order_pkey;
       public            postgres    false    212            \           2606    16722    store_users store_users_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.store_users
    ADD CONSTRAINT store_users_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.store_users DROP CONSTRAINT store_users_pkey;
       public            postgres    false    206            �      x�3�4�4�4�2�4�4�1z\\\ ��      �   $  x��V�n�F<�~E#@ !Eΐ�����[#ʱ�2�{b>���UA���Cjm9&o	`PawUWW�%�,�4d*���8d�H����{2��6!P��p� �Fg1�M4 �� ��lwyR���H�L֣��!���rެ�+�ˢ����Y���y� 9�'��������?�m	���O%P�p$��jǰj�~y(��8��#��X����U���j�,��d:���x�a^� �N����ǂ�g����sB}�����îy\�Y���u|/�8��,��2s���V��8l�4�gȄ�6Dގ��	u���Ls��`�#�^�ē����z�q̵'�#�ن�#;��۸@�m��F�CX�d�𩫻4�}gp��,�I�B�I�u~�>h�H_�}Ϝ^i��$:�msۉ����x+KԀX�S��ir���u,�P��ڋzMw�}�\l��To���C����,k���8su����P"������m��N¯ILqOm}�����' �v�.�R��D��5��Ԟ�k�R��w�%��v`!˱a�m`����O�e�U���1b\g�0�ǿ��m0��%>����@�g�1��>�0/*����B�By�?�ē�q�����*���wj�&EG�l�ܐ�C��=�i1D��{Qɻ$Vc�/��򼗎�M�]��)��S��\�lWn�>6�B�J�>WC�{���'-_�Y�m�g��1a/�8�р�&lwF�ן@�ͽZ�G��\���Q�Ǉm
̅m��.5�������H�3a���jZo�bJ�Ҕ���p��O ���TGR�l�K�)vl��p3!��FSc7��H�j�3�35=��V[A�4�[�;�6���"O%��LVy!q� t�Ohh %�"�si(P磯�S+���i2�2��BG0�&#������#����p����!�SOɲ�Lix(�����A4#��'����֨W��Dl����K��e� �/!�D�i����4��}�y ��\8f�#�����;Y�n�����Di�D�Ç"Oc�ZS�ESt�a's�8�[]�屬�/������MPG�yt���E��*|����F��7��m�5�mj���4?�:�j݁���
e>�ܺ��M�7&��۟s�^�H�
���3k��CH����QύX<⹪V�	�Z6�u����ܑ��9Q�}�
=UVx��q�QW���Dla��-6��d`_1�b0�l<�#�P���z:^Egx]7K��7,[)2\{�M"�����E��G����8઼/ߑ����EUȼ�H�g�:1	��Ɠ�����:u.ё����[���Z�ľ��!���qodʒ�;Y�9�P��-���zG�&��M]��0<~5
C�_@���ƽ��ƭ�;"�5)��,v_��k��/�s.&������^�L���`u�k�T1��-�h�\�HTb]��8A�F�6fS�N7���#F�[��L`o��T�LQ>���O1DY�	�;Y�{�Š�?e�
�^������Pw�H!��,{r����>|���7      �   M   x�]λ�0����ŁN�w��?GR��UT��K����D�$eR��4�,��6���5�(|1�܄|\�|]�|^��>'�*S      �   L   x�3����K,��%�Ŝ���yG7+��)��(x'�����u�t�8c�8���H�fh �fL�6c��=... �0      �   8   x���  ���aLA�q�9s���N�&+�,:�M�P?A~O�B^����3      �   @   x�3�LL���C&�s3s���s9MMQ�]|=�8c@�ˈ��8�A��Bȅ�A���qqq ��"<     