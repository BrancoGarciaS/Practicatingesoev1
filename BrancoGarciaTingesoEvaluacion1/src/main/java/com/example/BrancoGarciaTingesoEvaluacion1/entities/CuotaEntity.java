package com.example.BrancoGarciaTingesoEvaluacion1.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "cuotas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CuotaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id_cuota; // atributo llave

    private Integer estado_cuota; // 0 pendiente, 1 pagado
    private float monto;

    private LocalDate fecha_vencimiento;
    private LocalDate fecha_inicio;
    private Date fecha_pago;

    @Column(name = "aux")
    private long aux;

    private String rut_cuota;

    @ManyToOne
    @JoinColumn(name = "id_Student")
    private StudentEntity id_Student;

    public Long getId_cuota() {
        return id_cuota;
    }

    public void setId_cuota(Long id_cuota) {
        this.id_cuota = id_cuota;
    }

    public Integer getEstado_cuota() {
        return estado_cuota;
    }

    public void setEstado_cuota(Integer estado_cuota) {
        this.estado_cuota = estado_cuota;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Date getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public StudentEntity getEstudiante() {
        return id_Student;
    }

    public void setEstudiante(Long id_student) {
        this.id_Student = new StudentEntity(id_student);
        this.aux = id_student;
    }

    public LocalDate getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(LocalDate fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public LocalDate getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDate fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }
}
