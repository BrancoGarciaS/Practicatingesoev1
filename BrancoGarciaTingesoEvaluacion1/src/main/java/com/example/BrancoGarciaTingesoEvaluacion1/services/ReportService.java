package com.example.BrancoGarciaTingesoEvaluacion1.services;


import com.example.BrancoGarciaTingesoEvaluacion1.entities.CuotaEntity;
import com.example.BrancoGarciaTingesoEvaluacion1.entities.ReportEntity;
import com.example.BrancoGarciaTingesoEvaluacion1.entities.StudentEntity;
import com.example.BrancoGarciaTingesoEvaluacion1.repositories.CuotaRepository;
import com.example.BrancoGarciaTingesoEvaluacion1.repositories.ReportRepository;
import com.example.BrancoGarciaTingesoEvaluacion1.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ReportService {
    @Autowired
    ReportRepository reportRepository;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CuotaRepository cuotaRepository;
    @Autowired
    CuotaService cuotaService;

    public ReportEntity createReport(String rut){
        // actualizar cuotas
        //studentService.updateCuotas();
        ReportEntity report = new ReportEntity();
        Optional<StudentEntity> s = studentRepository.findByRut(rut);
        if(s.isPresent()){ // si existe el estudiante en la base de datos

            // esta variable sirve en el caso de que se haya creado un reporte previamente
            // con ese rut
            Optional<ReportEntity> report_rut = reportRepository.findReportByRut(rut);
            StudentEntity student_rut = s.get();
            if(report_rut.isPresent()){ // si ya se habia creado un reporte con ese rut, se actualiza
                return updateReport(report_rut.get(), student_rut);
            }
            ArrayList<CuotaEntity> c = cuotaRepository.findByRut_cuota(rut);
            report.setRut(student_rut.getRut());
            report.setName_student(student_rut.getName());
            report.setLast_name(student_rut.getLast_name());
            report.setNum_exams(student_rut.getNum_exams());
            report.setMean_score(student_rut.getScore());
            report.setTotal_tariff(student_rut.getTariff());
            report.setPayment_type(student_rut.getPayment_type());
            report.setNum_installments(student_rut.getInstallments());
            int count_paid = 0; // numero de cuotas pagadas
            float tariff_paid_s = 0; // monto total pagado
            float tariff_to_pay = 0; // monto a pagar
            float interes_tariff = 0; // arancel a pagar con intereses
            Integer later = 0; // cuotas atrasadas
            if(c.size() > 0){
                LocalDate last_payment = c.get(0).getFecha_pago();
                for(CuotaEntity installment_s : c){
                    // si la cuota está pagada se aumenta el contador
                    if(installment_s.getEstado_cuota() == 1){
                        count_paid++;
                        tariff_paid_s += installment_s.getMonto();
                        // si aun no se ha encontrado una fecha de pago
                        // o si la fecha de pago de la cuota es más reciente que la fecha
                        // más reciente encontrada

                        if(last_payment != null && installment_s.getFecha_pago() != null){
                            if(installment_s.getFecha_pago().isAfter(last_payment)){
                                last_payment = installment_s.getFecha_pago();
                            }
                        }
                    }
                    else{ // si no está pagada
                        tariff_to_pay += installment_s.getMonto();
                    }
                    interes_tariff += installment_s.getMonto();
                    if(cuotaService.isLate(installment_s)){
                        // si la cuota está atrasada se cuenta en atrasos
                        later++;
                    }
                }
                if (last_payment != null) { // Verifica si last_payment no es nulo
                    report.setLast_payment(last_payment);
                }
            }

            report.setInteres_tariff(interes_tariff);
            report.setNum_installments_paid(count_paid);
            report.setTariff_paid(tariff_paid_s);

            report.setTariff_to_pay(tariff_to_pay);
            report.setLate_installments(later);
            return reportRepository.save(report);
        }
        return null;
    }

    public ReportEntity updateReport(ReportEntity report, StudentEntity student_rut){
        String rut = report.getRut();
        ArrayList<CuotaEntity> c = cuotaRepository.findByRut_cuota(rut);
        report.setNum_exams(student_rut.getNum_exams());
        report.setMean_score(student_rut.getScore());

        int count_paid = 0; // numero de cuotas pagadas
        float tariff_paid_s = 0; // monto total pagado
        float tariff_to_pay = 0; // monto a pagar
        float interes_tariff = 0; // arancel a pagar con intereses
        Integer later = 0; // cuotas atrasadas
        if(c.size() > 0){
            LocalDate last_payment = c.get(0).getFecha_pago();
            for(CuotaEntity installment_s : c){
                // si la cuota está pagada se aumenta el contador
                if(installment_s.getEstado_cuota() == 1){
                    count_paid++;
                    tariff_paid_s += installment_s.getMonto();
                    // si aun no se ha encontrado una fecha de pago
                    // o si la fecha de pago de la cuota es más reciente que la fecha
                    // más reciente encontrada

                    if(last_payment != null && installment_s.getFecha_pago() != null){
                        if(installment_s.getFecha_pago().isAfter(last_payment)){
                            last_payment = installment_s.getFecha_pago();
                        }
                    }
                }
                else{ // si no está pagada
                    tariff_to_pay += installment_s.getMonto();
                }
                interes_tariff += installment_s.getMonto();
                if(cuotaService.isLate(installment_s)){
                    // si la cuota está atrasada se cuenta en atrasos
                    later++;
                    System.out.println("atrasada: " + installment_s.getId_cuota() + "  ");
                }
            }
            if (last_payment != null) { // Verifica si last_payment no es nulo
                report.setLast_payment(last_payment);
            }
        }

        report.setInteres_tariff(interes_tariff);
        report.setNum_installments_paid(count_paid);
        report.setTariff_paid(tariff_paid_s);

        report.setTariff_to_pay(tariff_to_pay);
        report.setLate_installments(later);
        return reportRepository.save(report);

    }



}
