--USER ROLES
INSERT INTO jewel.user_role(role_id_n, desc_v, hierarchy_n, created_by, created_on, modified_by, modified_on, role_v) VALUES (1, 'ADMIN Role', 999, 'System', NOW(), 'System', NOW(), 'ADMIN');
INSERT INTO jewel.user_role(role_id_n, desc_v, hierarchy_n, created_by, created_on, modified_by, modified_on, role_v) VALUES (2, 'Guest Role', 1, 'System', NOW(), 'System', NOW(), 'GUEST');
INSERT INTO jewel.user_role(role_id_n, desc_v, hierarchy_n, created_by, created_on, modified_by, modified_on, role_v) VALUES (3, 'Basic Role', 2, 'System', NOW(), 'System', NOW(), 'BASIC');

--Country
INSERT INTO jewel.ms_country(country_id, country_code, country_name) VALUES (1, 'IN','India' );

--State
INSERT INTO JEWEL.MS_STATE(STATE_ID, STATE_CODE, STATE_NAME, COUNTRY_ID) VALUES (1, 'TN', 'TAMIL NADU', 1);
--INSERT INTO JEWEL.MS_STATE(STATE_ID, STATE_CODE, STATE_NAME, COUNTRY_ID) VALUES (2, 'KA', 'KARNATAKA', 1);


--District
INSERT INTO jewel.ms_district(district_id, district_code, district_name, state_id) VALUES (100, 'SL' , 'SALEM', 1);
INSERT INTO jewel.ms_district(district_id, district_code, district_name, state_id) VALUES (101, 'NKL' , 'NAMAKKAL', 1);
INSERT INTO jewel.ms_district(district_id, district_code, district_name, state_id) VALUES (102, 'ATR' , 'ATTUR', 1);


--Sub District
INSERT INTO jewel.ms_sub_district(sub_district_id, sub_district_code, sub_district_name, district_id) VALUES (100, '', 'AMMAPET', 100);



--UOM Cateory
insert into jewel.ms_uom_category(uom_cat_id , uom_category) values (1, 'Weight');
insert into jewel.ms_uom_category(uom_cat_id , uom_category) values (2, 'Quantity');
insert into jewel.ms_uom_category(uom_cat_id , uom_category) values (3, 'Volume');
insert into jewel.ms_uom_category(uom_cat_id , uom_category) values (4, 'Time');

--Weight related UOM
INSERT INTO jewel.ms_uom(uom_id, uom_code, uom_desc, hierarchy, is_base_uom, created_by, created_on, modified_by, modified_on, uom_cat_id) VALUES (101, 'Milli Gram', 'MG', 0, FALSE, NULL, NULL, NULL, NULL, 1);
INSERT INTO jewel.ms_uom(uom_id, uom_code, uom_desc, hierarchy, is_base_uom, created_by, created_on, modified_by, modified_on, uom_cat_id) VALUES (102, 'Gram', 'GM', 0, FALSE, NULL, NULL, NULL, NULL, 1);
INSERT INTO jewel.ms_uom(uom_id, uom_code, uom_desc, hierarchy, is_base_uom, created_by, created_on, modified_by, modified_on, uom_cat_id) VALUES (103, 'Killo Gram', 'KG', 0, FALSE, NULL, NULL, NULL, NULL, 1);


--time related UOM
INSERT INTO jewel.ms_uom(uom_id, uom_code, uom_desc, hierarchy, is_base_uom, created_by, created_on, modified_by, modified_on, uom_cat_id) VALUES (401, 'DAYS', 'DAYS', 0, FALSE, NULL, NULL, NULL, NULL, 4);
INSERT INTO jewel.ms_uom(uom_id, uom_code, uom_desc, hierarchy, is_base_uom, created_by, created_on, modified_by, modified_on, uom_cat_id) VALUES (402, 'MONTHS', 'MONTHS', 0, FALSE, NULL, NULL, NULL, NULL, 4);
INSERT INTO jewel.ms_uom(uom_id, uom_code, uom_desc, hierarchy, is_base_uom, created_by, created_on, modified_by, modified_on, uom_cat_id) VALUES (403, 'YEARS', 'YEARS', 0, FALSE, NULL, NULL, NULL, NULL, 4);


--Product 
insert into jewel.ms_product_category (category_id, category_name,  description, category_status) values (1, 'Gold', 'Gold' , true);
insert into jewel.ms_product_category (category_id, category_name,  description, category_status) values (2, 'Silver', 'Silver',true);
insert into jewel.ms_product_category (category_id, category_name, description, category_status) values (3, 'Diamond', 'Diamond Ornaments', true);

