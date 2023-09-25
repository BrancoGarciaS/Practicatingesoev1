package com.example.BrancoGarciaTingesoEvaluacion1.services;

import com.example.BrancoGarciaTingesoEvaluacion1.entities.StudentEntity;
import org.springframework.stereotype.Service;

@Service
public class CalculoCuotas {

    public Integer descuento_TipoColegio(StudentEntity estudiante){
        Integer arancel = 1500000;
        Integer descuento = 0;
        if(estudiante.getSchool_type() == 1){ // si su colegio es tipo municipal
            descuento = (int) (arancel * 0.2);
        }
        else if(estudiante.getSchool_type() == 2){ // si es su colegio es tipo subvencionado
            descuento = (int) (arancel * 0.1);
        }
        // en caso de que el colegio sea privado (o sea 3) el descuento se conserva en 0
        return descuento;
    }

    public Integer descuento_AñosEgreso(StudentEntity estudiante){
        Integer arancel = 1500000;
        Integer descuento = 0;
        Integer años_egreso = 2023 - estudiante.getSenior_year();
        if(años_egreso < 1){
            descuento = (int) (arancel * 0.15);
        }
        // entre 1 o 2 años de egreso
        else if(años_egreso < 3){
            descuento = (int) (arancel * 0.08);
        }
        //  entre los 3 años a 4 años de egreso
        else if(años_egreso < 5){
            descuento = (int) (arancel * 0.04);
        }
        // a los 5 años o más no hay descuento (es igual a 0)
        return descuento;
    }

    public Integer arancel_con_descuento(StudentEntity estudiante){
        Integer arancel = 1500000;
        arancel = arancel - descuento_TipoColegio(estudiante);
        arancel = arancel -descuento_AñosEgreso(estudiante);
        return arancel;
    }

    public Integer descuento_Puntaje(StudentEntity estudiante){
        Integer arancel = 1500000;
        Integer descuento = 0;
        Integer puntaje = estudiante.getExams_mean();
        if(puntaje >= 950 && puntaje <= 1000){
            descuento = (int) (arancel * 0.1);
        }
        else if(puntaje >= 900 && puntaje <= 949){
            descuento = (int) (arancel * 0.05);
        }
        else if(puntaje >= 850 && puntaje <= 899){
            descuento = (int) (arancel * 0.02);
        }
        // menos de 850 no tiene descuento
        return descuento;
    }
    public Integer max_cuentas(StudentEntity estudiante){
        if(estudiante.getSchool_type() == 1){
            // si es de colegio municipal tiene máximo 10 cuotas
            return 10;
        }
        else if(estudiante.getSchool_type() == 2){
            // si es de colegio subvencionado tiene máximo 7 cuotas
            return 7;
        }
        return 4;  // en caso de venir de colegio privado
    }
}
