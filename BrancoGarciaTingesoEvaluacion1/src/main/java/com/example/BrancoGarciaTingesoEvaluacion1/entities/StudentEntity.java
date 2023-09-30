package com.example.BrancoGarciaTingesoEvaluacion1.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id_Student; // atributo llave
    //@Column(unique = true, nullable = false)
    @Column(name = "rut", nullable = false)
    private String rut;

    @OneToMany(mappedBy = "id_Student")
    @JsonIgnore
    private List<CuotaEntity> cuotas; // el estudiante tiene una lista de cuotas

    private String name;
    private String last_name;
    private String email;
    private Date senior_date; // año de egreso
    private String school_name; // nombre de la escuela
    /* */
    private Long school_type;
    private Integer num_exams;
    private Integer payment_type;
    private Integer installments; // numero de cuotas (si es 0, significa que será pago al contado
    private Integer tariff; // arancel
    private Integer exams_mean; // promedio del estudiante
    private Integer senior_year; // año de egreso

    private Double score; // puntaje
    //private int yearsSinceGraduation;

    /*
    public StudentEntity(long id_Student){
        this.id_Student = id_Student;
    } */

    // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - //
    // Constructor al ser una entidad que va a ser vinculada tras una FK a otra
    public StudentEntity(long id_Student){
        this.id_Student = id_Student;
    }
    //public StudentEntity(){}
    /* */
    public Long getId_Student() {
        return id_Student;
    }

    public void setId_Student(Long id_Student) {
        this.id_Student = id_Student;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public Date getSenior_date() {
        return senior_date;
    }

    public void setSenior_date(Date senior_date) {
        this.senior_date = senior_date;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }
    /* */

    public Long getSchool_type() {
        return school_type;
    }

    public void setSchool_type(Long school_type) {
        this.school_type = school_type;
    }

    public Integer getNum_exams() {
        return num_exams;
    }

    public void setNum_exams(Integer num_exams) {
        this.num_exams = num_exams;
    }

    public Integer getInstallments() {
        return installments;
    }

    public void setInstallments(Integer installments) {
        this.installments = installments;
    }

    public Integer getTariff() {
        return tariff;
    }

    public void setTariff(Integer tariff) {
        this.tariff = tariff;
    }

    public Integer getPayment_type() {
        return payment_type;
    }

    public void setPayment_type(Integer payment_type) {
        this.payment_type = payment_type;
    }


    /*
    public int getYearsSinceGraduation() {
        return yearsSinceGraduation;
    }

    public void setYearsSinceGraduation(int yearsSinceGraduation) {
        this.yearsSinceGraduation = yearsSinceGraduation;
    }*/

    public Integer getExams_mean() {
        return exams_mean;
    }

    public void setExams_mean(Integer exams_mean) {
        this.exams_mean = exams_mean;
    }

    public List<CuotaEntity> getCuotas() {
        return cuotas;
    }

    public void setCuotas(List<CuotaEntity> cuotas) {
        this.cuotas = cuotas;

        /*
        this.cuotas = new ArrayList<>(); // Crea una nueva lista para evitar referencias compartidas
        for (CuotaEntity cuota : cuotas) {
            this.cuotas.add(cuota);
        }*/
    }


    public Integer getSenior_year() {
        return senior_year;
    }

    public void setSenior_year(Integer senior_year) {
        this.senior_year = senior_year;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    /* */
}
