CREATE TABLE consultations (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_doctor BIGINT NOT NULL,
    id_patient BIGINT NOT NULL,
    data DATETIME NOT NULL,
    PRIMARY KEY(id),
    CONSTRAINT fk_consultations_id_doctor FOREIGN KEY (id_doctor) REFERENCES doctors(id),
    CONSTRAINT fk_consultations_id_patient FOREIGN KEY (id_patient) REFERENCES patients(id)
);