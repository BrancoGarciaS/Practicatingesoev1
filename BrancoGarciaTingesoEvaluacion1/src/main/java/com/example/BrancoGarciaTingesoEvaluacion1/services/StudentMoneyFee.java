package com.example.BrancoGarciaTingesoEvaluacion1.services;

import org.springframework.stereotype.Service;

@Service
public class StudentMoneyFee {
    public int descuentoArancelTotal(){
        int arancel_total = 1500000;
        return (int) (arancel_total * 0.2);
    }

    /*
    insert into students (id_student, email, last_name, name,
					  rut, school_name, exams_mean, installments,
					  num_exams, payment_type, school_type, senior_date, tariff)
					  values
					  (7, 'hola@gmail.com', 'robert', 'perez', '32.323.424-J','Santo tomas',
					  0, 0, 0, 0, 3, '2022-03-02', 1500000)

    SELECT id_Student, senior_date,
           AGE(CURRENT_DATE, senior_date) AS years_since_graduation
    FROM students
    where id_student = 43 OR id_student = 44 OR id_student = 7
     */
}
