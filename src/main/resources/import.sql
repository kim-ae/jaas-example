INSERT INTO system_role (code) values ('ADMIN'), ('EXECUTIVE');

INSERT INTO system_user (login, password) values ('admin', 'pass123'), ('executive', 'pass123');

--Atribuir roles para cada um dos usuários.
INSERT INTO user_role_relation(user_id, role_id)
SELECT id as user_id, role_id FROM system_user CROSS JOIN( 
    SELECT id as role_id FROM system_role WHERE code = 'ADMIN') as a 
WHERE login = 'admin' 
UNION 
SELECT id as user_id, role_id FROM system_user CROSS JOIN( 
    SELECT id as role_id FROM system_role WHERE code = 'EXECUTIVE') as a 
WHERE login = 'executive';