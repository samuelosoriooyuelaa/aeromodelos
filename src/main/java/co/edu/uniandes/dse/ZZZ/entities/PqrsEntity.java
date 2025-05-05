package co.edu.uniandes.dse.ZZZ.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class PqrsEntity extends BaseEntity {

    //atributos
    private String texto;
    private String tipo; 

    //una pqrs pertenece a un cliente
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;


}
