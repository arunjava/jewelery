--USER ROLES
INSERT INTO textiles.user_role(
	role_id_n, desc_v, hierarchy_n, created_by, created_on, modified_by, modified_on, role_v)
	VALUES (1, 'ADMIN Role', 999, 'System', NOW(), 'System', NOW(), 'ADMIN');
INSERT INTO textiles.user_role(
	role_id_n, desc_v, hierarchy_n, created_by, created_on, modified_by, modified_on, role_v)
	VALUES (2, 'Guest Role', 1, 'System', NOW(), 'System', NOW(), 'GUEST');
INSERT INTO textiles.user_role(
	role_id_n, desc_v, hierarchy_n, created_by, created_on, modified_by, modified_on, role_v)
	VALUES (3, 'Basic Role', 2, 'System', NOW(), 'System', NOW(), 'BASIC');

