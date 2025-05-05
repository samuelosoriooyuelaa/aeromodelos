package co.edu.uniandes.dse.ZZZ.entities;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class EmpresaEntity extends BaseEntity {

    //atributos
    private String nombreEmpresa;
    private String paisOrigen;
    private String ciudadOrigen;
    private int cantidadEmpleados;

    //una empresa tiene uno o muchos modelos
    /*Relación Empresa-Modelo */
    @PodamExclude
    @OneToMany(mappedBy = "empresa")
    private List<ModeloEntity> modelos = new ArrayList<>();


}
