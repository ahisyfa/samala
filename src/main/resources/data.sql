-- BillType
INSERT INTO BILL_TYPE(name,period_type,nominal,active,created_time,updated_time) VALUES
('IPL','MONTHLY',20000,1,NOW(),NOW()),
('Infaq','FREE',20000,1,NOW(),NOW());


-- Education
INSERT INTO education(name) VALUES
('Tidak/Belum Sekolah'),
('TK'),
('SD'),
('SMP'),
('SMA/SMK'),
('D3'),
('S1'),
('S2'),
('S3');

-- FamilyRole
INSERT INTO family_role(name) VALUES
('Kepala Keluarga'),
('Istri'),
('Anak');

-- NotificationType
INSERT INTO notification_type(name) VALUES
('IPL'),
('Login');

-- PaymentMethod
INSERT INTO payment_method(name, created_time, updated_time) VALUES
('Cash', NOW(), NOW()),
('Transfer', NOW(), NOW());


-- Religion
INSERT INTO religion(name) VALUES
('Islam'),
('Protestan'),
('Katholik'),
('Hindu'),
('Budha');


-- ResidenceBlock
INSERT INTO residence_block(name, occupied_status) VALUES
('A-5',1),
('A-6', 1),
('A-7', 1),
('A-9', 1),
('A-15', 1),
('B-2', 1),
('B-3', 1),
('B-5', 1),
('B-9', 1),
('B-21', 1),
('C-1', 1),
('C-2', 1),
('C-3', 1),
('C-4', 1),
('C-5', 1),
('C-6', 1),
('C-7', 1),
('C-8', 1),
('C-9', 1),
('C-15', 1),
('D-3', 1),
('D-7', 1),
('D-10', 1),
('D-11', 1),
('D-12', 1),
('D-13', 1),
('D-18', 1),
('D-21', 1),
('E-1', 1),
('E-2', 1),
('F-2', 1),
('F-4', 1),
('G-1', 1),
('G-2', 1),
('G-3', 1),
('G-4', 1),
('G-5', 1),
('G-6', 1),
('G-7', 1),
('G-8', 1),
('G-9', 1),
('G-19', 1),
('G-21', 1),
('G-22', 1),
('H-6', 1),
('H-7', 1),
('H-9', 1),
('I-2', 1),
('I-4', 1),
('I-6', 1),
('I-7', 1),
('I-9', 1),
('I-10', 1),
('I-11', 1),
('I-12', 1),
('I-13', 1),
('I-15', 1),
('J-1', 1),
('J-2', 1),
('J-3', 1),
('J-4', 1),
('J-5', 1),
('J-6', 1),
('J-7', 1),
('J-8', 1),
('J-9', 1),
('J-10', 1),
('J-11', 1),
('J-12', 1),
('J-13', 1),
('J-14', 1),
('J-17', 1),
('J-18', 1),
('K-2', 1),
('K-3', 1),
('K-6', 1),
('K-7', 1),
('K-8', 1),
('K-10', 1),
('K-11', 1),
('L-11', 1),
('L-12', 1),
('M-2', 1),
('M-5', 1),
('M-6', 1),
('M-9', 1),
('M-13', 1),
('N-1', 1),
('N-2', 1),
('N-4', 1),
('N-5', 1),
('N-6', 1),
('N-8', 1),
('N-9', 1),
('N-10', 1),
('N-11', 1),
('O-2', 1),
('O-3', 1),
('O-4', 1),
('O-5', 1),
('O-6', 1),
('O-8', 1),
('O-9', 1),
('P-2', 1),
('P-3', 1),
('P-4', 1),
('P-7', 1),
('P-9', 1);


-- Person
INSERT INTO person(full_name, id_ktp, family_card_id, date_of_birth, place_of_birth, gender, marriage_status, nationality, family_role_id, last_education_id, religion_id, address, residence_block_id, phone_number, updated_time, created_time) VALUES
('SISI', '-', '-', '1993-06-01', 'Bogor', 'F', 1, 'WNI', 1, 7, 1, 'Casa Samala Blok A-5', 1, '089797916291', NOW(), NOW()),
('SAMPLE PERSON C-1', '-', '-', '1993-06-01', 'Bogor', 'F', 1, 'WNI', 1, 7, 1, 'Casa Samala Blok C-1', 11, '089797916291', NOW(), NOW());


-- ResidenceBlockOwner
INSERT INTO residence_block_owner(person_id, residence_block_id) VALUES
(1,1);

-- USER
INSERT INTO SAMALA_USERS(USERNAME,PASSWORD,ROLES,CREATED_TIME,UPDATED_TIME, PERSON_ID) VALUES
('admin','$2a$10$W1gMnvc9mdzWrPlvt7b70.YRjC0AAne22kQGvtjbr/IkFnVttvJve','ROLE_ADMIN',NOW(),NOW(),1),
('user','$2a$10$W1gMnvc9mdzWrPlvt7b70.YRjC0AAne22kQGvtjbr/IkFnVttvJve','user',NOW(),NOW(),2);


