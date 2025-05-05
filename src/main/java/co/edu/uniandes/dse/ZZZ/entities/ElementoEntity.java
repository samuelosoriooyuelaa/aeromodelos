package co.edu.uniandes.dse.ZZZ.entities;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;


@Data
@Entity
public class ElementoEntity extends BaseEntity {
    //atributos
   private String nombre;
   private Float precio;
   private String descripcion;
   private String tipo;
   private String caracteristica;
   private String urlImagen;
   private Integer disponibilidad;

   //un elemento tiene 0,1 o muchos comentarios asociados
   @PodamExclude
   @OneToMany(mappedBy = "elemento")
   private List<ComentarioEntity> comentarios = new ArrayList<>();


}
