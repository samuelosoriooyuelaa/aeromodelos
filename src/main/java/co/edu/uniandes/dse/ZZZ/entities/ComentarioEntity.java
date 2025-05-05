package co.edu.uniandes.dse.ZZZ.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class ComentarioEntity extends BaseEntity {

    //atributos
    private String texto;
    private String titulo; 
    private Integer calificacion;
    private Boolean recomienda;
    private String urlImagen;

    //un comentario le pertenece a un cliente
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;

    //un comentario le pertenece a un elemento
    @PodamExclude
    @ManyToOne
    private ElementoEntity elemento;

    //un comentario le pertenece a un modelo
    @PodamExclude
    @ManyToOne
    private ModeloEntity modelo;


}
