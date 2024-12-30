-- Address for Doctor
insert into address (id, address_line1, address_line2, city, postal_code)
values (1, 'Street 1', 'Building A', 'Warsaw', '62-030');

-- Address for Patient
insert into address (id, address_line1, address_line2, city, postal_code)
values (2, 'Street 2', 'Apartment 5', 'Krakow', '30-001');

-- Doctor
insert into doctor (id, first_name, last_name, telephone_number, email, doctor_number, specialization, address_id)
values (1, 'John', 'Doe', '123456789', 'johndoe@example.com', 'DOC001', 'Cardiology', 1);

-- Patient
insert into patient (id, first_name, last_name, telephone_number, email, patient_number, date_of_birth, address_id, is_insured)
values (1, 'Jane', 'Smith', '987654321', 'janesmith@example.com', 'PAT001', '1990-05-15', 2, true);

-- Visit
insert into visit (id, description, time, doctor_id, patient_id)
values (1, 'Regular checkup', '2024-11-23 10:00:00', 1, 1);

-- Medical Treatments for the Visit
insert into medical_treatment (id, description, type, visit_id)
values (1, 'Blood Test', 'Lab Test', 1),
       (2, 'ECG', 'Procedure', 1);