package com.example.BrancoGarciaTingesoEvaluacion1.services;
import com.example.BrancoGarciaTingesoEvaluacion1.entities.CuotaEntity;
import com.example.BrancoGarciaTingesoEvaluacion1.entities.ExamenEntity;
import com.example.BrancoGarciaTingesoEvaluacion1.entities.StudentEntity;
import com.example.BrancoGarciaTingesoEvaluacion1.repositories.CuotaRepository;
import com.example.BrancoGarciaTingesoEvaluacion1.repositories.ExamenRepository;
import com.example.BrancoGarciaTingesoEvaluacion1.repositories.StudentRepository;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExamenService {
    @Autowired
    ExamenRepository examenRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    CuotaRepository cuotaRepository;

    @Autowired
    CalculoCuotas calculoCuotas;

    public ExamenEntity saveData(ExamenEntity examen){
        return examenRepository.save(examen);
    }

    //private final Logger logg = (Logger) LoggerFactory.getLogger(ExamenService.class);

    @Generated
    public String guardar(MultipartFile file){
        String filename = file.getOriginalFilename();
        if(filename != null){
            if(!file.isEmpty()){
                try{
                    byte [] bytes = file.getBytes();
                    Path path  = Paths.get(file.getOriginalFilename());
                    Files.write(path, bytes);
                    System.out.printf("archivo guardado");
                    //logg.info("Archivo guardado");
                }
                catch (IOException e){
                    //logg.info("ERROR", e);
                    System.out.printf("error");
                }
            }
            return "Archivo guardado con exito!";
        }
        else{
            return "No se pudo guardar el archivo";
        }
    }

    @Generated
    public void leerCsv(String direccion){
        String texto = "";
        BufferedReader bf = null;
        //dataRepository.deleteAll();
        try{
            bf = new BufferedReader(new FileReader(direccion));
            String temp = "";
            String bfRead;
            int count = 1;
            while((bfRead = bf.readLine()) != null){
                if (count == 1){
                    count = 0;
                }
                else{
                    guardarDataDB(bfRead.split(";")[0], bfRead.split(";")[1], bfRead.split(";")[2]);
                    temp = temp + "\n" + bfRead;
                }
            }
            texto = temp;
            System.out.println("Archivo leido exitosamente");
        }catch(Exception e){
            System.err.println("No se encontro el archivo");
        }finally{
            if(bf != null){
                try{
                    bf.close();
                }catch(IOException e){
                    //logg.error("ERROR", e);
                }
            }
        }
    }

    public void guardarData(ExamenEntity data){
        examenRepository.save(data);
    }

    public void guardarDataDB(String rut, String fecha, String score){
        ExamenEntity newData = new ExamenEntity();
        newData.setRut(rut);
        newData.setFecha(fecha);
        newData.setPuntaje_examen(Float.parseFloat(score));
        newData.setFecha_examen(LocalDate.now());
        guardarData(newData);
    }

    public void asignarPromedios(){
        // agrupo por rut y calculo media
        List<Object[]> results = examenRepository.groupMean();

        for (Object[] result : results) {
            // por cada fila de la consulta
            String rut = (String) result[0];
            Double promedio = (Double) result[1];
            Long num_exams = (Long) result[2];
            // obtengo el estudiante por el rut
            Optional<StudentEntity> student_rut = studentRepository.getStudentByRut(rut);
            if(student_rut.isPresent()){
                System.out.print("Rut encontrado:" + rut);
                StudentEntity s = student_rut.get();
                s.setScore(promedio);
                Long n = num_exams + s.getNum_exams();
                s.setNum_exams(n);
                studentRepository.save(s);
                if(s.getPayment_type() == 1 || !(s.getCuotas().isEmpty())){
                    ArrayList<CuotaEntity> cuotas = cuotaRepository.findByRut_cuota(rut);
                    for(CuotaEntity cuota : cuotas){
                        if(cuota.getEstado_cuota() == 0){ // si la cuota está pendiente
                            float m = calculoCuotas.descuentoPuntaje(cuota, promedio);
                            cuota.setMonto(m);
                            cuotaRepository.save(cuota);
                            System.out.print("Descuento aplicado\n");
                        }
                    }
                }
            }
            else{
                System.out.print("Rut no encontrado:" + rut);
            }
        }
    }

}
