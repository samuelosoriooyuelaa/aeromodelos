package co.edu.uniandes.dse.ZZZ.entities;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class CategoriaEntity extends BaseEntity {
    //atributos
    private String nombreCategoria;
    private String descripcionCategoria;

    //una categoria tiene uno o muchos modelos
    @PodamExclude
    @OneToMany(mappedBy = "categoria")
    private List<ModeloEntity> modelos = new ArrayList<>();


}
