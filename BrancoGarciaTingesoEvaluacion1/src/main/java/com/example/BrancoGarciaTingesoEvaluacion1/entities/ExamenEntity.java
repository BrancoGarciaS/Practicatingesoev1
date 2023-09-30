package com.example.BrancoGarciaTingesoEvaluacion1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "examenes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id_examen; // atributo llave
    private String rut; // atributo que conecta con estudiante
    private LocalDate fecha_examen;
    private String fecha;
    private float puntaje_examen;

    public Long getId_examen() {
        return id_examen;
    }

    public void setId_examen(Long id_examen) {
        this.id_examen = id_examen;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public LocalDate getFecha_examen() {
        return fecha_examen;
    }

    public void setFecha_examen(LocalDate fecha_examen) {
        this.fecha_examen = fecha_examen;
    }

    public float getPuntaje_examen() {
        return puntaje_examen;
    }

    public void setPuntaje_examen(float puntaje_examen) {
        this.puntaje_examen = puntaje_examen;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
