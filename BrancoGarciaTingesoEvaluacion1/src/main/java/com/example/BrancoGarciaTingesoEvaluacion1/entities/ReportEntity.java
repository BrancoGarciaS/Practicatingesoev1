package com.example.BrancoGarciaTingesoEvaluacion1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id_report; // atributo llave

    @Column(unique = true, nullable = false)
    private String rut; // rut del estudiante

    private String name_student; // nombre del estudiante
    private String last_name; // apellido del estudiante
    private Long num_exams; // numero de examenes rendidos
    private Double mean_score; // promedio del puntaje de examenes
    private float total_tariff; // monto total de arancel a pagar
    private float interes_tariff; // tarifa con intereses
    private Integer payment_type; // tipo de pago
    private Integer num_installments; // numero total de cuotas pactadas
    private Integer num_installments_paid; // numero de cuotas pagadas
    private float tariff_paid; // monto total pagado
    private LocalDate last_payment; // fecha del ultimo pago
    private float tariff_to_pay; // tarifa a pagar
    private Integer late_installments; // numero de cuotas atrasadas

}
