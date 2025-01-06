-- Address for Doctor
insert into address (id, address_line1, address_line2, city, postal_code)
values (1, 'Street 1', 'Building A', 'Warsaw', '62-030');

-- Address for Patient
insert into address (id, address_line1, address_line2, city, postal_code)
values (2, 'Street 2', 'Apartment 5', 'Krakow', '30-001');

-- Additional Addresses
 insert into address (id, address_line1, address_line2, city, postal_code)
        values
            (3, 'DDD', 'CCC', 'lubon', '62-030'),
            (4, 'EEE', 'FFF', 'wroclaw', '54-153'),
            (5, 'GGG', 'HHH', 'lubon', '62-030'),
            (6, 'xx', 'yy', 'lubon', '62-030'),
            (7, 'aaa', 'bbb', 'wroclaw', '54-153'),
            (8, 'sss', 'CCC', 'lubon', '62-030'),
            (9, 'BBB', 'FFF', 'wroclaw', '54-153'),
            (10, 'GLK', 'ASD', 'lubon', '62-030');

-- Doctors
insert into doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
values (1, 'John', 'Doe', '123456789', 'johndoe@example.com', 'DOC001', 'SURGEON', 1);
-- Specializacja to ENUM ;)
insert into doctor (id, first_name, last_name, email, telephone_number, address_id, doctor_number, specialization)
            values (2, 'Jane', 'Evans ', '2@2.2', '987654321', 3, 'DOC002', 'GP');

-- Patients
insert into patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id, is_insured)
values (1, 'Jane', 'Smith', '987654321', 'janesmith@example.com', 'PAT001', '1990-05-15', 2, true);

insert into patient (id, first_name, last_name, email, telephone_number, address_id, patient_number, date_of_birth, is_insured)
        values
             (2, 'Jane', 'Doe', 'a@a.a', '987654321', 4, 'PAT002', '1996-01-01', FALSE),
             (3, 'Tom', 'Williams', 'b@b.b', '123123123', 5, 'PAT003', '1981-01-01', FALSE),
             (4, 'Jerry', 'Doe', 'c@c.c', '321321321', 6, 'PAT004', '1999-02-01', TRUE),
             (5,'John', 'Doe', 'test@test.test', '123456789', 7, 'PAT005', '2000-01-05', TRUE);

-- Visit
insert into visit (id, description, time, doctor_id, patient_id)
values (1, 'Regular checkup', '2024-11-23 10:00:00', 1, 1);

insert into visit (id, description, time, doctor_id, patient_id)
values
     (2, 'Sudden illness', '2024-11-28 16:00:00', 2, 1),
     (3, 'Infarct', '2024-12-03 17:00:00', 1, 3),
    (4, 'Sudden illness', '2024-12-07 13:00:00', 2, 2),
     (5, 'Sudden illness', '2025-12-12 14:00:00', 2, 4),
     (6, 'Sudden illness', '2025-12-29 15:00:00', 2, 1),
     (7, 'Regular checkup', '2025-01-04 18:00:00', 1, 3);

-- Medical Treatments for the Visits
insert into medical_treatment (id, description, type, visit_id)
values (1, 'Blood Test', 'RTG', 1),
       (2, 'ECG', 'EKG', 1);

insert into medical_treatment (id, description, type, visit_id)
values
       (3, 'Antibiotics', 'USG', 2),
       (4, 'MRI Scan', 'RTG', 3),
       (5, 'Blood Pressure Monitoring', 'USG', 5),
      (6, 'Medicines to lower blood pressure', 'USG', 5),
       (7, 'CT Scan', 'EKG', 6),
       (8, 'General Health Assessment', 'EKG', 7);

-- TYPE to  ENUM :)
-- 	USG,
-- 	EKG,
-- 	RTG

















