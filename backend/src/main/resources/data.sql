--USER ROLES
INSERT INTO textiles.user_role(role_id_n, desc_v, hierarchy_n, created_by, created_on, modified_by, modified_on, role_v) VALUES (1, 'ADMIN Role', 999, 'System', NOW(), 'System', NOW(), 'ADMIN');
INSERT INTO textiles.user_role(role_id_n, desc_v, hierarchy_n, created_by, created_on, modified_by, modified_on, role_v) VALUES (2, 'Guest Role', 1, 'System', NOW(), 'System', NOW(), 'GUEST');
INSERT INTO textiles.user_role(role_id_n, desc_v, hierarchy_n, created_by, created_on, modified_by, modified_on, role_v) VALUES (3, 'Basic Role', 2, 'System', NOW(), 'System', NOW(), 'BASIC');

--Country
INSERT INTO jewel.ms_country(country_id, country_code, country_name) VALUES (1, 'IN','India' );

--State
INSERT INTO JEWEL.MS_STATE(STATE_ID, STATE_CODE, STATE_NAME, COUNTRY_ID) VALUES (1, 'TN', 'TAMIL NADU', 1);
INSERT INTO JEWEL.MS_STATE(STATE_ID, STATE_CODE, STATE_NAME, COUNTRY_ID) VALUES (2, 'KA', 'KARNATAKA', 1);


--District
INSERT INTO jewel.ms_district(district_id, district_code, district_name, state_id) VALUES (100, 'SL' , 'SALEM', 1);
INSERT INTO jewel.ms_district(district_id, district_code, district_name, state_id) VALUES (101, 'NKL' , 'NAMAKKAL', 1);


--Sub District
INSERT INTO jewel.ms_sub_district(sub_district_id, sub_district_code, sub_district_name, district_id) VALUES (100, '', 'AMMAPET', 100);