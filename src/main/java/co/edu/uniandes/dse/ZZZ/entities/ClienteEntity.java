package co.edu.uniandes.dse.ZZZ.entities;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class ClienteEntity extends BaseEntity{

    //atributos 
    private String nombre;
    private String apellido;
    private String correo;

    //un cliente tiene 0 o muchos comentarios asociados
    @PodamExclude
    @OneToMany(mappedBy = "cliente")
    private List<PqrsEntity> pqrs = new ArrayList<>();

    @PodamExclude
    @OneToMany(mappedBy = "cliente")
    private List<ComentarioEntity> comentarios = new ArrayList<>();  //aqui estaba como List<PqrsEntity>




}
